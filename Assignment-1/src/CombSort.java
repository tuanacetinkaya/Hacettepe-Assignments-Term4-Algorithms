public class CombSort extends SortingAlgorithm<TestRat> {
    CombSort(TestRat[] input) {
        super(input);
        this.name = "Comb Sort";
    }

    @Override
    public TestRat[] sort() {
        // gives a terminal output about the sorting algorithm in process at that moment
        System.out.printf("%s is now sorting an array of %d elements...\r", name,inputSize);

        int gap = input.length;
        double shrink = 1.3;
        boolean sorted = false;

        while (!sorted) {
            // gap = n/1.3
            gap = (int) Math.floor(gap / shrink);
            if (gap <= 1) {
                gap = 1;
                sorted = true;
            }
            int i = 0;
            while (i + gap < input.length) {
                if(input[i].compareTo(input[i+gap]) > 0){
                    swap(input, i, i+gap);
                    sorted = false; // if this doesn't happen the list is already sorted
                }
                i += 1;
            }
        }
        return input;
    }
}
