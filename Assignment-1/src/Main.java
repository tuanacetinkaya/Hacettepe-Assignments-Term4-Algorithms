public class Main {

    public static void main(String[] args) {
        //This section tests each case and prints seperate reports for each one.
        //Check the CaseTester class to reassign the final parameters to minimize execution time.
        CaseTester sortTester = new CaseTester();
        sortTester.testAverageCases();
        sortTester.testBestCases();
        sortTester.testWorstCases();


        // This section is to test stability of each algorithm
        // Size of the array to check stability is given as a parameter.
        StabilityTester stabilityTester = new StabilityTester();
        stabilityTester.testStability(16);
    }

}