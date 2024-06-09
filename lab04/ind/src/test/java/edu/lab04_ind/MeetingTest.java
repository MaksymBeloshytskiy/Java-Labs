package edu.lab04_ind;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MeetingTest {

    @Test
    public void testGetDate() {
        Meeting meeting = new Meeting("2022-05-25", "Test Meeting", 10);
        assertEquals("2022-05-25", meeting.getDate());
    }

    @Test
    public void testSetDate() {
        Meeting meeting = new Meeting("2022-05-25", "Test Meeting", 10);
        meeting.setDate("2022-05-26");
        assertEquals("2022-05-26", meeting.getDate());
    }

    @Test
    public void testGetName() {
        Meeting meeting = new Meeting("2022-05-25", "Test Meeting", 10);
        assertEquals("Test Meeting", meeting.getName());
    }

    @Test
    public void testSetName() {
        Meeting meeting = new Meeting("2022-05-25", "Test Meeting", 10);
        meeting.setName("New Meeting");
        assertEquals("New Meeting", meeting.getName());
    }

    @Test
    public void testGetNumberOfParticipants() {
        Meeting meeting = new Meeting("2022-05-25", "Test Meeting", 10);
        assertEquals(10, meeting.getNumberOfParticipants());
    }

    @Test
    public void testSetNumberOfParticipants() {
        Meeting meeting = new Meeting("2022-05-25", "Test Meeting", 10);
        meeting.setNumberOfParticipants(20);
        assertEquals(20, meeting.getNumberOfParticipants());
    }

    @Test
    public void testGetIdFromDatabase() {
        Meeting meeting = new Meeting("2022-05-25", "Test Meeting", 10);
        assertNotNull(meeting.getIdFromDatabase());
    }

    @Test
    public void testCompareTo() {
        Meeting meeting1 = new Meeting("2022-05-25", "Meeting 1", 10);
        Meeting meeting2 = new Meeting("2022-05-26", "Meeting 2", 15);
        assertEquals(-1, meeting1.compareTo(meeting2));
        assertEquals(1, meeting2.compareTo(meeting1));
        assertEquals(0, meeting1.compareTo(meeting1));
    }

    @Test
    public void testSetConferenceId() {
        Meeting meeting = new Meeting("2022-05-25", "Test Meeting", 10);
        meeting.setConferenceId(123);
        // Assuming setConferenceId() prints "Setting conference id to 123"
    }
}

