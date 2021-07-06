import java.util.*;

/**
 * For the sake of bitonic sort all inputs are generated in sizes of powers of 2
 */
public class CaseTester {
    final int NUM_OF_TRIES_TO_AVERAGE = 10;
    //set to 0 to avoid long waits for stooge sort
    final int STOOGE_SORT_TRIES_TO_AVERAGE = 1;
    final int[] EXPERIMENT_SPLITS = {16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192};

    //below input can be used if the results of bitonic sort going to be ignored
//    final int[] EXPERIMENT_SPLITS = {16, 32, 64,100, 500, 1000, 2000, 2500, 3000, 3500, 4000, 5000, 6000, 7000, 8000};

    RandomFactory randomizer;
    CrazyScientist rick;

    CaseTester() {
        randomizer = new RandomFactory();
        rick = new CrazyScientist(EXPERIMENT_SPLITS.length);
    }

    public void testBestCases() {
        // setExperimentSize is being used as a reset button while running all cases at the same time
        rick.setExperimentSize(EXPERIMENT_SPLITS);

        // generate array
        //for each input we'll try we repeat the experiment with the same list to each algorithm and record the average
        for (int sizeIndex = 0; sizeIndex < EXPERIMENT_SPLITS.length; sizeIndex++) {
            long combAverage = 0;
            long gnomeAverage = 0;
            long shakerAverage = 0;
            long stoogeAverage = 0;
            long bitonicAverage = 0;
            int arraySize = EXPERIMENT_SPLITS[sizeIndex];

            //to limit the long search of stooge sort we stop checking after $STOOGE_SORT_TRIES_TO_AVERAGE times
            int stoogeCounter = 0;
            for (int i = 0; i < NUM_OF_TRIES_TO_AVERAGE; i++) {

                TestRat[] bestArray = bestCaseGenerator(arraySize);
                CombSort combSort = new CombSort(bestArray.clone());
                GnomeSort gnomeSort = new GnomeSort(bestArray.clone());
                ShakerSort shakerSort = new ShakerSort(bestArray.clone());
                StoogeSort stoogeSort = new StoogeSort(bestArray.clone());
                BitonicSort bitonicSort = new BitonicSort(bestArray.clone());

                combAverage += caseTest(combSort) / NUM_OF_TRIES_TO_AVERAGE;
                gnomeAverage += caseTest(gnomeSort) / NUM_OF_TRIES_TO_AVERAGE;
                shakerAverage += caseTest(shakerSort) / NUM_OF_TRIES_TO_AVERAGE;
                if (stoogeCounter < STOOGE_SORT_TRIES_TO_AVERAGE) {
                    stoogeCounter++;
                    stoogeAverage += caseTest(stoogeSort) / STOOGE_SORT_TRIES_TO_AVERAGE;
                }
                bitonicAverage += caseTest(bitonicSort) / NUM_OF_TRIES_TO_AVERAGE;


            }
            rick.addDataCombSort(combAverage);
            rick.addDataGnomeSort(gnomeAverage);
            rick.addDataShakerSort(shakerAverage);
            rick.addDataStoogeSort(stoogeAverage);
            rick.addDataBitonicSort(bitonicAverage);
        }
        System.out.println("========== BEST CASE ANALYSIS WITH ORDERED LISTS ==========");
        rick.getReports();
    }

    public void testWorstCases() {
        // setExperimentSize is being used as a reset button while running all cases at the same time
        rick.setExperimentSize(EXPERIMENT_SPLITS);

        // generate array
        //for each input we'll try we repeat the experiment with the same list to each algorithm and record the average
        for (int arraySize : EXPERIMENT_SPLITS) {
            long combAverage = 0;
            long gnomeAverage = 0;
            long shakerAverage = 0;
            long stoogeAverage = 0;
            long bitonicAverage = 0;
            //to limit the long search of stooge sort we stop checking after $STOOGE_SORT_TRIES_TO_AVERAGE times
            int stoogeCounter = 0;
            for (int i = 0; i < NUM_OF_TRIES_TO_AVERAGE; i++) {

                TestRat[] worstArray = worstCaseGenerator(arraySize);
                CombSort combSort = new CombSort(worstArray.clone());
                GnomeSort gnomeSort = new GnomeSort(worstArray.clone());
                ShakerSort shakerSort = new ShakerSort(worstArray.clone());
                StoogeSort stoogeSort = new StoogeSort(worstArray.clone());
                BitonicSort bitonicSort = new BitonicSort(worstArray.clone());

                combAverage += caseTest(combSort) / NUM_OF_TRIES_TO_AVERAGE;
                gnomeAverage += caseTest(gnomeSort) / NUM_OF_TRIES_TO_AVERAGE;
                shakerAverage += caseTest(shakerSort) / NUM_OF_TRIES_TO_AVERAGE;
                if (stoogeCounter < STOOGE_SORT_TRIES_TO_AVERAGE) {
                    stoogeCounter++;
                    stoogeAverage += caseTest(stoogeSort) / STOOGE_SORT_TRIES_TO_AVERAGE;
                }
                bitonicAverage += caseTest(bitonicSort) / NUM_OF_TRIES_TO_AVERAGE;


            }
            rick.addDataCombSort(combAverage);
            rick.addDataGnomeSort(gnomeAverage);
            rick.addDataShakerSort(shakerAverage);
            rick.addDataStoogeSort(stoogeAverage);
            rick.addDataBitonicSort(bitonicAverage);
        }
        System.out.println("========== WORST CASE ANALYSIS WITH REVERSE ORDERED LISTS ==========");
        rick.getReports();

    }

    public void testAverageCases() {
        // setExperimentSize is being used as a reset button while running all cases at the same time
        rick.setExperimentSize(EXPERIMENT_SPLITS);

        // generate array
        //for each input we'll try we repeat the experiment with the same list to each algorithm and record the average
        for (int sizeIndex = 0; sizeIndex < EXPERIMENT_SPLITS.length; sizeIndex++) {
            long combAverage = 0;
            long gnomeAverage = 0;
            long shakerAverage = 0;
            long stoogeAverage = 0;
            long bitonicAverage = 0;
            int arraySize = EXPERIMENT_SPLITS[sizeIndex];

            //to limit the long search of stooge sort we stop checking after $STOOGE_SORT_TRIES_TO_AVERAGE times
            int stoogeCounter = 0;
            for (int i = 0; i < NUM_OF_TRIES_TO_AVERAGE; i++) {

                TestRat[] averageArray = averageCaseGenerator(arraySize);
                CombSort combSort = new CombSort(averageArray.clone());
                GnomeSort gnomeSort = new GnomeSort(averageArray.clone());
                ShakerSort shakerSort = new ShakerSort(averageArray.clone());
                StoogeSort stoogeSort = new StoogeSort(averageArray.clone());
                BitonicSort bitonicSort = new BitonicSort(averageArray.clone());

                combAverage += caseTest(combSort) / NUM_OF_TRIES_TO_AVERAGE;
                gnomeAverage += caseTest(gnomeSort) / NUM_OF_TRIES_TO_AVERAGE;
                shakerAverage += caseTest(shakerSort) / NUM_OF_TRIES_TO_AVERAGE;
                if (stoogeCounter < STOOGE_SORT_TRIES_TO_AVERAGE) {
                    stoogeCounter++;
                    stoogeAverage += caseTest(stoogeSort) / STOOGE_SORT_TRIES_TO_AVERAGE;
                }
                bitonicAverage += caseTest(bitonicSort) / NUM_OF_TRIES_TO_AVERAGE;


            }
            rick.addDataCombSort(combAverage);
            rick.addDataGnomeSort(gnomeAverage);
            rick.addDataShakerSort(shakerAverage);
            rick.addDataStoogeSort(stoogeAverage);
            rick.addDataBitonicSort(bitonicAverage);
        }
        System.out.println("========== AVERAGE CASE ANALYSIS WITH UNORDERED LISTS ==========");
        rick.getReports();

    }

    // best case for each algorithm is a sorted array
    private TestRat[] bestCaseGenerator(int numberOfElements) {
        int[] idArray = randomizer.randomize(numberOfElements);
        List<Integer> experimentArray = Arrays.asList(randomizer.randomizeObject(numberOfElements));
        Collections.sort(experimentArray);

        TestRat[] ratArray = new TestRat[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            TestRat newRat = new TestRat(idArray[i], experimentArray.get(i));
            ratArray[i] = newRat;
        }
        return ratArray;
    }

    // worst case for each algorithm is a reversed sorted array
    // some exceptions are ignored for the sake of simplicity
    private TestRat[] worstCaseGenerator(int numberOfElements) {
        int[] idArray = randomizer.randomize(numberOfElements);
        List<Integer> experimentArray = Arrays.asList(randomizer.randomizeObject(numberOfElements));
        experimentArray.sort(Collections.reverseOrder());

        TestRat[] ratArray = new TestRat[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            TestRat newRat = new TestRat(idArray[i], experimentArray.get(i));
            ratArray[i] = newRat;
        }
        return ratArray;
    }

    // average case for each algorithm is a randomly generated array
    public TestRat[] averageCaseGenerator(int numberOfElements) {
        int[] idArray = randomizer.randomize(numberOfElements);
        List<Integer> experimentArray = Arrays.asList(randomizer.randomizeObject(numberOfElements));

        TestRat[] ratArray = new TestRat[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            TestRat newRat = new TestRat(idArray[i], experimentArray.get(i));
            ratArray[i] = newRat;
        }
        return ratArray;
    }

    /**
     * encapsulated way to test cases multiple times and average them
     *
     * @param sortingAlgorithm is the generic class for all sorting algorithms
     * @param <T>              is a child of SortingAlgorithm
     * @return The execution time
     */
    private <T extends SortingAlgorithm> long caseTest(T sortingAlgorithm) {

        TimeDecorator timeSort = new TimeDecorator(sortingAlgorithm);
        long executionTime = timeSort.printExecutionTime();
        return executionTime;
    }

//    private void combSortTest(Comparable[] array){}

}
