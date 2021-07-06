import java.util.ArrayList;

/**
 * A class to document experiments of rats
 * As crazy as it gets
 */
public class CrazyScientist {
    private int[] experimentSizes;
    private ArrayList<Long> combSortAverages;
    private ArrayList<Long> gnomeSortAverages;
    private ArrayList<Long> shakerSortAverages;
    private ArrayList<Long> stoogeSortAverages;
    private ArrayList<Long> bitonicSortAverages;

    CrazyScientist(int experimentNumber) {
        resetLists(experimentNumber);
    }

    private void resetLists(int experimentNumber){
        this.combSortAverages = new ArrayList<Long>(experimentNumber);
        this.gnomeSortAverages = new ArrayList<Long>(experimentNumber);
        this.shakerSortAverages = new ArrayList<Long>(experimentNumber);
        this.stoogeSortAverages = new ArrayList<Long>(experimentNumber);
        this.bitonicSortAverages = new ArrayList<Long>(experimentNumber);
    }

    public void setExperimentSize(int[] dataSet) {
        this.experimentSizes = dataSet;
        resetLists(dataSet.length);
    }

    public void addDataCombSort(long data) {
        combSortAverages.add(data);
    }

    public void addDataGnomeSort(long data) {
        gnomeSortAverages.add(data);
    }

    public void addDataShakerSort(long data) {
        shakerSortAverages.add(data);
    }

    public void addDataStoogeSort(long data) {
        stoogeSortAverages.add(data);
    }

    public void addDataBitonicSort(long data) {
        bitonicSortAverages.add(data);
    }

    public void getReports() {
        System.out.println("================== REPORTS ==================");
        System.out.println("========= COMB SORT =========");
        getReportComb();
        System.out.println("========= GNOME SORT =========");
        getReportGnome();
        System.out.println("========= SHAKER SORT =========");
        getReportShaker();
        System.out.println("========= STOOGE SORT =========");
        getReportStooge();
        System.out.println("========= BITONIC SORT =========");
        getReportBitonic();
        System.out.println("========= REPORTS END =========");

    }

    public ArrayList<Long> getReportComb() {
        System.out.println("Time averages for Comb Sort by inputs as follows:\n");
        for (int i = 0; i < experimentSizes.length; i++) {
            System.out.println(String.format("%d : %d", experimentSizes[i], combSortAverages.get(i)));
        }
        return combSortAverages;
    }

    public ArrayList<Long> getReportGnome() {
        System.out.println("Time averages for Gnome Sort by inputs as follows:\n");
        for (int i = 0; i < experimentSizes.length; i++) {
            System.out.println(String.format("%d : %d", experimentSizes[i], gnomeSortAverages.get(i)));
        }
        return gnomeSortAverages;
    }

    public ArrayList<Long> getReportShaker() {
        System.out.println("Time averages for Shaker Sort by inputs as follows:\n");
        for (int i = 0; i < experimentSizes.length; i++) {
            System.out.println(String.format("%d : %d", experimentSizes[i], shakerSortAverages.get(i)));
        }
        return shakerSortAverages;
    }

    public ArrayList<Long> getReportStooge() {
        System.out.println("Time averages for Stooge Sort by inputs as follows:\n");
        for (int i = 0; i < experimentSizes.length; i++) {
            System.out.println(String.format("%d : %d", experimentSizes[i], stoogeSortAverages.get(i)));
        }
        return stoogeSortAverages;
    }

    public ArrayList<Long> getReportBitonic() {
        System.out.println("Time averages for Bitonic Sort by inputs as follows:\n");
        for (int i = 0; i < experimentSizes.length; i++) {
            System.out.println(String.format("%d : %d", experimentSizes[i], bitonicSortAverages.get(i)));
        }
        return bitonicSortAverages;
    }


}

