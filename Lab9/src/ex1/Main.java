package ex1;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

class Utils {
    public static final String BASIC_STRATEGY = "basic";
    public static final String FILTERED_STRATEGY = "filtered";
    public static int getClientId(){
        return 42;
    }
    public static final int MAX_STEP_DIFFERENCE = 1000;
}

class DataRepository extends Observable {
    ArrayList<SensorData> data = new ArrayList<>();
    public void addData(SensorData dataRecord) {
        data.add(dataRecord);
        setChanged();
        notifyObservers(dataRecord);
    }

}

// Data stored by the DataRepository
class SensorData {
    private int stepsCount;
    private long timestamp;

    @Override
    public String toString() {
        return String.format("stepsCount=%d, timestamp=%d", stepsCount, timestamp);
    }

    public SensorData(int stepsCount, long timestamp) {
        this.stepsCount = stepsCount;
        this.timestamp = timestamp;
    }

    public int getStepsCount() {
        return stepsCount;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

// Message sent by the ServerCommunicationController
class ServerMessage {
    private int steps;
    private int clientid;
    private long timestamp;

    ServerMessage(int steps, int clientid, long timestamp) {
        this.steps = steps;
        this.clientid = clientid;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{" +
                "steps=" + steps +
                ", clientid=" + clientid +
                ", timestamp=" + timestamp +
                '}';
    }
}

/**
 * Defines the operations that can be performed on step data. Each implementation uses its own algorithm.
 */
interface StepCountStrategy {
    /**
     * Computes and returns the total number of steps since the app started running.
     */
    int getTotalSteps();

    /**
     * Returns a string describing this strategy, to be used for printing purposes.
     */
    String getStrategyDescription();
}

abstract class Strategy implements StepCountStrategy {
}

class BasicStepCountStrategy extends Strategy {
    DataRepository dataRepository;

    public BasicStepCountStrategy(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public int getTotalSteps() {
        int totalSteps = 0;

        for (SensorData data : dataRepository.data)
            totalSteps += data.getStepsCount();

        return totalSteps;
    }

    @Override
    public String getStrategyDescription() {
        return "Basic strategy, total step count: : " + getTotalSteps();
    }
}

class FilteredStepCountStrategy extends Strategy {
    DataRepository dataRepository;

    public FilteredStepCountStrategy(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public int getTotalSteps() {
        int totalSteps = 0;

        for (SensorData data : dataRepository.data)
            totalSteps += data.getStepsCount() >= 0 && data.getStepsCount() < Utils.MAX_STEP_DIFFERENCE ?
                    data.getStepsCount() : 0;

        return totalSteps;
    }

    @Override
    public String getStrategyDescription() {
        return "Filtered strategy, total step count: : " + getTotalSteps();
    }
}

class StepCountStrategyFactory {
    public static Strategy createStrategy(String type, DataRepository dataRepository) {
        switch (type) {
            case Utils.BASIC_STRATEGY: return new BasicStepCountStrategy(dataRepository);
            case Utils.FILTERED_STRATEGY: return new FilteredStepCountStrategy(dataRepository);
        }
        throw new IllegalArgumentException("The strategy type " + type + " is not recognized.");
    }
}

class DataAggregator implements Observer {
    Strategy strategy;

    public DataAggregator(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(strategy.getStrategyDescription());
    }
}

class ConsoleLogger implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        SensorData dataRecord = (SensorData)arg;
        System.out.println("New sensor data: " + dataRecord.toString());
    }
}

class ServerCommunicationController implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        SensorData dataRecord = (SensorData) arg;
        ServerMessage message = new ServerMessage(dataRecord.getStepsCount(), Utils.getClientId(), dataRecord.getTimestamp());
        System.out.println("Generated server message: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        DataRepository dataRepository = new DataRepository();

        long baseTimestamp = 10000;

        Scanner scanner = new Scanner(System.in);
        int taskNum = scanner.nextInt();

        switch (taskNum) {
            case 1:
                dataRepository.addObserver(new ConsoleLogger());
                dataRepository.addObserver(new ServerCommunicationController());
                break;
            case 2:
                String first_strategy_type = scanner.next();
                String second_strategy_type = scanner.next();
                dataRepository.addObserver(new DataAggregator(StepCountStrategyFactory.createStrategy(first_strategy_type, dataRepository)));
                dataRepository.addObserver(new DataAggregator(StepCountStrategyFactory.createStrategy(second_strategy_type, dataRepository)));

                break;
        }

        dataRepository.addData(new SensorData(10, baseTimestamp + 1));
        System.out.println();

        dataRepository.addData(new SensorData(20, baseTimestamp + 2));
        System.out.println();

        dataRepository.addData(new SensorData(30, baseTimestamp + 3));
        System.out.println();

        dataRepository.addData(new SensorData(4000, baseTimestamp + 4));
        System.out.println();

        dataRepository.addData(new SensorData(50, baseTimestamp + 5));
        System.out.println();

        dataRepository.addData(new SensorData(-100, baseTimestamp + 6));
        System.out.println();

        dataRepository.addData(new SensorData(500, baseTimestamp + 600));
        System.out.println();
    }
}
