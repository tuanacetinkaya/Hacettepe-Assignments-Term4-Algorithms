public class GnomeSort extends SortingAlgorithm<TestRat>{

    GnomeSort(TestRat[] input) {
        super(input);
        this.name = "Gnome Sort";
    }

    /** GNOME SORT
     * when we find something unordered we insert it back to it's place by swapping with the previous
     *   until we see something smaller than that
     *   when we do we continue the same process
     *  so when we encounter the smallest item we swap back till the first element in list
     */
    @Override
    public TestRat[] sort() {
        // gives a terminal output about the sorting algorithm in process at that moment
        System.out.printf("%s is now sorting an array of %d elements...\r", name,inputSize);

        int pos = 0;
        while (pos < inputSize){
            // if two compared values are in order or we are at the beginning of the array,
            // we increment the position to keep moving
            if(pos == 0 || input[pos].compareTo(input[pos-1]) >= 0){
                pos += 1;
            }
            else{
                swap(input, pos, pos-1);
                pos -= 1;
            }
        }
        return input;
    }
}
