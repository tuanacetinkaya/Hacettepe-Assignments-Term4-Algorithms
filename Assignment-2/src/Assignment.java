import java.text.DecimalFormat;
import java.util.Locale;

public class Assignment implements Comparable {
    private String name;
    private String start;
    private int duration;
    private int importance;
    private boolean maellard;

    /*
        Getter methods
     */
    public String getName() {
        return name;
    }


    public String getStartTime() {
        return start;
    }

    public int getDuration() {
        return duration;
    }

    public int getImportance() {
        return importance;
    }

    public boolean isMaellard() {
        return maellard;
    }

    /**
     * Finish time should be calculated here
     *
     * @return calculated finish time as String
     */
    public String getFinishTime() {
        int hourInt = Integer.parseInt(getStartTime().split(":")[0]) + getDuration();
        String hour = String.format(hourInt <= 9 ? "0%s" : "%s", hourInt);
        return String.format("%s:%s", hour, getStartTime().split(":")[1]);

    }

    /**
     * Weight calculation should be performed here
     *
     * @return calculated weight
     */
    public double getWeight() {
        return duration > 0 ? ((double) importance * (maellard ? 1001 : 1) / duration) : 0;
    }

    /**
     * This method is needed to use {@link java.util.Arrays#sort(Object[])} ()}, which sorts the given array easily
     *
     * @param o Object to compare to
     * @return If self > object, return > 0 (e.g. 1)
     * If self == object, return 0
     * If self < object, return < 0 (e.g. -1)
     */
    @Override
    public int compareTo(Object o) {
        Assignment other = (Assignment) o;
        return getNumericFinish() - other.getNumericFinish();
    }

    /**
     * @return Should return a string in the following form:
     * Assignment{name='Refill vending machines', start='12:00', duration=1, importance=45, maellard=false, finish='13:00', weight=45.0}
     */
    @Override
    public String toString() {
        DecimalFormat decimal = new DecimalFormat("#.#");
        double weightFormatted = Double.parseDouble(decimal.format(getWeight()));
        return String.format("Assignment{name='%s', start='%s', duration=%d, importance=%d, maellard=%b, finish='%s', weight=%.1f}",
                name, start, duration, importance, maellard, getFinishTime(), weightFormatted);
    }

    /**
     * converts the finish time into integer for the sake of simplicity
     *
     * @return the finish time in minute type
     */
    public int getNumericFinish() {
        return Integer.parseInt(getFinishTime().split(":")[0]) * 60 + Integer.parseInt(getFinishTime().split(":")[1]);
    }
}
