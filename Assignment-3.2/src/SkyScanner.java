import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * File Reader Class
 */
public class SkyScanner {
    private String airportList;
    private String flightList;
    private String commandList;

    SkyScanner(String airportList, String flightList, String commandList){
        this.airportList = airportList;
        this.flightList = flightList;
        this. commandList = commandList;
    }

    /**
     * Read the airport list and create a hashmap to point where the airport aliases belong
     * @return the hashmap formatted as Alias -> City
     */
    public HashMap<String, String> readAirportList() {
        HashMap<String, String> airports = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(airportList));
            while(scanner.hasNextLine()){
                String[] line = scanner.nextLine().split("\t");
                for(int i = 1; i < line.length; i++){
                    airports.put(line[i], line[0]);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return airports;
    }

    /**
     * @return the flight list
     */
    public HashMap<String, ArrayList<Flight>> readFlightList(){
        HashMap<String, ArrayList<Flight>> database = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(flightList));
            while(scanner.hasNextLine()){
                Flight flight = new Flight(scanner.nextLine());
                addFlight(flight, database);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return database;
    }

    public ArrayList<String> readCommands(){
        ArrayList<String> commands = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(commandList));
            while(scanner.hasNextLine()){
                commands.add(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return commands;
    }

    private void addFlight(Flight flight, HashMap<String, ArrayList<Flight>> database){
        String key = flight.getDeparture();
        ArrayList<Flight> value = database.get(key);
        if(value == null){
            value = new ArrayList<Flight>();
            value.add(flight);
            database.put(key, value);
        }else{
            if(!value.contains(flight)) value.add(flight);
        }
    }

}
