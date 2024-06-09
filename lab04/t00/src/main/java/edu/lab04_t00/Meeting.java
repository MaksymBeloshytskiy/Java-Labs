package edu.lab04_t00;

class Meeting implements Comparable<Meeting> {
    private String date;
    private String name;
    private int numberOfParticipants;

    public Meeting(String date, String name, int numberOfParticipants) {
        this.date = date;
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    // get id from database
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
