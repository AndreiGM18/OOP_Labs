package ex3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// DIAGRAM CLASSES
class DiagramComponent {
    private String text = "text";
    private String color = "WHITE";
    private int height = 40;
    private int weight = 100;

    @Override
    public String toString() {
        return "[" +
                "text='" + text + '\'' +
                ", color='" + color + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", connectedComponents=" + connectedComponents +
                ']';
    }

    private List<String> connectedComponents = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void connectTo(String componentId) {
        connectedComponents.add(componentId);
    }

    public void removeConnection(String componentId) {
        connectedComponents.remove(componentId);
    }
}


class DiagramCanvas {
    private List<DiagramComponent> components = new ArrayList<>();

    public void addComponent(DiagramComponent diagramComponent) {
        components.add(diagramComponent);
    }

    public void removeComponent(DiagramComponent diagramComponent) {
        components.remove(diagramComponent);
    }

    public DiagramComponent getComponent(int id) {
        return components.get(id);
    }

    public void show() {
        System.out.println("Diagram:");
        components.forEach(System.out::println);
    }
}


// COMMAND CLASSES
enum CommandType {
    DRAW_RECTANGLE("draw rectangle"),
    CHANGE_COLOR("change color"),
    RESIZE("resize"),
    CONNECT("connect"),
    CHANGE_TEXT("change text");

    public final String text;

    CommandType(String text) {
        this.text = text;
    }

    public static CommandType fromString(String text) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.text.equalsIgnoreCase(text)) {
                return commandType;
            }
        }
        return null;
    }
}

interface DrawCommand {
    void execute();
    void undo();
}

class Invoker {
    LinkedList<DrawCommand> commands = new LinkedList<>();
    LinkedList<DrawCommand> undidCommands = new LinkedList<>();

    /**
     * Clear up all the used resources, start fresh :D
     */
    public void restart() {
        commands.clear();
        undidCommands.clear();
    }

    /**
     * Executes a given command
     * @param command
     */
    public void execute(DrawCommand command) {
        commands.addFirst(command);
        command.execute();
    }

    /**
     * Undo the latest command
     */
    public void undo() {
        undidCommands.addFirst(commands.removeFirst());

        // Hint: use data structures to keep track of commands
        // Do not use the java.util.Stack, its implementation is based on vector which is inefficient and deprecated.
        // Use a class that implements the Deque interface, e.g. LinkedList https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Deque.html

        undidCommands.get(0).undo();
    }

    /**
     * Redo command previously undone. Cannot perform a redo after an execute, only after at least one undo.
     */
    public void redo() {
        if (undidCommands.size() != 0) {
            commands.addFirst(undidCommands.removeFirst());
            commands.get(0).execute();
        }
    }
}

class DrawRectangleCommand implements DrawCommand {
    DiagramCanvas diagramCanvas;
    DiagramComponent diagramComponent;

    public DrawRectangleCommand(DiagramCanvas diagramCanvas) {
        this.diagramCanvas = diagramCanvas;
    }


    @Override
    public void execute() {
        DiagramComponent diagramComponent = new DiagramComponent();
        diagramCanvas.addComponent(diagramComponent);
        this.diagramComponent = diagramComponent;
    }

    @Override
    public void undo() {
        diagramCanvas.removeComponent(diagramComponent);
    }
}

class ResizeCommand implements DrawCommand {
    DiagramCanvas diagramCanvas;
    int numDiagram;
    double percentage;

    public ResizeCommand(DiagramCanvas diagramCanvas, int numDiagram, double percentage) {
        this.diagramCanvas = diagramCanvas;
        this.numDiagram = numDiagram;
        this.percentage = percentage;
    }

    @Override
    public void execute() {
        DiagramComponent diagramComponent = diagramCanvas.getComponent(numDiagram);
        diagramComponent.setHeight((int)(percentage * diagramComponent.getHeight() / 100));
        diagramComponent.setWeight((int)(percentage * diagramComponent.getWeight() / 100));
    }

    @Override
    public void undo() {
        DiagramComponent diagramComponent = diagramCanvas.getComponent(numDiagram);
        diagramComponent.setHeight((int)(100 * diagramComponent.getHeight() / percentage));
        diagramComponent.setWeight((int)(100 * diagramComponent.getWeight() / percentage));
    }
}

class ConnectComponentsCommand implements DrawCommand {
    DiagramCanvas diagramCanvas;
    String c1, c2;

    public ConnectComponentsCommand(DiagramCanvas diagramCanvas, String c1, String c2) {
        this.diagramCanvas = diagramCanvas;
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public void execute() {
        DiagramComponent diagramComponent1 = diagramCanvas.getComponent(Integer.parseInt(c1));
        DiagramComponent diagramComponent2 = diagramCanvas.getComponent(Integer.parseInt(c2));
        diagramComponent1.connectTo(c2);
        diagramComponent2.connectTo(c1);
    }

    @Override
    public void undo() {
        DiagramComponent diagramComponent1 = diagramCanvas.getComponent(Integer.parseInt(c1));
        DiagramComponent diagramComponent2 = diagramCanvas.getComponent(Integer.parseInt(c2));
        diagramComponent1.removeConnection(c2);
        diagramComponent2.removeConnection(c1);
    }
}

class ChangeTextCommand implements DrawCommand {
    DiagramCanvas diagramCanvas;
    int numDiagram;
    String text;
    String oldText;

    public ChangeTextCommand(DiagramCanvas diagramCanvas, int numDiagram, String text) {
        this.diagramCanvas = diagramCanvas;
        this.numDiagram = numDiagram;
        this.text = text;
    }

    @Override
    public void execute() {
        DiagramComponent diagramComponent = diagramCanvas.getComponent(numDiagram);
        oldText = diagramComponent.getText();
        diagramComponent.setText(text);
    }

    @Override
    public void undo() {
        DiagramComponent diagramComponent = diagramCanvas.getComponent(numDiagram);
        diagramComponent.setText(oldText);
    }
}

class ChangeColorCommand implements DrawCommand {
    DiagramCanvas diagramCanvas;
    int numDiagram;
    String color;
    String oldColor;

    public ChangeColorCommand(DiagramCanvas diagramCanvas, int numDiagram, String color) {
        this.diagramCanvas = diagramCanvas;
        this.numDiagram = numDiagram;
        this.color = color;
    }

    @Override
    public void execute() {
        DiagramComponent diagramComponent = diagramCanvas.getComponent(numDiagram);
        oldColor = diagramComponent.getColor();
        diagramComponent.setColor(color);
    }

    @Override
    public void undo() {
        DiagramComponent diagramComponent = diagramCanvas.getComponent(numDiagram);
        diagramComponent.setColor(oldColor);
    }
}

class Client {

    private Invoker invoker;
    private DiagramCanvas diagramCanvas;

    Client() {
        invoker = new Invoker();
        diagramCanvas = new DiagramCanvas();
    }

    public void showDiagram() {
        diagramCanvas.show();
    }

    public void newDiagram() {
        diagramCanvas = new DiagramCanvas();
        invoker.restart();
    }

    public void executeAction(String commandName, String ...args) {
        DrawCommand command;
        try {
            CommandType commandType = CommandType.fromString(commandName);
            if (commandType == null) {
                throw new IllegalArgumentException();
            }
            command = getCommand(commandType, args);

        } catch(IllegalArgumentException ex) {
            System.out.println("Invalid command: " + commandName);
            System.out.println("Available commands:");
            for (CommandType type : CommandType.values()) {
                System.out.println("\t- " + type.text);
            }
            return;
        }

        invoker.execute(command);
    }

    private DrawCommand getCommand(CommandType type, String ...args) throws IllegalArgumentException {
        try {
            switch (type) {
                case DRAW_RECTANGLE: return new DrawRectangleCommand(diagramCanvas);
                case RESIZE: return new ResizeCommand(diagramCanvas, Integer.parseInt(args[0]), Double.parseDouble(args[1]));
                case CONNECT: return new ConnectComponentsCommand(diagramCanvas, args[0], args[1]);
                case CHANGE_TEXT: return new ChangeTextCommand(diagramCanvas, Integer.parseInt(args[0]), args[1]);
                case CHANGE_COLOR: return new ChangeColorCommand(diagramCanvas, Integer.parseInt(args[0]), args[1]);
            };
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    public void undo(){
        invoker.undo();
    }

    public void redo() {
        invoker.redo();
    }
}

public class Main {
    private static String spacerSymbols = new String(new char[40]).replace("\0", "-");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int taskNum = scanner.nextInt();

        Client client = new Client();

        switch(taskNum) {
            case 1:
                printOutputSpacerFor("testDraw");
                testDraw(client);

                printOutputSpacerFor("testTextAndColor");
                testTextAndColor(client);

                printOutputSpacerFor("testResize");
                testResize(client);

                printOutputSpacerFor("testConnect");
                testConnect(client);

                printOutputSpacerFor("testAllCommands");
                testAllCommands(client);

                break;
            case 2:
                printOutputSpacerFor("testSimpleUndoRedo");
                testSimpleUndoRedo(client);

                printOutputSpacerFor("testComplexUndoRedo");
                testComplexUndoRedo(client);

                break;
        }

    }

    private static void printOutputSpacerFor(String test) {
        System.out.println(spacerSymbols + test + spacerSymbols);
    }

    private static void testDraw(Client client) {
        client.newDiagram();

        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");

        client.showDiagram();
    }

    private static void testTextAndColor(Client client) {
        testDraw(client);
        client.executeAction("change color", "0", "RED");
        client.executeAction("change color", "1", "BLUE");
        client.executeAction("draw rectangle");
        client.executeAction("change text", "0", "MyClass1");
        client.executeAction("change text", "5", "MyClass2");
        client.showDiagram();
    }

    private static void testConnect(Client client) {
        testDraw(client);
        client.executeAction("connect", "0", "1");
        client.executeAction("connect", "3", "2");
        client.executeAction("connect", "1", "4");
        client.executeAction("draw rectangle");
        client.showDiagram();
    }

    private static void testResize(Client client) {
        testDraw(client);
        client.executeAction("resize", "0", "10");
        client.executeAction("resize", "2", "50");
        client.executeAction("resize", "4", "25");
        client.showDiagram();
    }

    private static void testAllCommands(Client client) {
        client.newDiagram();

        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("resize", "0", "10");
        client.executeAction("change color", "2", "PINK");
        client.executeAction("connect", "2", "1");
        client.executeAction("draw rectangle");
        client.executeAction("change text", "1", "Class1");
        client.executeAction("change text", "3", "Class2");

        client.showDiagram();
    }

    private static void testSimpleUndoRedo(Client client) {
        client.newDiagram();

        client.executeAction("draw rectangle");
        client.executeAction("change color", "0", "ORANGE");
        client.showDiagram();

        client.executeAction("draw rectangle");
        client.showDiagram();

        client.undo();
        client.showDiagram();

        client.redo();
        client.showDiagram();
    }

    private static void testComplexUndoRedo(Client client) {
        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("draw rectangle");
        client.executeAction("resize", "0", "10");
        client.executeAction("change color", "2", "PINK");
        client.executeAction("connect", "2", "1");
        client.executeAction("draw rectangle");
        client.executeAction("change text", "1", "Class1");
        client.executeAction("change color", "3", "RED");
        client.showDiagram();
        client.undo();
        client.undo();
        client.showDiagram();

        client.undo();
        client.undo();
        client.showDiagram();

        client.redo();
        client.redo();
        client.executeAction("resize", "2", "50");
        client.showDiagram();
    }
}