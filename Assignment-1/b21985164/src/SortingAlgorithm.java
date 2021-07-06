public abstract class SortingAlgorithm<T extends Comparable<T>>{
    SortingAlgorithm(T[] input){
        this.input = input;
        this.inputSize = input.length;
    }
    public String name;

    public int inputSize;
    public T[] input;

    public abstract T[] sort();

    // swap method is commonly used in most sorting algorithms so we implement it here
    public void swap(T[] array, int first, int second){
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public boolean isSorted(){
        for(int i = 0; i< inputSize -1; i++){
            if(input[i].compareTo(input[i + 1]) > 0){
                return false;
            }
        }
        return true;
    }
}
