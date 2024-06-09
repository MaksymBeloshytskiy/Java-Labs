package edu.lab04_t01;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Meeting implements Comparable<Meeting> {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty conferenceId;
    private final SimpleStringProperty date;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty numberOfParticipants;

    public Meeting() {
        this(0, 0, "", "", 0);
    }

    public Meeting(int id, int conferenceId, String date, String name, int numberOfParticipants) {
        this.id = new SimpleIntegerProperty(id);
        this.conferenceId = new SimpleIntegerProperty(conferenceId);
        this.date = new SimpleStringProperty(date);
        this.name = new SimpleStringProperty(name);
        this.numberOfParticipants = new SimpleIntegerProperty(numberOfParticipants);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public int getConferenceId() {
        return conferenceId.get();
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId.set(conferenceId);
    }

    public SimpleIntegerProperty conferenceIdProperty() {
        return conferenceId;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants.get();
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants.set(numberOfParticipants);
    }

    public SimpleIntegerProperty numberOfParticipantsProperty() {
        return numberOfParticipants;
    }

    @Override
    public int compareTo(Meeting o) {
        return this.name.get().compareTo(o.name.get());
    }
}
