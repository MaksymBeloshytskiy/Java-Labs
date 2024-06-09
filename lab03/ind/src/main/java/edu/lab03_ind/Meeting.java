package edu.lab03_ind;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a meeting with a topic, date, and number of participants.
 */
public class Meeting {
    private StringProperty topic;
    private StringProperty date;
    private int participants;

    /**
     * Constructs a Meeting object with the specified topic, date, and number of participants.
     *
     * @param topic        the topic of the meeting
     * @param date         the date of the meeting
     * @param participants the number of participants in the meeting
     */
    public Meeting(String topic, String date, int participants) {
        this.topic = new SimpleStringProperty(topic);
        this.date = new SimpleStringProperty(date);
        this.participants = participants;
    }

    /**
     * Returns the topic of the meeting.
     *
     * @return the topic of the meeting
     */
    public String getTopic() {
        return topic.get();
    }

    /**
     * Sets the topic of the meeting.
     *
     * @param topic the topic of the meeting
     */
    public void setTopic(String topic) {
        this.topic.set(topic);
    }

    /**
     * Returns the StringProperty representing the topic of the meeting.
     *
     * @return the StringProperty representing the topic of the meeting
     */
    public StringProperty topicProperty() {
        return topic;
    }

    /**
     * Returns the date of the meeting.
     *
     * @return the date of the meeting
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Sets the date of the meeting.
     *
     * @param date the date of the meeting
     */
    public void setDate(String date) {
        this.date.set(date);
    }

    /**
     * Returns the StringProperty representing the date of the meeting.
     *
     * @return the StringProperty representing the date of the meeting
     */
    public StringProperty dateProperty() {
        return date;
    }

    /**
     * Returns the number of participants in the meeting.
     *
     * @return the number of participants in the meeting
     */
    public int getParticipants() {
        return participants;
    }

    /**
     * Sets the number of participants in the meeting.
     *
     * @param participants the number of participants in the meeting
     */
    public void setParticipants(int participants) {
        this.participants = participants;
    }
}