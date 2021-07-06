import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        SkyScanner bookScanner = new SkyScanner(args[0], args[1], args[2]);
        HashMap<String,String > airports = bookScanner.readAirportList();
        HashMap<String, ArrayList<Flight>> flightDatabase = bookScanner.readFlightList();
        ArrayList<String> commands = bookScanner.readCommands();

        ATCTower tower = new ATCTower(airports, flightDatabase, commands);
        tower.performCommands();

    }
}
