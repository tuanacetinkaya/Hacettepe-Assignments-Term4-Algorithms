public class TestRat implements Comparable<TestRat> {
    private int ratID;
    private int ratExperimentNumber;

    TestRat(int ratAge, int ratExperimentNumber){
        this.ratID = ratAge;
        this.ratExperimentNumber = ratExperimentNumber;
    }
    TestRat first;
    TestRat second;


    @Override
    public int compareTo(TestRat o) {
        if(this.ratExperimentNumber > o.ratExperimentNumber){
            return 1;
        }
        if(this.ratExperimentNumber < o.ratExperimentNumber){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString(){
        return (String.format("%d\t: Number of Experiments for rat ID#%d",ratExperimentNumber,ratID));
    }

    public int getRatID() {
        return ratID;
    }

    public int getRatExperimentNumber() {
        return ratExperimentNumber;
    }
}
