import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StabilityTester {
    CaseTester arrayGenerator;

    StabilityTester() {
        arrayGenerator = new CaseTester();
    }

    /**
     * initializes each algorithm and tests their stability with the same lists.
     * @param numberOfElements
     */
    public void testStability(int numberOfElements){
        TestRat[] testArray = arrayGenerator.averageCaseGenerator(numberOfElements);
        CombSort combSort = new CombSort(testArray.clone());
        GnomeSort gnomeSort = new GnomeSort(testArray.clone());
        ShakerSort shakerSort = new ShakerSort(testArray.clone());
        StoogeSort stoogeSort = new StoogeSort(testArray.clone());
        BitonicSort bitonicSort = new BitonicSort(testArray.clone());
        TestRat[] sortedListComb = combSort.sort();
        TestRat[] sortedListGnome = gnomeSort.sort();
        TestRat[] sortedListShaker = shakerSort.sort();
        TestRat[] sortedListStooge = stoogeSort.sort();
        TestRat[] sortedListBitonic = bitonicSort.sort();

        System.out.println("=================== STABILITY CHECKS ===================");
        System.out.println("\t\t\t========= COMB SORT =========");
        System.out.println(checkStability(testArray,sortedListComb));
        System.out.println("\t\t\t========= GNOME SORT =========");
        System.out.println(checkStability(testArray,sortedListGnome));
        System.out.println("\t\t\t========= SHAKER SORT =========");
        System.out.println(checkStability(testArray,sortedListShaker));
        System.out.println("\t\t\t========= STOOGE SORT =========");
        System.out.println(checkStability(testArray,sortedListStooge));
        System.out.println("\t\t\t========= BITONIC SORT =========");
        System.out.println(checkStability(testArray,sortedListBitonic));

    }

    private void testAlgorithms(){

    }

    /**
     * java.Collections.sort is a reasonable stable sort since it's using merge sort,
     * so we can use that in advantage to check other sorting algorithms.
     *
     * @param original is the unsorted array initially created
     * @param sorted   is the sorted version of the array
     * @return true if the algorithm sorted in a stable matter
     */
    public boolean checkStability(TestRat[] original, TestRat[] sorted) {
        boolean isStable = true;
        List<TestRat> stableArray = Arrays.asList(original);
        Collections.sort(stableArray);
        System.out.println("Sorted Array for That Algorithm is:");
        for (int i = 0; i < stableArray.size(); i++) {
            if (sorted[i].getRatID() != stableArray.get(i).getRatID()){
                System.out.println(String.format("%s  <--- Instability! Stable output had to be: %s ",
                        sorted[i].toString(),stableArray.get(i).toString()));
                isStable = false;
            }else {
                System.out.println(sorted[i]);
            }
        }
        return isStable;
    }
}
