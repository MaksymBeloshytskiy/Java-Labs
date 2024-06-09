package edu.lab03_ind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ConferenceTest {

    private Conference conference;

    @Before
    public void setUp() {
        conference = new Conference("Tech Conference", "New York");
        conference.addMeeting(new Meeting("Workshop", "2024-05-21", 50));
        conference.addMeeting(new Meeting("Panel Discussion", "2024-05-22", 100));
        conference.addMeeting(new Meeting("Keynote Speech", "2024-05-23", 200));
    }

    @Test
    public void testGetName() {
        assertEquals("Tech Conference", conference.getName());
    }

    @Test
    public void testSetName() {
        conference.setName("Science Conference");
        assertEquals("Science Conference", conference.getName());
    }

    @Test
    public void testGetLocation() {
        assertEquals("New York", conference.getLocation());
    }

    @Test
    public void testSetLocation() {
        conference.setLocation("San Francisco");
        assertEquals("San Francisco", conference.getLocation());
    }

    @Test
    public void testGetMeetings() {
        List<Meeting> meetings = conference.getMeetings();
        assertEquals(3, meetings.size());
    }

    @Test
    public void testAddMeeting() {
        conference.addMeeting(new Meeting("Seminar", "2024-05-24", 150));
        List<Meeting> meetings = conference.getMeetings();
        assertEquals(4, meetings.size());
    }

    @Test
    public void testSortMeetingsByName() {
        conference.sortMeetingsByName();
        List<Meeting> meetings = conference.getMeetings();
        assertTrue(meetings.get(0).getTopic().equals("Keynote Speech"));
    }

    @Test
    public void testSortMeetingsByParticipants() {
        conference.sortMeetingsByParticipants();
        List<Meeting> meetings = conference.getMeetings();
        assertTrue(meetings.get(0).getParticipants() == 50);
    }
}

