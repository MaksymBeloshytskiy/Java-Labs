package edu.lab04_ind;

/**
 * Represents a meeting.
 */
class Meeting implements Comparable<Meeting> {
    private String date;
    private String name;
    private int numberOfParticipants;

    /**
     * Constructs a meeting with the specified date, name, and number of participants.
     *
     * @param date                the date of the meeting
     * @param name                the name of the meeting
     * @param numberOfParticipants the number of participants in the meeting
     */
    public Meeting(String date, String name, int numberOfParticipants) {
        this.date = date;
        this.name = name;
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
     * Sets the date of the meeting.
     *
     * @param date the date of the meeting
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the name of the meeting.
     *
     * @return the name of the meeting
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the meeting.
     *
     * @param name the name of the meeting
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets the number of participants in the meeting.
     *
     * @param numberOfParticipants the number of participants in the meeting
     */
    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    /**
     * Retrieves the ID of the meeting from the database.
     *
     * @return the ID of the meeting
     */
    public String getIdFromDatabase() {
        return "id";
    }

    @Override
    public int compareTo(Meeting o) {
        return this.name.compareTo(o.name);
    }

    // Set conference id
    public void setConferenceId(int conferenceId) {
        System.out.println("Setting conference id to " + conferenceId);
    }
}
