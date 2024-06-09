package lab00.ind.src.main;

import java.util.ArrayList;
import java.util.List;

/**
 * The Main class represents the entry point of the program.
 * It contains the main method which creates a list of meetings,
 * tests different conference classes, and prints the results.
 */
public class Main {
    public static void main(String[] args) {
        // Creating a list of meetings
        List<Meeting> meetingsList = new ArrayList<>();
        meetingsList.add(new Meeting("2024-03-20", "Artificial Intelligence Workshop", 80));
        meetingsList.add(new Meeting("2024-03-21", "Data Science Symposium", 100));
        meetingsList.add(new Meeting("2024-03-22", "Machine Learning Conference", 120));
        meetingsList.add(new Meeting("2024-03-23", "Blockchain Summit", 90));

        // Testing ConferenceWithMeetingsList
        ConferenceWithMeetingsList conferenceWithMeetingsList = new ConferenceWithMeetingsList("Conference 1", "Place 1", meetingsList);
        System.out.println("Conference with meetings list:");
        printMeetings(conferenceWithMeetingsList.getMeetings());

        // Testing ConferenceWithMeetingsListUsingStreams
        ConferenceWithMeetingsListUsingStreams conferenceWithMeetingsListUsingStreams = new ConferenceWithMeetingsListUsingStreams("Conference 2", "Place 2", meetingsList);
        System.out.println("\nConference with meetings list using streams:");
        printMeetings(conferenceWithMeetingsListUsingStreams.getMeetings());

        // Testing ConferenceWithStreams
        ConferenceWithStreams conferenceWithStreams = new ConferenceWithStreams("Conference 3", "Place 3", meetingsList);
        System.out.println("\nConference with streams:");
        System.out.println("Average number of participants: " + conferenceWithStreams.calculateAverageParticipants());
        System.out.println("Meeting with largest number of participants: ");
        System.out.println(conferenceWithStreams.findMeetingWithLargestParticipants());
        System.out.println("Meetings sorted by topic:");
        printMeetings(conferenceWithStreams.searchMeetingsInAlphabetOrder());
    }

    // Helper method to print meetings
    private static void printMeetings(Meeting[] meetings) {
        if (meetings != null) {
            for (Meeting meeting : meetings) {
                System.out.println("Date: " + meeting.getDate());
                System.out.println("Topic: " + meeting.getTopic());
                System.out.println("Number of Participants: " + meeting.getNumberOfParticipants());
                System.out.println("====================");
            }
        }
    }
}
