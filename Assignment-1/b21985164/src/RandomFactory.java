import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RandomFactory {
    /**
     * generate a randomized array in range of input size
     * this method used to generate id numbers for rats, that's why negative numbers are excluded
     * @param inputSize is size of the array
     * @return the random array
     */
    public int[] randomize(int inputSize){
        int[] list = new int[inputSize];
        for(int i = 0; i < inputSize; i++){
            // generate numbers within absolute range of input size to increase the chance to get distinct elements
            list[i] = ThreadLocalRandom.current().nextInt(0, inputSize + 1);
        }
//        System.out.println(Arrays.toString(list));
        return list;
    }

    /**
     * object version of randomize method for the sake of sorting only integer arraylists instead of objects
     * @param inputSize
     * @return the random array
     */
    public Integer[] randomizeObject(int inputSize){
        Integer[] list = new Integer[inputSize];
        for(int i = 0; i < inputSize; i++){
            // generate numbers within absolute range of input size to increase the chance to get distinct elements
            list[i] = ThreadLocalRandom.current().nextInt(-inputSize, inputSize + 1);
        }
        // System.out.println(Arrays.toString(list));
        return list;
    }
}
