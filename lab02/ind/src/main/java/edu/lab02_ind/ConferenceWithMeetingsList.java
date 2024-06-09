package edu.lab02_ind;

import java.util.Collections;
import java.util.List;

/**
 * Represents a conference with a list of meetings.
 * Extends the Conference class.
 */
public class ConferenceWithMeetingsList extends Conference {

    private List<Meeting> meetingsList;

    /**
     * Constructs a ConferenceWithMeetingsList object with the specified name, place, and meetings list.
     *
     * @param name         the name of the conference
     * @param place        the place of the conference
     * @param meetingsList the list of meetings associated with the conference
     */
    public ConferenceWithMeetingsList(String name, String place, List<Meeting> meetingsList) {
        super(name, place);
        this.meetingsList = meetingsList;
    }

    /**
     * Returns an array of meetings associated with the conference.
     *
     * @return an array of meetings
     */
    @Override
    public Meeting[] getMeetings() {
        return meetingsList.toArray(new Meeting[0]);
    }

    /**
     * Sorts the meetings list by the first feature (number of participants) using Collections.sort().
     */
    @Override
    public void sortByFirstFeature() {
        Collections.sort(meetingsList, (m1, m2) -> Integer.compare(m1.getNumberOfParticipants(), m2.getNumberOfParticipants()));
    }

    /**
     * Sorts the meetings list by the second feature (topic) using Collections.sort().
     */
    @Override
    public void sortBySecondFeature() {
        Collections.sort(meetingsList, (m1, m2) -> m1.getTopic().compareTo(m2.getTopic()));
    }
}
