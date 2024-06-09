package edu.lab03_ind;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MeetingTest {

    @Test
    public void testGetTopic() {
        Meeting meeting = new Meeting("Project Discussion", "2024-05-21", 10);
        assertEquals("Project Discussion", meeting.getTopic());
    }

    @Test
    public void testSetTopic() {
        Meeting meeting = new Meeting("Project Discussion", "2024-05-21", 10);
        meeting.setTopic("Team Meeting");
        assertEquals("Team Meeting", meeting.getTopic());
    }

    @Test
    public void testGetDate() {
        Meeting meeting = new Meeting("Project Discussion", "2024-05-21", 10);
        assertEquals("2024-05-21", meeting.getDate());
    }

    @Test
    public void testSetDate() {
        Meeting meeting = new Meeting("Project Discussion", "2024-05-21", 10);
        meeting.setDate("2024-05-22");
        assertEquals("2024-05-22", meeting.getDate());
    }

    @Test
    public void testGetParticipants() {
        Meeting meeting = new Meeting("Project Discussion", "2024-05-21", 10);
        assertEquals(10, meeting.getParticipants());
    }

    @Test
    public void testSetParticipants() {
        Meeting meeting = new Meeting("Project Discussion", "2024-05-21", 10);
        meeting.setParticipants(15);
        assertEquals(15, meeting.getParticipants());
    }
}

