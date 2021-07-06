import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {
    /**
     * Propogated {@link IOException} here
     * {@link #readFile} and {@link #writeOutput} methods should be called here
     * A {@link Scheduler} instance must be instantiated here
     */
    public static void main(String[] args) throws IOException {
//        Assignment[] assignmentList = readFile("./sample_io/sampleinput.json");
        Assignment[] assignmentList = readFile(args[0]);
        assert assignmentList != null; // in case the readFile method returns null due to an error
        Arrays.sort(assignmentList);
        Scheduler scheduler = new Scheduler(assignmentList);
        writeOutput("solution_dynamic.json", scheduler.scheduleDynamic());
        writeOutput("solution_greedy.json", scheduler.scheduleGreedy());


    }

    /**
     * @param filename json filename to read
     * @return Returns a list of {@link Assignment}s obtained by reading the given json file
     * @throws FileNotFoundException If the given file does not exist
     */
    private static Assignment[] readFile(String filename) throws FileNotFoundException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            ArrayList<Assignment> assignmentList = new Gson().fromJson(reader, new TypeToken<ArrayList<Assignment>>() {
            }.getType());
            return assignmentList.toArray(new Assignment[assignmentList.size()]);

        }catch (IOException ioException) {
            throw new FileNotFoundException("File not found");
        }
    }

    /**
     * @param filename  json filename to write
     * @param arrayList a list of {@link Assignment}s to write into the file
     * @throws IOException If something goes wrong with file creation
     */
    private static void writeOutput(String filename, ArrayList<Assignment> arrayList) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            gson.toJson(arrayList, writer);
        }
    }
}
