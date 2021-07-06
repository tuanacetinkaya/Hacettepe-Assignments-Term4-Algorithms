import java.time.LocalDateTime;

public class Flight {
    private String id;
    private String departure;
    private String arrival;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String duration;
    private int durationMinutes;
    private int price;

    public Flight(String line) {
        String[] parsed = line.split("\t");
        this.id = parsed[0];
        this.departure = parsed[1].split("->")[0];
        this.arrival = parsed[1].split("->")[1];
        // date[0] = dd/MM/yyyy
        // date[1] = HH:mm
        // date[2] = EEE
        String[] date = parsed[2].split(" ");
        this.departureDate = LocalDateTime.parse(date[0].split("/")[2]+"-"+date[0].split("/")[1]+"-"+date[0].split("/")[0]+"T"+date[1]+":00");
        this.duration = parsed[3];
        this.durationMinutes = Integer.parseInt(duration.split(":")[1]) + (60 * Integer.parseInt(duration.split(":")[0]));
        this.price = Integer.parseInt(parsed[4]);

        //calculate the arrival
        this.arrivalDate = this.departureDate.plusHours(Integer.parseInt(this.duration.split(":")[0]));
        this.arrivalDate = this.arrivalDate.plusMinutes(Integer.parseInt(this.duration.split(":")[1]));
    }

    public String getId() {
        return id;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public String getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s->%s", id, departure, arrival);
    }
}
