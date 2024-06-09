package edu.lab02_ind;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a conference with a list of meetings using streams.
 */
public class ConferenceWithMeetingsListUsingStreams extends ConferenceWithStreams {

    /**
     * Constructs a ConferenceWithMeetingsListUsingStreams object with the specified name, place, and meetings list.
     *
     * @param name         the name of the conference
     * @param place        the place of the conference
     * @param meetingsList the list of meetings
     */
    public ConferenceWithMeetingsListUsingStreams(String name, String place, List<Meeting> meetingsList) {
        super(name, place, meetingsList);
        this.meetingsList = meetingsList;
    }

    /**
     * Returns an array of meetings.
     *
     * @return an array of meetings
     */
    @Override
    public Meeting[] getMeetings() {
        return meetingsList.toArray(new Meeting[0]);
    }

    /**
     * Sorts the meetings list by the first feature, which is the number of participants.
     */
    @Override
    public void sortByFirstFeature() {
        meetingsList = meetingsList.stream()
                .sorted(Comparator.comparingInt(Meeting::getNumberOfParticipants))
                .collect(Collectors.toList());
    }

    /**
     * Sorts the meetings list by the second feature, which is the topic.
     */
    @Override
    public void sortBySecondFeature() {
        meetingsList = meetingsList.stream()
                .sorted(Comparator.comparing(Meeting::getTopic))
                .collect(Collectors.toList());
    }
}
