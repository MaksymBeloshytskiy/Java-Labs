package edu.lab02_ind;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The TextFileWriter class provides methods to write and read data from a text file.
 */
public class TextFileWriter {

    /**
     * Writes the data of conferences to a text file.
     *
     * @param conferences The list of conferences to write.
     * @param fileName    The name of the file to write the data to.
     */
    public static void writeData(List<ConferenceWithStreams> conferences, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Conference conference : conferences) {
                writer.write("Conference: " + conference.getName() + " (" + conference.getPlace() + ")");
                writer.newLine();
                writer.write("Meetings:");
                writer.newLine();
                Meeting[] meetings = ((ConferenceWithStreams) conference).searchMeetingsInAlphabetOrder();
                for (Meeting meeting : meetings) {
                    writer.write("- Date: " + meeting.getDate() + ", Topic: " + meeting.getTopic() +
                            ", Participants: " + meeting.getNumberOfParticipants());
                    writer.newLine();
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the data of conferences from a text file.
     *
     * @param fileName The name of the file to read the data from.
     * @return The list of conferences read from the file.
     */
    public static List<ConferenceWithStreams> readData(String fileName) {
        List<ConferenceWithStreams> conferences = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            ConferenceWithStreams conference = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Conference: ")) {
                    String[] parts = line.split(" \\(");
                    String name = parts[0].substring("Conference: ".length());
                    String place = parts[1].substring(0, parts[1].length() - 1);
                    conference = new ConferenceWithStreams(name, place, new ArrayList<>());
                    conferences.add(conference);
                } else if (line.equals("Meetings:")) {
                    List<Meeting> meetings = new ArrayList<>();
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        String[] meetingInfo = line.split(", ");
                        String date = meetingInfo[0].substring("Date: ".length());
                        String topic = meetingInfo[1].substring("Topic: ".length());
                        int participants = Integer.parseInt(meetingInfo[2].substring("Participants: ".length()));
                        meetings.add(new Meeting(date, topic, participants));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conferences;
    }
}
