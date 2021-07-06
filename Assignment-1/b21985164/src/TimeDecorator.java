import java.util.ArrayList;

public class TimeDecorator {
    SortingAlgorithm sortingAlgorithm;
    TimeDecorator(SortingAlgorithm sortingAlgorithm){
        this.sortingAlgorithm = sortingAlgorithm;
    }

    public long printExecutionTime(){
        long executionTime = this.time();
        System.out.print(String.format("%d nanoseconds to sort %d elements with %s\r",
                executionTime, sortingAlgorithm.inputSize, sortingAlgorithm.name ));
        return executionTime;
    }

// TODO I can merge random factory and time decorator to measure time wrt different inputs
//    public void printAverageTimeOf(int numberOfRuns){
//        long sum = 0;
//    }

    private long time(){
        long startTime = System.nanoTime();
        sortingAlgorithm.sort();
        long stopTime = System.nanoTime();
        return stopTime - startTime;
    }
}
