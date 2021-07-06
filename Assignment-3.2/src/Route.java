import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Class for Connecting the Paths
 */
public class Route {
    private ArrayList<Flight> transferRoute;
    private ArrayList<Integer> transportationTimes;
    private int duration;
    private int price;

    Route() {
        transferRoute = new ArrayList<>();
        transportationTimes = new ArrayList<>();
        duration = 0;
        price = 0;
    }

    public void addToRoute(Flight flight) {
        int transferTime = 0;
        if(transferRoute.size() > 0){
            transferTime = (int)ChronoUnit.MINUTES.between(transferRoute.get(transferRoute.size() - 1).getArrivalDate(), flight.getDepartureDate());
        }
        transportationTimes.add(transferTime);
        duration += flight.getDurationMinutes() + transferTime;
        price += flight.getPrice();
        transferRoute.add(flight);
    }

    public void removeLast() {
        if (transferRoute.size() > 0) {
            Flight lastFlight = transferRoute.get(transferRoute.size() - 1);
            duration -= lastFlight.getDurationMinutes();
            duration -= transportationTimes.get(transferRoute.size()-1);
            transportationTimes.remove(transferRoute.size()-1);
            price -= lastFlight.getPrice();
            transferRoute.remove(lastFlight);

        }
    }

    public Route getCopy(){
        Route copyRoute = new Route();
        ArrayList<Flight> copyList = (ArrayList<Flight>) this.transferRoute.clone();
        copyRoute.setTransferRoute(copyList);
        copyRoute.setDuration(this.duration);
        copyRoute.setPrice(this.price);
        return copyRoute;
    }


    @Override
    public String toString() {
        int hour = duration / 60;
        int minute = duration % 60;
        String durationString = String.valueOf(hour < 10? ("0" + hour): hour);
        durationString += ":" + (minute < 10? ("0" + minute): minute);

        StringBuilder formatted = new StringBuilder(transferRoute.size() > 0 ? transferRoute.get(0).toString() : "");
        for (int i = 1; i < transferRoute.size(); i++) {
            formatted.append("||").append(transferRoute.get(i).toString());
        }
        formatted.append("\t").append(durationString);
        formatted.append("/").append(price).append("\n");

        return formatted.toString();
    }

    public LocalDateTime getArrivalDate(){
        if (transferRoute.size() > 0) {
            Flight lastFlight = transferRoute.get(transferRoute.size() - 1);
            return lastFlight.getArrivalDate();
        }
        return null;
    }

    public ArrayList<Flight> getTransferRoute() {
        return transferRoute;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public void setTransferRoute(ArrayList<Flight> transferRoute) {
        this.transferRoute = transferRoute;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
