import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class ATCTower {
    BufferedWriter writer;

    HashMap<String, String> airports;
    HashMap<String, ArrayList<String>> cities;
    HashMap<String, ArrayList<Flight>> adjacencyFlights;

    ArrayList<String> commands;
    ArrayList<Route> possibleRoutes;

    public ATCTower( HashMap<String, String> airports, HashMap<String, ArrayList<Flight>> flightGraph, ArrayList<String> commands) {
        this.commands = commands;
        this.airports = airports;
        this.adjacencyFlights = flightGraph;
        this.cities = new HashMap<>();
        this.possibleRoutes = new ArrayList<>();

        // for simplicity create a reversed airport array
        for (Map.Entry<String, String> entry : airports.entrySet()) {
            if (cities.get(entry.getValue()) == null) {
                ArrayList<String> alias = new ArrayList<>();
                alias.add(entry.getKey());
                cities.put(entry.getValue(), alias);
            } else {
                ArrayList<String> alias = cities.get(entry.getValue());
                alias.add(entry.getKey());
            }
        }

    }

    public void performCommands() {

        // create output file to write results
        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));

            for (String commandLine : commands) {
                String[] hashedCommand = commandLine.split("\t");
                String[] cities; // if exists
                try{
                    writer.write("command : " + commandLine + "\n");
                }catch (Exception e){
                    e.printStackTrace();
                }
                switch (hashedCommand[0]) {
                    case "listAll":
                        cities = hashedCommand[1].split("->");
                        listAll(cities[0], cities[1], hashedCommand[2]);
                        printOutput(possibleRoutes);
                        break;

                    case "listProper":
                        cities = hashedCommand[1].split("->");
                        printOutput(listProper(cities[0], cities[1], hashedCommand[2]));
                        break;

                    case "listCheapest":
                        cities = hashedCommand[1].split("->");
                        printOutput(listCheapest(cities[0], cities[1], hashedCommand[2]));
                        break;

                    case "listQuickest":
                        cities = hashedCommand[1].split("->");
                        printOutput(listQuickest(cities[0], cities[1], hashedCommand[2]));
                        break;

                    case "listCheaper":
                        cities = hashedCommand[1].split("->");
                        printOutput(listCheaper(cities[0], cities[1], hashedCommand[2], Integer.parseInt(hashedCommand[3])));
                        break;

                    case "listQuicker":
                        cities = hashedCommand[1].split("->");
                        printOutput(listQuicker(cities[0], cities[1], hashedCommand[2], hashedCommand[3]));
                        break;

                    case "listExcluding":
                        cities = hashedCommand[1].split("->");
                        printOutput(listExcluding(cities[0], cities[1], hashedCommand[2], hashedCommand[3]));
                        break;
                    case "listOnlyFrom":
                        cities = hashedCommand[1].split("->");
                        printOutput(listOnlyFrom(cities[0], cities[1], hashedCommand[2], hashedCommand[3]));
                        break;
                    case "diameterOfGraph":
                        diameterOfGraph();
                        break;
                    case "pageRankOfNodes":
                        pageRankOfNodes();
                        break;
                    default:
                        break;
                }


            }

            writer.close();
        } catch (IOException e) {
            System.out.println("output.txt failed: \n" + e.getMessage());
            System.exit(0);
        }


    }

    /**
     * Command Structure:
     * [listAll] tab [dept]-> [arr] tab [start_date]
     */
    private void listAll(String dept, String arr, String dateStr) {
        LocalDateTime date = LocalDateTime.parse(dateStr.split("/")[2] + "-" + dateStr.split("/")[1] + "-" + dateStr.split("/")[0] + "T" + "00:00");
        this.possibleRoutes = new ArrayList<>();
        // for every airport in a city we need to find a way to any airport in arrival city
        for (String alias : cities.get(dept)) {
            dfs(alias, arr, date, new Route(), new ArrayList<String>());
        }
    }

    /**
     * I did not consider efficiency for that method, so enjoy the O(n^2) time complexity
     * Command Structure:
     * [listProper] tab [dept]-> [arr] tab [start_date]
     * for considering arrival time instead of duration following phrase could be replaces with the first if contition
     * compare.getArrivalDate().isAfter(route.getArrivalDate())
     */
    private ArrayList<Route> listProper(String dept, String arr, String dateStr) {
        // if it has not been called before, call listAll to fill the possible routes array
        // could be empty due to lack of flights but calling twice is a safe play
        if (possibleRoutes.isEmpty()) {
            listAll(dept, arr, dateStr);
        }
        //change the clone of the list so that the other functions may remain as they first set
        ArrayList<Route> cloneRoutes = (ArrayList<Route>) possibleRoutes.clone();

        Set<Integer> deleteIndex = new HashSet<>();
        for (Route route : cloneRoutes) {
            int index = 0;

            // find all flights needs to be removed compare to indexed route and store their indexes
            for (Route compare : cloneRoutes) {
                if (compare.getDuration() > route.getDuration() && compare.getPrice() > route.getPrice()) {
                    deleteIndex.add(index);
                }
                index++;
            }
        }
        // remove the flights safely
        return cleanList(cloneRoutes, deleteIndex);
    }

    /**
     * Command Structure:
     * [listCheapest] tab [dept]-> [arr] tab [start_date]
     */
    private ArrayList<Route> listCheapest(String dept, String arr, String dateStr) {
        // if it has not been called before, call listAll to fill the possible routes array
        // could be empty due to lack of flights but calling twice is a safe play
        if (possibleRoutes.isEmpty()) {
            listAll(dept, arr, dateStr);
        }
        //change the clone of the list so that the other functions may remain as they first set
        ArrayList<Route> cloneRoutes = (ArrayList<Route>) possibleRoutes.clone();

        Set<Integer> deleteIndex = new HashSet<>();
        for (Route route : cloneRoutes) {
            int index = 0;

            // find all flights needs to be removed compare to indexed route and store their indexes
            for (Route compare : cloneRoutes) {
                if (compare.getPrice() > route.getPrice()) {
                    deleteIndex.add(index);
                }
                index++;
            }
        }
        // remove the flights safely
        return cleanList(cloneRoutes, deleteIndex);


    }

    /**
     * Command Structure:
     * [listQuickest] tab [dept]-> [arr] tab [start_date]
     */
    private ArrayList<Route> listQuickest(String dept, String arr, String dateStr) {
        // if it has not been called before, call listAll to fill the possible routes array
        // could be empty due to lack of flights but calling twice is a safe play
        if (possibleRoutes.isEmpty()) {
            listAll(dept, arr, dateStr);
        }
        //change the clone of the list so that the other functions may remain as they first set
        ArrayList<Route> cloneRoutes = (ArrayList<Route>) possibleRoutes.clone();

        Set<Integer> deleteIndex = new HashSet<>();
        for (Route route : cloneRoutes) {
            int index = 0;

            // find all flights needs to be removed compare to indexed route and store their indexes
            for (Route compare : cloneRoutes) {
                if (compare.getDuration() > route.getDuration()) {
                    deleteIndex.add(index);
                }
                index++;
            }
        }
        // remove the flights safely
        return cleanList(cloneRoutes, deleteIndex);


    }

    /**
     * Command Structure:
     * [listCheaper] tab [dept]-> [arr] tab [start_date] tab [max_price]
     */
    private ArrayList<Route> listCheaper(String dept, String arr, String dateStr, int maxPrice) {
        // if it has not been called before, call listAll to fill the possible routes array
        // could be empty due to lack of flights but calling twice is a safe play
        if (possibleRoutes.isEmpty()) {
            listAll(dept, arr, dateStr);
        }
        //we need all proper flights to search between those
        ArrayList<Route> cloneRoutes = listProper(dept, arr, dateStr);

        Set<Integer> deleteIndex = new HashSet<>();
        int index = 0;
        for (Route route : cloneRoutes) {
            if(route.getPrice() > maxPrice){
                deleteIndex.add(index);
            }
            index++;
        }
        // remove the flights safely

        return cleanList(cloneRoutes, deleteIndex);
    }

    /**
     * Command Structure:
     * [listQuicker] tab [dept]-> [arr] tab [start_date] tab [latest_date_time]
     */
    private ArrayList<Route> listQuicker(String dept, String arr, String dateStr, String latestDateTime) {
        // if it has not been called before, call listAll to fill the possible routes array
        // could be empty due to lack of flights but calling twice is a safe play
        if (possibleRoutes.isEmpty()) {
            listAll(dept, arr, dateStr);
        }
        //we need all proper flights to search between those
        ArrayList<Route> cloneRoutes = listProper(dept, arr, dateStr);

        String[] date = latestDateTime.split(" ");
        LocalDateTime latestLocalDateTime = LocalDateTime.parse(date[0].split("/")[2]+"-"+date[0].split("/")[1]+"-"+date[0].split("/")[0]+"T"+date[1]+":00");

        Set<Integer> deleteIndex = new HashSet<>();
        int index = 0;
        for (Route route : cloneRoutes) {
            if(route.getArrivalDate().isAfter(latestLocalDateTime)){
                deleteIndex.add(index);
            }
            index++;
        }
        // remove the flights safely

        return cleanList(cloneRoutes, deleteIndex);
    }

    /**
     * Command Structure:
     * [listExcluding] tab [dept]-> [arr] tab [start_date] tab [company]
     */
    private ArrayList<Route> listExcluding(String dept, String arr, String dateStr, String company) {
        // if it has not been called before, call listAll to fill the possible routes array
        // could be empty due to lack of flights but calling twice is a safe play
        if (possibleRoutes.isEmpty()) {
            listAll(dept, arr, dateStr);
        }
        //we need all proper flights to search between those
        ArrayList<Route> cloneRoutes = listProper(dept, arr, dateStr);

        Set<Integer> deleteIndex = new HashSet<>();
        int index = 0;
        for (Route route : cloneRoutes) {
            for(Flight flight: route.getTransferRoute()){
                if(flight.getId().startsWith(company)){
                    deleteIndex.add(index);
                    break;
                }
            }
            index++;
        }
        // remove the flights safely

        return cleanList(cloneRoutes, deleteIndex);
    }

    /**
     * Command Structure:
     * [listOnlyFrom] tab [dept]-> [arr] tab [start_date] tab [company]
     */
    private ArrayList<Route> listOnlyFrom(String dept, String arr, String dateStr, String company) {
        // if it has not been called before, call listAll to fill the possible routes array
        // could be empty due to lack of flights but calling twice is a safe play
        if (possibleRoutes.isEmpty()) {
            listAll(dept, arr, dateStr);
        }
        //we need all proper flights to search between those
        ArrayList<Route> cloneRoutes = listProper(dept, arr, dateStr);

        Set<Integer> deleteIndex = new HashSet<>();
        int index = 0;
        for (Route route : cloneRoutes) {
            for(Flight flight: route.getTransferRoute()){
                if(!flight.getId().startsWith(company)){
                    deleteIndex.add(index);
                    break;
                }
            }
            index++;
        }
        // remove the flights safely

        return cleanList(cloneRoutes, deleteIndex);
    }

    /**
     * Command Structure:
     * [diameterOfGraph]
     */
    private void diameterOfGraph() {
        try {
            writer.write("Not implemented\n\n");
        }catch (Exception e){
            e.printStackTrace();
        }
//        int longest = 0;
////        ArrayList<String> allAirports = airports.keySet().toArray();
//        ArrayList<String> allAirports = new ArrayList<>();
//        airports.forEach((key, value) -> allAirports.add(key));
//        for (String arr: allAirports) {
//            for (String dep:allAirports) {
//            }
//        }

    }

    /**
     * Command Structure:
     * [pageRankOfNodes]
     */
    private void pageRankOfNodes() {
        try {
            writer.write("Not implemented\n\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private ArrayList<Route> cleanList(ArrayList<Route> cloneRoutes, Set<Integer> deleteIndex){
        ArrayList<Integer> listed = new ArrayList(deleteIndex);
        listed.sort(Collections.reverseOrder());
        for (int i : listed) {
            cloneRoutes.remove(i);
        }
        return cloneRoutes;
    }


    private void dfs(String alias, String arr, LocalDateTime date, Route currentRoute, ArrayList<String> visited) {
        visited.add(airports.get(alias));
        if (visited.contains(arr) || adjacencyFlights.get(alias) == null) {
            return;
        }

        for (Flight flight : adjacencyFlights.get(alias)) {
            {
                if (isFlightOkay(flight, currentRoute, date)) {

                    currentRoute.addToRoute(flight);
                    visited.add(airports.get(flight.getArrival()));
                    if (airports.get(flight.getArrival()).equals(arr)) {
                        // add the copy to save the progress
                        possibleRoutes.add(currentRoute.getCopy());
                    } else {
                        dfs(flight.getArrival(), arr, date, currentRoute, visited);
                    }
                    currentRoute.removeLast();
                    visited.remove(airports.get(flight.getArrival()));
                }
            }
        }
    }


    // here comes the helper methods
    private boolean isFlightOkay(Flight newFlight, Route currentRoute, LocalDateTime date) {
        if (currentRoute.getTransferRoute().size() < 1 && newFlight.getDepartureDate().isAfter(date)) {
            return true;
        }
        Flight lastFlight = currentRoute.getTransferRoute().get(currentRoute.getTransferRoute().size() - 1);
        // Arrival point of the first flight and departure point of the second flight should be the same airport
        if (!newFlight.getDeparture().equals(lastFlight.getArrival())) {
            return false;
        }

        // Departure time of the second flight should be later than arrival time of the first flight
        if (!newFlight.getDepartureDate().isAfter(lastFlight.getArrivalDate())) {
            return false;
        }

        // Transferring between two airports at the same city is not allowed
        if (airports.get(newFlight.getDeparture()).equals(airports.get(newFlight.getArrival()))) {
            return false;
        }

        // A flight plan may not pass through the same city more than once.
        for (Flight flight : currentRoute.getTransferRoute()) {
            if (newFlight.getArrival().equals(flight.getDeparture()) ||
                    newFlight.getArrival().equals(flight.getArrival())) {
                return false;
            }
        }
        return true;
    }

    private void printOutput(ArrayList<Route> cloneRoutes) {
        try {
            if (cloneRoutes.isEmpty()) {
                writer.write("No suitable flight plan is found\n");
            } else {
                for (Route route : cloneRoutes) {
                    writer.write(route.toString());
                }
            }
            writer.write("\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
