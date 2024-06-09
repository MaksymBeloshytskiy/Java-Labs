package lab00.ind.src.test;

import lab00.ind.src.main.ConferenceWithMeetingsList;
import lab00.ind.src.main.ConferenceWithMeetingsListUsingStreams;
import lab00.ind.src.main.ConferenceWithStreams;
import lab00.ind.src.main.Meeting;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConferenceTests {

    @Test
    void testConferenceWithMeetingsList() {
        List<Meeting> meetingsList = new ArrayList<>();
        meetingsList.add(new Meeting("2024-03-20", "Artificial Intelligence Workshop", 80));
        meetingsList.add(new Meeting("2024-03-21", "Data Science Symposium", 100));

        ConferenceWithMeetingsList conference = new ConferenceWithMeetingsList("Conference 1", "Place 1", meetingsList);
        assertNotNull(conference);
        assertEquals("Conference 1", conference.getName());
        assertEquals("Place 1", conference.getPlace());
        assertEquals(2, conference.getMeetings().length);
    }

    @Test
    void testConferenceWithMeetingsListUsingStreams() {
        List<Meeting> meetingsList = new ArrayList<>();
        meetingsList.add(new Meeting("2024-03-20", "Artificial Intelligence Workshop", 80));
        meetingsList.add(new Meeting("2024-03-21", "Data Science Symposium", 100));

        ConferenceWithMeetingsListUsingStreams conference = new ConferenceWithMeetingsListUsingStreams("Conference 2", "Place 2", meetingsList);
        assertNotNull(conference);
        assertEquals("Conference 2", conference.getName());
        assertEquals("Place 2", conference.getPlace());
        assertEquals(2, conference.getMeetings().length);
    }

    @Test
    void testConferenceWithStreams() {
        List<Meeting> meetingsList = new ArrayList<>();
        meetingsList.add(new Meeting("2024-03-20", "Artificial Intelligence Workshop", 80));
        meetingsList.add(new Meeting("2024-03-21", "Data Science Symposium", 100));

        ConferenceWithStreams conference = new ConferenceWithStreams("Conference 3", "Place 3", meetingsList);
        assertNotNull(conference);
        assertEquals("Conference 3", conference.getName());
        assertEquals("Place 3", conference.getPlace());
        assertEquals(2, conference.getMeetings().length);
        assertEquals(90.0, conference.calculateAverageParticipants());
    }
}
