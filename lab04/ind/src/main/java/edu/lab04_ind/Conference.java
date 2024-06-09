package edu.lab04_ind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Conference class represents a conference event.
 * It contains information about the conference name, place, and a list of meetings.
 */
public class Conference {
    private String name;
    private String place;
    private List<Meeting> meetings;

    /**
     * Constructs a Conference object with the specified name, place, and meetings.
     *
     * @param name     the name of the conference
     * @param place    the place where the conference is held
     * @param meetings the list of meetings associated with the conference
     */
    public Conference(String name, String place, List<Meeting> meetings) {
        this.name = name;
        this.place = place;
        this.meetings = new ArrayList<>();
    }

    /**
     * Returns the name of the conference.
     *
     * @return the name of the conference
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the conference.
     *
     * @param name the name of the conference
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the place where the conference is held.
     *
     * @return the place where the conference is held
     */
    public String getPlace() {
        return place;
    }

    /**
     * Sets the place where the conference is held.
     *
     * @param place the place where the conference is held
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Returns the list of meetings associated with the conference.
     *
     * @return the list of meetings associated with the conference
     */
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Adds a meeting to the list of meetings associated with the conference.
     *
     * @param meeting the meeting to be added
     */
    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    /**
     * Finds and returns the meeting with the longest name from the list of meetings.
     *
     * @return the meeting with the longest name
     */
    public Meeting findMeetingWithLongestName() {
        return Collections.max(meetings, Comparator.comparingInt(meeting -> meeting.getName().length()));
    }

    /**
     * Finds and returns the meeting with the fewest participants from the list of meetings.
     *
     * @return the meeting with the fewest participants
     */
    public Meeting findMeetingWithFewestParticipants() {
        return Collections.min(meetings, Comparator.comparingInt(Meeting::getNumberOfParticipants));
    }

    /**
     * Sorts the list of meetings by name in ascending order.
     */
    public void sortMeetingsByName() {
        meetings.sort(Comparator.comparing(Meeting::getName));
    }

    /**
     * Sorts the list of meetings by the number of participants in ascending order.
     */
    public void sortMeetingsByParticipants() {
        meetings.sort(Comparator.comparingInt(Meeting::getNumberOfParticipants));
    }
}

