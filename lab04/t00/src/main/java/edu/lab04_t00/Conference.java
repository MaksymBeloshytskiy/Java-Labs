package edu.lab04_t00;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Conference {
    private String name;
    private String place;
    private List<Meeting> meetings;

    public Conference(String name, String place, List<Meeting> meetings) {
        this.name = name;
        this.place = place;
        this.meetings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    public Meeting findMeetingWithLongestName() {
        return Collections.max(meetings, Comparator.comparingInt(meeting -> meeting.getName().length()));
    }

    public Meeting findMeetingWithFewestParticipants() {
        return Collections.min(meetings, Comparator.comparingInt(Meeting::getNumberOfParticipants));
    }

    public void sortMeetingsByName() {
        meetings.sort(Comparator.comparing(Meeting::getName));
    }

    public void sortMeetingsByParticipants() {
        meetings.sort(Comparator.comparingInt(Meeting::getNumberOfParticipants));
    }
}

