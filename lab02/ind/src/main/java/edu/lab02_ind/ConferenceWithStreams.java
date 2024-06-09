package edu.lab02_ind;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that represents a conference with additional stream operations.
 */
public class ConferenceWithStreams extends Conference {

    protected List<Meeting> meetingsList;

    /**
     * Constructs a ConferenceWithStreams object with the specified name, place, and list of meetings.
     *
     * @param name         the name of the conference
     * @param place        the place of the conference
     * @param meetingsList the list of meetings in the conference
     */
    public ConferenceWithStreams(String name, String place, List<Meeting> meetingsList) {
        super(name, place);
        this.meetingsList = meetingsList;
    }

    /**
     * Returns an array of meetings in the conference.
     *
     * @return an array of meetings
     */
    @Override
    public Meeting[] getMeetings() {
        return meetingsList.toArray(new Meeting[0]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Conference: ").append(getName()).append(" (").append(getPlace()).append(")");
        return builder.toString();
    }

    /**
     * Sorts the meetings in the conference by the first feature (number of participants).
     */
    @Override
    public void sortByFirstFeature() {
        meetingsList = meetingsList.stream()
                .sorted(Comparator.comparingInt(Meeting::getNumberOfParticipants))
                .collect(Collectors.toList());
    }

    /**
     * Sorts the meetings in the conference by the second feature (topic).
     */
    @Override
    public void sortBySecondFeature() {
        meetingsList = meetingsList.stream()
                .sorted(Comparator.comparing(Meeting::getTopic))
                .collect(Collectors.toList());
    }

    /**
     * Calculates the average number of participants in the meetings.
     *
     * @return the average number of participants
     */
    @Override
    public double calculateAverageParticipants() {
        return meetingsList.stream()
                .mapToInt(Meeting::getNumberOfParticipants)
                .average()
                .orElse(0.0);
    }

    /**
     * Finds the meeting with the largest number of participants.
     *
     * @return the meeting with the largest number of participants, or null if there are no meetings
     */
    @Override
    public Meeting findMeetingWithLargestParticipants() {
        return meetingsList.stream()
                .max(Comparator.comparingInt(Meeting::getNumberOfParticipants))
                .orElse(null);
    }

    /**
     * Finds the lengths of the names of the meetings.
     *
     * @return an array of integers representing the lengths of the meeting names
     */
    @Override
    public int[] findMeetingNameLengths() {
        return meetingsList.stream()
                .mapToInt(meeting -> meeting.getTopic().length())
                .toArray();
    }

    /**
     * Searches for meetings with a minimum number of participants.
     *
     * @param minParticipants the minimum number of participants
     * @return an array of meetings with at least the specified number of participants
     */
    @Override
    public Meeting[] searchMeetingsByParticipants(int minParticipants) {
        return meetingsList.stream()
                .filter(meeting -> meeting.getNumberOfParticipants() >= minParticipants)
                .toArray(Meeting[]::new);
    }

    /**
     * Searches for meetings in alphabetical order by topic.
     *
     * @return an array of meetings sorted in alphabetical order by topic
     */
    @Override
    public Meeting[] searchMeetingsInAlphabetOrder() {
        return meetingsList.stream()
                .sorted(Comparator.comparing(Meeting::getTopic))
                .toArray(Meeting[]::new);
    }
}
