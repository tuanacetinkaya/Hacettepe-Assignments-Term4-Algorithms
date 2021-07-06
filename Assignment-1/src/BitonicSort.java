public class BitonicSort extends SortingAlgorithm<TestRat> {
    private final int UP = 1;
    private final int DOWN = 0;

    BitonicSort(TestRat[] input) {
        super(input);
        this.name = "Bitonic Sort";
    }


    /** BITONIC SORT
     * o(N*LOG^2(N)) complexity
     */
    @Override
    public TestRat[] sort() {
        // gives a terminal output about the sorting algorithm in process at that moment
        System.out.printf("%s is now sorting an array of %d elements...\r", name,inputSize);


        if (inputSize % 2 != 0) {
            System.out.println("Your Input Size Has to be an even number! \nBitonic Sort Skipped");
            return null;
        }
        //if the previous if case did not work input size is an even number,
        // so we can check if it's a power of 2
        for (int i = 0; i <= inputSize/2; i++) {

            if (Math.pow(2, i) == inputSize) {
                //caller of bitonicSort for sorting an array of length N in ASCENDING order
                bitonicSort(input, 0, inputSize, UP);
                return input;
            }
            else if(Math.pow(2, i) > inputSize){
                System.out.println("Your Input Size Has to be a power of 2 in order to Bitonic Sort to work properly!" +
                        "\nBitonic Sort Skipped");
                return null;
            }
        }
        System.out.println("Your Input Size Has to be a power of 2 in order to Bitonic Sort to work properly!" +
                "\nBitonic Sort Skipped");
        return null;
    }


    // dire is a parameter to select if the list will be in increasing or decreasing order
    public void compAndSwap(TestRat[] a, int i, int j, int dire) {
        if ((dire == UP && a[i].compareTo(a[j]) > 0) || (dire == DOWN && a[i].compareTo(a[j]) < 0)) {
            swap(a, i, j);
        }
    }

    //the sequence to be sorted starts at index position low
    // the parameter cnt is the number of elements to be sorted
    public void bitonicMerge(TestRat[] a, int low, int cnt, int dire) {
        if (cnt > 1) {
            int k = cnt / 2;
            for (int i = low; i < low + k; i++) {
                compAndSwap(a, i, i + k, dire);
            }
            bitonicMerge(a, low, k, dire);
            bitonicMerge(a, low + k, k, dire);
        }
    }

    public void bitonicSort(TestRat[] a, int low, int cnt, int dire) {
        if (cnt > 1) {
            int k = cnt / 2;
            bitonicSort(a, low, k, 1);
            bitonicSort(a, low + k, k, 0);
            bitonicMerge(a, low, cnt, dire);
        }
    }
}
