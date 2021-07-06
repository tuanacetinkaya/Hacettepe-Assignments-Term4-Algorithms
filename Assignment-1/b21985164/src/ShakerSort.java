public class ShakerSort extends SortingAlgorithm<TestRat> {


    ShakerSort(TestRat[] input) {
        super(input);
        this.name = "Shaker Sort";
    }

    /**
     * SHAKER SORT
     *  checks if the list is in order, if not swaps each unordered pair until the end of the list
     *  if the list was swapped once that means it might be out of order so to check that it does the operation again.
     *  if it had to swap again while loop repeats until it traverse the list once without swapping any items.
     *  hence the list is sorted now.
     *  COMPLEXITY:
     *         Best Case: O(N) => traverse a sorted list once and breaks out of loop
     *         Worst Case: O(N^2) => where we need to swap each couple N times in a list of decreasing elements
     */
    @Override
    public TestRat[] sort() {
        // gives a terminal output about the sorting algorithm in process at that moment
        System.out.printf("%s is now sorting an array of %d elements...\r", name,inputSize);

        boolean swapped = false;
        do {
            //! N times
            for (int i = 0; i <= inputSize - 2; i++) {
                if (input[i].compareTo(input[i + 1]) > 0) { // test whether the two elements are in the wrong order
                    swap(input, i, i + 1); // let the two elements change places
                    swapped = true;
                }
            }
            if (!swapped) {
                // we can exit the outer loop here if no swaps occured
                break;
            }
            swapped = false;
            //same operation again:
            for (int i = 0; i <= inputSize - 2; i++) {
                if (input[i].compareTo(input[i + 1]) > 0) { // test whether the two elements are in the wrong order
                    swap(input, i, i + 1); // let the two elements change places
                    swapped = true;
                }
            }
        } while (swapped);

        return input;
    }
}
