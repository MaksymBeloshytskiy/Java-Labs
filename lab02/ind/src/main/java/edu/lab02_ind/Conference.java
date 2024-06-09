package edu.lab02_ind;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Abstract class representing the first entity of the individual task.
 */
public abstract class Conference {

    private String name;
    private String place;

    /**
     * Constructor for the Conference class.
     *
     * @param name  the name of the conference
     * @param place the place where the conference takes place
     */
    public Conference(String name, String place) {
        this.name = name;
        this.place = place;
    }

    /**
     * Access function for the name of the conference.
     *
     * @return the name of the conference
     */
    public String getName() {
        return name;
    }

    /**
     * Access function for the place where the conference takes place.
     *
     * @return the place of the conference
     */
    public String getPlace() {
        return place;
    }

    /**
     * Abstract method for sorting meetings by the first feature.
     */
    public void sortByFirstFeature() {
        Meeting[] meetings = getMeetings();
        if (meetings != null) {
            int n = meetings.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (meetings[j].getNumberOfParticipants() > meetings[j + 1].getNumberOfParticipants()) {
                        // Swap elements if they are in the wrong order
                        Meeting temp = meetings[j];
                        meetings[j] = meetings[j + 1];
                        meetings[j + 1] = temp;
                    }
                }
            }
        }
    }

    /**
     * Abstract method for sorting meetings by the second feature.
     */
    public void sortBySecondFeature() {
        Meeting[] meetings = getMeetings();
        if (meetings != null) {
            int n = meetings.length;
            for (int i = 1; i < n; ++i) {
                Meeting key = meetings[i];
                int j = i - 1;

                // Compare elements by the second feature and order
                while (j >= 0 && meetings[j].getTopic().compareTo(key.getTopic()) > 0) {
                    meetings[j + 1] = meetings[j];
                    j = j - 1;
                }
                meetings[j + 1] = key;
            }
        }
    }

    /**
     * Abstract method to access the sequence of elements of the second class type.
     *
     * @return an array of meetings
     */
    public abstract Meeting[] getMeetings();
    
    /**
     * Calculate the average number of participants in the meetings.
     *
     * @return the average number of participants
     */
    public double calculateAverageParticipants() {
        Meeting[] meetings = getMeetings();
        if (meetings != null && meetings.length > 0) {
            int totalParticipants = 0;
            for (Meeting meeting : meetings) {
                totalParticipants += meeting.getNumberOfParticipants();
            }
            return (double) totalParticipants / meetings.length;
        }
        return 0.0;
    }

    /**
     * Find the meeting with the largest number of participants.
     *
     * @return the meeting with the largest number of participants
     */
    public Meeting findMeetingWithLargestParticipants() {
        Meeting[] meetings = getMeetings();
        if (meetings != null && meetings.length > 0) {
            Meeting largestMeeting = meetings[0];
            for (Meeting meeting : meetings) {
                if (meeting.getNumberOfParticipants() > largestMeeting.getNumberOfParticipants()) {
                    largestMeeting = meeting;
                }
            }
            return largestMeeting;
        }
        return null;
    }

    /**
     * Determine the length of each meeting name.
     *
     * @return an array containing the length of each meeting name
     */
    public int[] findMeetingNameLengths() {
        Meeting[] meetings = getMeetings();
        if (meetings != null && meetings.length > 0) {
            int[] lengths = new int[meetings.length];
            for (int i = 0; i < meetings.length; i++) {
                lengths[i] = meetings[i].getTopic().length();
            }
            return lengths;
        }
        return null;
    }

    /**
     * Overrides the toString() method.
     *
     * @return a string representation of the conference
     */
    @Override
    public String toString() {
        return "Conference{" +
                "name='" + name + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

    /**
     * Overrides the equals() method.
     *
     * @param obj another object for comparison
     * @return true if objects are equivalent, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Conference conference = (Conference) obj;
        return name.equals(conference.name) && place.equals(conference.place);
    }

    /**
     * Overrides the hashCode() method.
     *
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        return name.hashCode() + place.hashCode();
    }

    /**
     * Implementation of the method for searching meetings by the specified criteria.
     *
     * @param minParticipants the minimum number of participants
     * @return an array of meetings that meet the search criteria
     */
    public Meeting[] searchMeetingsByParticipants(int minParticipants) {
        Meeting[] meetings = getMeetings();
        if (meetings != null) {
            return Arrays.stream(meetings)
                    .filter(meeting -> meeting.getNumberOfParticipants() >= minParticipants)
                    .toArray(Meeting[]::new);
        }
        return null;
    }

    /**
     * Implementation of the method for searching meetings in alphabet order.
     *
     * @return an array of meetings in alphabet order
     */
    public Meeting[] searchMeetingsInAlphabetOrder() {
        Meeting[] meetings = getMeetings();
        if (meetings != null) {
            return Arrays.stream(meetings)
                    .sorted(Comparator.comparing(Meeting::getTopic))
                    .toArray(Meeting[]::new);
        }
        return null;
    }
}
