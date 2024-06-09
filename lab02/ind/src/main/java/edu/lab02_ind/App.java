package edu.lab02_ind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The Main class represents the entry point of the program.
 * It contains the main method which creates a list of meetings,
 * tests different conference classes, and prints the results.
 */
/**
 * The main class that represents the application.
 */
public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    /**
     * The main method of the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // Create conferences
        List<ConferenceWithStreams> conferences = createConferences();

        // Print conference information
        System.out.println("Conferences:");
        conferences.forEach(System.out::println);

        // Collect all meetings
        List<Meeting> allMeetings = conferences.stream()
                .flatMap(conf -> Arrays.stream(conf.getMeetings()))
                .collect(Collectors.toList());

        // Print all meetings
        System.out.println("\nAll meetings:");
        allMeetings.forEach(System.out::println);

        // Sort meetings by number of participants
        allMeetings.sort(Comparator.comparingInt(Meeting::getNumberOfParticipants));
        System.out.println("\nMeetings sorted by number of participants:");
        allMeetings.forEach(System.out::println);

        // Sort meetings by topic
        allMeetings.sort(Comparator.comparing(Meeting::getTopic));
        System.out.println("\nMeetings sorted by topic:");
        allMeetings.forEach(System.out::println);

        // Write data to text file
        TextFileWriter.writeData(conferences, "C:\\Users\\maksy\\OneDrive\\Documents\\Projects\\JavaLabs\\lab02\\ind\\target\\files\\conferences.txt");
        System.out.println("\nConferences written to the file.");

        // Read data from the text file
        TextFileWriter.readData("C:\\Users\\maksy\\OneDrive\\Documents\\Projects\\JavaLabs\\lab02\\ind\\target\\files\\conferences.txt");
        System.out.println("\nConferences read from the file:");
        conferences.forEach(System.out::println);
        // Meetings from the file
        System.out.println("\nMeetings read from the file:");
        conferences.stream()
                .flatMap(conf -> Arrays.stream(conf.getMeetings()))
                .forEach(System.out::println);

        // Write data to XML and JSON files
        XmlFileWriter.writeData(conferences, "C:\\Users\\maksy\\OneDrive\\Documents\\Projects\\JavaLabs\\lab02\\ind\\target\\files\\conferences.xml");
        JsonFileWriter.writeData(conferences, "C:\\Users\\maksy\\OneDrive\\Documents\\Projects\\JavaLabs\\lab02\\ind\\target\\files\\conferences.json");

        System.out.println("\nConferences written to XML and JSON files.\n");
        // Log completion
        LOGGER.log(Level.INFO, "Program execution completed successfully.");
    }

    /**
     * Creates a list of conferences.
     *
     * @return The list of conferences.
     */
    private static List<ConferenceWithStreams> createConferences() {
        List<ConferenceWithStreams> conferences = new ArrayList<>();

        // Create the first conference
        List<Meeting> meetings1 = new ArrayList<>();
        meetings1.add(new Meeting("2024-05-20", "AI in Healthcare", 50));
        meetings1.add(new Meeting("2024-05-21", "Machine Learning Applications", 70));
        Conference conf1 = new ConferenceWithMeetingsListUsingStreams("FutureAI", "Paris", meetings1);

        // Create the second conference
        List<Meeting> meetings2 = new ArrayList<>();
        meetings2.add(new Meeting("2024-06-10", "Data Science Summit", 80));
        meetings2.add(new Meeting("2024-06-11", "Blockchain Conference", 100));
        Conference conf2 = new ConferenceWithMeetingsListUsingStreams("Data Analysis", "Houston", meetings2);

        // Add conferences to the list
        conferences.add((ConferenceWithStreams) conf1);
        conferences.add((ConferenceWithStreams) conf2);

        return conferences;
    }
}
