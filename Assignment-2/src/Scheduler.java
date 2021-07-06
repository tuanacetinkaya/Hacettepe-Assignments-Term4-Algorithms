import java.util.ArrayList;
import java.util.Collections;

public class Scheduler {
    private Assignment[] assignmentArray;
    private Integer[] C;
    private Double[] max;
    private ArrayList<Assignment> solutionDynamic;
    private ArrayList<Assignment> solutionGreedy;

    /**
     * @throws IllegalArgumentException when the given array is empty
     */
    public Scheduler(Assignment[] assignmentArray) throws IllegalArgumentException {

        if (assignmentArray.length == 0) throw new IllegalArgumentException("Empty Array");
        this.assignmentArray = assignmentArray;
        this.C = new Integer[assignmentArray.length];
        this.max = new Double[assignmentArray.length];
        this.solutionDynamic = new ArrayList<>();
        this.solutionGreedy = new ArrayList<>();

        // fill the array C
        calculateC();
        // calculate maximum total weights using the values of C
        calculateMax(assignmentArray.length - 1);
    }

    /**
     * @param index of the {@link Assignment}
     * @return Returns the index of the last compatible {@link Assignment},
     * returns -1 if there are no compatible assignments
     */
    private int binarySearch(int index) {
        int forbidden = index;
        // numeric starting time of target assignment
        int limit = assignmentArray[index].getNumericFinish() - assignmentArray[index].getDuration() * 60; //13

        int left = 0; //lower limit for binary search
        int mid = 0;
        //there's no point of looking if there's nothing where you're looking for
        // it's like looking for meaning in life.
        if (assignmentArray.length == 0) return -1;

        //if the very first element has bigger finish time than your start time, no elements are compatible
        if (limit < assignmentArray[0].getNumericFinish()) return -1;

        //vanilla binary search here, except we return when we find an equal in case of a total match from the right
        while (left <= index) {
            mid = (left + index) / 2;
            // update the upper limit when the mid element is larger
            if (limit < assignmentArray[mid].getNumericFinish()) index = mid - 1;
                //update the lower limit when the mid element is smaller or equal
            else {

                if (limit >= assignmentArray[mid + 1].getNumericFinish()) {
                    left = mid + 1;
                } else break; //then the value is greater or equal to last item or
            }
        }
        return forbidden != mid ? mid : -1;

    }


    /**
     * {@link #C} contains the indexes of first compatible tasks before each task
     */
    private void calculateC() {
        for (int i = 0; i < C.length; i++) {
            C[i] = binarySearch(i);
        }
//        System.out.println(Arrays.toString(C));
    }


    /**
     * we reverse the list to get chronological order
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleDynamic() {
        //find a solution from calculated maximum total weights
        findSolutionDynamic(assignmentArray.length - 1);

        //reverse the list to get chronological order
        Collections.reverse(solutionDynamic);
        return solutionDynamic;
    }

    /**
     * {@link #solutionDynamic} must be filled after calling this method
     */
    private void findSolutionDynamic(int i) {
        // we ignore the case if i = -1
        if (i > -1) {
            // now we need to see if counting the task is better or not
            //when comparing we check if the i > 0 to avoid IndexOutOfBoundsException
            System.out.printf("findSolutionDynamic(%d)%n", i);
            if (i == 0 || max[i] > max[i - 1]) {
                //that means having the task is better
                System.out.printf("Adding %s to the dynamic schedule%n", assignmentArray[i]);
                solutionDynamic.add(assignmentArray[i]);
                //and we continue from that point with the best compatible task we found earlier
                findSolutionDynamic(C[i]);
            } else {
                // if it's not better to have that task we move on and find a solution excluding that task
                findSolutionDynamic(i - 1);
            }
        }

    }

    /**
     * {@link #max} must be filled after calling this method
     */
    private Double calculateMax(int i) {
        // returns -1 when i = -1
        if (i <= -1) {
            return 0.0;
        }
        // max of the first task is always it's weight since it's redundant to calculate the max of -1's
        //it's also useful to set the 'Zero' status
        if (i == 0) {
            max[0] = assignmentArray[0].getWeight();
            System.out.printf("calculateMax(%d): Zero%n", i);
        }
        // if the data is not present in max array, we calculate and save the result
        else if (max[i] == null) {
            System.out.printf("calculateMax(%d): Prepare%n", i);
            max[i] = Math.max(assignmentArray[i].getWeight() + calculateMax(C[i]), calculateMax(i - 1));
        }
        // if it's already there we'll just return the result and set the status to Present
        else {
            System.out.printf("calculateMax(%d): Present%n", i);
        }
        return max[i];

    }

    /**
     * {@link #solutionGreedy} must be filled after calling this method
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleGreedy() {
        // assigned to max integer so that the first task is always selected
        int recentlyFinished = -1;
        for (Assignment task : assignmentArray) {
            //new task's starting time has to be greater than recently finished task's finish time
            if (task.getNumericFinish() - (task.getDuration() * 60) >= recentlyFinished) {
                System.out.printf("Adding %s to the greedy schedule%n", task);
                solutionGreedy.add(task);
                recentlyFinished = task.getNumericFinish();
            }
        }
        return solutionGreedy;
    }
}
