public class StoogeSort extends SortingAlgorithm<TestRat> {
    StoogeSort(TestRat[] input) {
        super(input);
        this.name = "Stooge Sort";
    }

    /**
     * STOOGE SORT
     *  sorts 1/3 of the list to make sure large items reach throygh the last third
     *  then sorts the last 1/3 of the list that places largest elements outside the first third in sorted order
     *  lastly sorts the first third again to place the small elements coming from the last third
     *  repeats the process in a recursive matter when the sublists has 3 or more elements
     */
    @Override
    public TestRat[] sort() {
        // gives a terminal output about the sorting algorithm in process at that moment
        System.out.print(String.format("%s is now sorting an array of %d elements...\r", name,inputSize));

        stoogeSort(input, 0, inputSize-1);
        return input;
    }

    private void stoogeSort(TestRat[] L, int i, int j) {
        // if the leftmost element is larger than the rightmost element
        if (L[i].compareTo(L[j]) > 0) {
            // swap the leftmost element and the rightmost element
            swap(L, i, j);
        }
        // if there are at least 3 elements in the array
        if (j - i + 1 > 2) {
            int t = (int)Math.floor((j - i + 1) / 3);
            stoogeSort(L,i,j-t);
            stoogeSort(L,i+t,j);
            stoogeSort(L,i,j-t);
            return;
        }
    }
}