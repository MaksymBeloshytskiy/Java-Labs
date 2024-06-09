package lab01.ind.src.test;

import static org.junit.Assert.assertEquals;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lab01.ind.src.main.*;

import org.junit.Before;
import org.junit.Test;

public class ConferenceWithLocalizationTest {

    private ConferenceWithLocalization conference;

    @Before
    public void setUp() {
        // Create a list of events for testing
        List<Event> eventsList = new ArrayList<>();
        eventsList.add(new Event("Event 1", 100, ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC), "Comment 1", Locale.US));
        eventsList.add(new Event("Event 2", 150, ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC), "Comment 2", Locale.US));
        eventsList.add(new Event("Event 3", 200, ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC), "Comment 3", Locale.US));

        // Create a conference instance for testing
        conference = new ConferenceWithLocalization("Test Conference", "Test Place", eventsList, Locale.US);
    }

    @Test
    public void testGetEventsList() {
        // Ensure that the events list returned by getEventsList matches the one provided during initialization
        assertEquals(3, conference.getEventsList().size());
    }

    @Test
    public void testSetEventsList() {
        // Create a new list of events
        List<Event> newEventsList = new ArrayList<>();
        newEventsList.add(new Event("New Event", 50, ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC), "New Comment", Locale.US));

        // Set the new events list
        conference.setEventsList(newEventsList);

        // Ensure that the events list has been updated
        assertEquals(1, conference.getEventsList().size());
    }
}
