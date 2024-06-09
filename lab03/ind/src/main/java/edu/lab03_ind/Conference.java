package edu.lab03_ind;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Represents a conference.
 */
public class Conference {
    private StringProperty name;
    private StringProperty location;
    private List<Meeting> meetings;

    /**
     * Constructs a conference with the given name and location.
     *
     * @param name     the name of the conference
     * @param location the location of the conference
     */
    public Conference(String name, String location) {
        this.name = new SimpleStringProperty(name);
        this.location = new SimpleStringProperty(location);
        this.meetings = new ArrayList<>();
    }

    /**
     * Returns the name of the conference.
     *
     * @return the name of the conference
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the conference.
     *
     * @param name the name of the conference
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Returns the property representing the name of the conference.
     *
     * @return the property representing the name of the conference
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Returns the location of the conference.
     *
     * @return the location of the conference
     */
    public String getLocation() {
        return location.get();
    }

    /**
     * Sets the location of the conference.
     *
     * @param location the location of the conference
     */
    public void setLocation(String location) {
        this.location.set(location);
    }

    /**
     * Returns the property representing the location of the conference.
     *
     * @return the property representing the location of the conference
     */
    public StringProperty locationProperty() {
        return location;
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
     * Adds a meeting to the conference.
     *
     * @param meeting the meeting to be added
     */
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /**
     * Sorts the meetings in the conference by their topic name.
     */
    public void sortMeetingsByName() {
        meetings.sort(Comparator.comparing(Meeting::getTopic));
    }

    /**
     * Sorts the meetings in the conference by the number of participants.
     */
    public void sortMeetingsByParticipants() {
        meetings.sort(Comparator.comparingInt(Meeting::getParticipants));
    }
}
