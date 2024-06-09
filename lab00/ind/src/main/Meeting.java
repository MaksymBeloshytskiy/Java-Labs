package lab00.ind.src.main;

/**
 * Represents a meeting with a date, topic, and number of participants.
 */
public class Meeting implements Comparable<Meeting> {
    private String date;
    private String topic;
    private int numberOfParticipants;

    /**
     * Constructs a Meeting object with the specified date, topic, and number of participants.
     *
     * @param date                the date of the meeting
     * @param topic               the topic of the meeting
     * @param numberOfParticipants the number of participants in the meeting
     */
    public Meeting(String date, String topic, int numberOfParticipants) {
        this.date = date;
        this.topic = topic;
        this.numberOfParticipants = numberOfParticipants;
    }

    /**
     * Returns the date of the meeting.
     *
     * @return the date of the meeting
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the topic of the meeting.
     *
     * @return the topic of the meeting
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Returns the number of participants in the meeting.
     *
     * @return the number of participants in the meeting
     */
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    /**
     * Returns a string representation of the meeting.
     *
     * @return a string representation of the meeting
     */
    @Override
    public String toString() {
        return String.format("Date: %s\nTopic: %s\nNumber of Participants: %d", date, topic, numberOfParticipants);
    }

    /**
     * Checks if this meeting is equal to another object.
     *
     * @param obj the object to compare to
     * @return true if the meetings are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Meeting meeting = (Meeting) obj;
        return date.equals(meeting.date) &&
                topic.equals(meeting.topic) &&
                numberOfParticipants == meeting.numberOfParticipants;
    }

    /**
     * Returns the hash code value for the meeting.
     *
     * @return the hash code value for the meeting
     */
    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + topic.hashCode();
        result = 31 * result + numberOfParticipants;
        return result;
    }

    /**
     * Compares this meeting to another meeting based on the number of participants.
     *
     * @param o the meeting to compare to
     * @return a negative integer if this meeting has fewer participants,
     *         zero if the meetings have the same number of participants,
     *         a positive integer if this meeting has more participants
     */
    @Override
    public int compareTo(Meeting o) {
        return Integer.compare(this.numberOfParticipants, o.numberOfParticipants);
    }
}
