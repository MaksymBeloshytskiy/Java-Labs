package edu.lab02_ind;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The JsonFileWriter class provides a utility for writing data to a JSON file.
 */
public class JsonFileWriter {
    
    /**
     * Writes the given list of conferences to a JSON file with the specified file name.
     *
     * @param conferences the list of conferences to be written
     * @param fileName the name of the JSON file to write to
     */
    public static void writeData(List<ConferenceWithStreams> conferences, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Create Gson object with pretty printing enabled
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // Convert conferences list to JSON and write to file
            gson.toJson(conferences, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

