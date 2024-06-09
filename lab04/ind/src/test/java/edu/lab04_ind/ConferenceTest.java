package edu.lab04_ind;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConferenceTest {

    private Conference conference;
    private List<Meeting> meetings;

    @Before
    public void setUp() {
        // Create some sample meetings
        Meeting meeting1 = new Meeting("2022-05-25", "Meeting 1", 10);
        Meeting meeting2 = new Meeting("2022-05-26", "Meeting 2", 20);
        Meeting meeting3 = new Meeting("2022-05-27", "Meeting 3", 15);

        // Add meetings to the list
        meetings = new ArrayList<>();
        meetings.add(meeting1);
        meetings.add(meeting2);
        meetings.add(meeting3);

        // Create a conference object
        conference = new Conference("Conference", "Location", meetings);
    }

    @Test
    public void testGetName() {
        assertEquals("Conference", conference.getName());
    }

    @Test
    public void testSetName() {
        conference.setName("New Conference");
        assertEquals("New Conference", conference.getName());
    }

    @Test
    public void testGetPlace() {
        assertEquals("Location", conference.getPlace());
    }

    @Test
    public void testSetPlace() {
        conference.setPlace("New Location");
        assertEquals("New Location", conference.getPlace());
    }

    @Test
    public void testGetMeetings() {
        assertNotNull(conference.getMeetings());
        assertEquals(3, conference.getMeetings().size());
    }

    @Test
    public void testAddMeeting() {
        Meeting newMeeting = new Meeting("2022-05-28", "New Meeting", 25);
        conference.addMeeting(newMeeting);
        assertEquals(4, conference.getMeetings().size());
        assertTrue(conference.getMeetings().contains(newMeeting));
    }

    @Test
    public void testFindMeetingWithLongestName() {
        Meeting meetingWithLongestName = conference.findMeetingWithLongestName();
        assertEquals("Meeting 3", meetingWithLongestName.getName());
    }

    @Test
    public void testFindMeetingWithFewestParticipants() {
        Meeting meetingWithFewestParticipants = conference.findMeetingWithFewestParticipants();
        assertEquals("Meeting 1", meetingWithFewestParticipants.getName());
    }

    @Test
    public void testSortMeetingsByName() {
        conference.sortMeetingsByName();
        assertEquals("Meeting 1", conference.getMeetings().get(0).getName());
        assertEquals("Meeting 2", conference.getMeetings().get(1).getName());
        assertEquals("Meeting 3", conference.getMeetings().get(2).getName());
    }

    @Test
    public void testSortMeetingsByParticipants() {
        conference.sortMeetingsByParticipants();
        assertEquals("Meeting 1", conference.getMeetings().get(0).getName());
        assertEquals("Meeting 3", conference.getMeetings().get(1).getName());
        assertEquals("Meeting 2", conference.getMeetings().get(2).getName());
    }
}
