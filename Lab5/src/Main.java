import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

interface Task {
    void execute();
}

class OutTask implements Task {
    String message;

    public OutTask(String message) {
        this.message = message;
    }

    public void execute() {
        System.out.println(this.message);
    }
}

class RandomOutTask implements Task {
    int rand;

    static Random r = new Random(12345);

    public RandomOutTask() {
        this.rand = r.nextInt();
    }

    public void execute() {
        System.out.println(this.rand);
    }
}

class CounterOutTask implements Task {
    public static int count = 0;

    public CounterOutTask() {
    }

    public void execute() {
        System.out.println(++this.count);
    }
}

interface Container {
    Task pop();

    void push(Task task);

    int size();

    boolean isEmpty();

    void transferFrom(Container container);

    ArrayList<Task> getTasks();
}

class Stack implements Container {
    ArrayList<Task> tasks = new ArrayList<>();

    public Task pop() {
        return tasks.remove(tasks.size() - 1);
    }

    public void push(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void transferFrom(Container container) {
        while (!container.isEmpty()) {
            tasks.add(container.pop());
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

class Queue extends Stack {
    public Task pop() {
        return tasks.remove(0);
    }
}

interface Minus {
    void minus(float value);
}

interface Plus {
    void plus(float value);
}

interface Mult {
    void mult(float value);
}

interface Div {
    void div(float value);
}

class Operation implements Minus, Plus, Mult, Div {
    public float value;

    public float getNumber() {
        return value;
    }

    public Operation(float value) {
        this.value = value;
    }

    public void minus(float value) {
        this.value -= value;
    }

    public void plus(float value) {
        this.value += value;
    }

    public void mult(float value) {
        this.value *= value;
    }

    public void div(float value) {
        if (value == 0)
            System.out.println("Division by zero is not possible");
        else
            this.value /= value;
    }
}

class Song {
    String name;
    int id;
    String composer;

    public Song(String name, int id, String composer) {
        this.name = name;
        this.id = id;
        this.composer = composer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String toString() {
        return "Song{" +
                "name=" + name +
                ", id=" + id +
                ", composer=" + composer +
                '}';
    }
}

abstract class Album {
    ArrayList<Song> songs = new ArrayList<>();

    abstract void addSong(Song song);

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public String toString() {
        return "Album{" +
                "songs=" + songs +
                '}';
    }
}

class DangerousAlbum extends Album {
    public void addSong(Song song) {
        boolean flag = true;

        for (int i = 2; i <= song.id / 2; ++i) {
            if (song.id % i == 0) {
                flag = false;
                break;
            }
        }

        if (flag)
            this.songs.add(song);
    }
}

class ThrillerAlbum extends Album {
    public void addSong(Song song) {
        if (song.composer == "Michael Jackson" && song.id % 2 == 0)
            this.songs.add(song);
    }
}

class BadAlbum extends Album {
    public void addSong(Song song) {
        if (song.name.length() == 3) {
            int n = song.id;
            int r, mirror = 0;

            while (n > 0) {
                r = n % 10;
                mirror = mirror * 10 + r;
                n /= 10;
            }

            if (mirror == song.id)
                this.songs.add(song);
        }
    }
}

public class Main {
    private static final int TASK_NO = 6;
    private static Task[] taskList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int taskNum = scanner.nextInt();

        taskList = new Task[TASK_NO];
        taskList[0] = new OutTask("First message task");
        taskList[1] = new RandomOutTask();
        taskList[2] = new CounterOutTask();
        taskList[3] = new OutTask("Second message task");
        taskList[4] = new CounterOutTask();
        taskList[5] = new RandomOutTask();


        switch(taskNum) {
            case 1:
                for (Task task : taskList) {
                    task.execute();
                }

                break;

            case 2:
                System.out.println("----> Queue");
                Queue q = new Queue();
                for(Task task : taskList) {
                    q.push(task);
                }
                q.pop();
                q.pop();
                for (Task task : q.getTasks()) {
                    task.execute();
                }

                System.out.println("----> Stack");
                Stack s = new Stack();
                for(Task task : taskList) {
                    s.push(task);
                }
                s.pop();
                s.pop();
                for (Task task : s.getTasks()) {
                    task.execute();
                }

                System.out.println("----> Testare metoda transferFrom");
                q.transferFrom(s);

                for (Task task : q.getTasks()) {
                    task.execute();
                }

                System.out.println(s.isEmpty());

                break;

            case 3:
                Operation op = new Operation(13);
                op.div(0);
                op.div(1);
                System.out.println(op.getNumber());
                op.mult(2);
                System.out.println(op.getNumber());
                op.minus(3);
                System.out.println(op.getNumber());
                op.plus(7);
                System.out.println(op.getNumber());
                break;

            case 4:
                Song song1 = new Song("Bad", 101, "Michael Jackson");
                Song song2 = new Song("Dangerous", 19, "Michael Jackson");
                Song song3 = new Song("Heal the world", 53, "Composer");
                Song song4 = new Song("Thriller", 82, "Michael Jackson" );
                Song song5 = new Song("Beat it", 83, "Michel Jakson");
                Song song6 = new Song("Smooth Criminal", 77, "Composer");

                DangerousAlbum dangerous = new DangerousAlbum();
                dangerous.addSong(song2);
                dangerous.addSong(song3);
                dangerous.addSong(song6);
                System.out.println(dangerous);

                ThrillerAlbum thriller = new ThrillerAlbum();
                thriller.addSong(song4);
                thriller.addSong(song6);
                thriller.addSong(song5);
                System.out.println(thriller);

                BadAlbum bad = new BadAlbum();
                bad.addSong(song1);
                bad.addSong(song6);
                System.out.println(bad);

                break;

        }

    }
}