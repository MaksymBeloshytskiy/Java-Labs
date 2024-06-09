package edu.lab04_t01;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Conference {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty place;
    private final List<Meeting> meetings;

    public Conference() {
        this(0, "", "", new ArrayList<>());
    }

    public Conference(int id, String name, String place, List<Meeting> meetings) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.place = new SimpleStringProperty(place);
        this.meetings = meetings;
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

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getPlace() {
        return place.get();
    }

    public void setPlace(String place) {
        this.place.set(place);
    }

    public SimpleStringProperty placeProperty() {
        return place;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }
}
