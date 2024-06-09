package lab01.ind.src.test;

import static org.junit.Assert.assertEquals;

import java.time.ZonedDateTime;
import java.util.Locale;
import java.time.ZoneOffset;
import lab01.ind.src.main.Event;

import org.junit.Test;

public class EventTest {

    @Test
    public void testGetFormattedDateTime() {
        // Create an event with a specific date and time
        ZonedDateTime dateTime = ZonedDateTime.of(2024, 3, 20, 10, 0, 0, 0, ZoneOffset.UTC);
        Event event = new Event("Artificial Intelligence Workshop", 80, dateTime, "Interesting topic", Locale.US);

        // Define the expected formatted date and time string
        String expectedDateTime = "20.03.2024 10:00 GMT +00:00";

        // Call the getFormattedDateTime method
        String formattedDateTime = event.getFormattedDateTime(dateTime, Locale.US);

        // Assert that the formattedDateTime matches the expectedDateTime
        assertEquals(expectedDateTime, formattedDateTime);
    }

    @Test
    public void testCalculateTimeDifference() {
        // Create two events with different date times
        ZonedDateTime dateTime1 = ZonedDateTime.of(2024, 3, 20, 10, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime dateTime2 = ZonedDateTime.of(2024, 3, 20, 11, 30, 0, 0, ZoneOffset.UTC);
        Event event1 = new Event("Event 1", 100, dateTime1, "Comment 1", Locale.US);
        Event event2 = new Event("Event 2", 150, dateTime2, "Comment 2", Locale.US);

        // Calculate the time difference between the two events
        long timeDifference = event1.calculateTimeDifference(event2);

        // Assert that the time difference is as expected (1 hour and 30 minutes in minutes)
        assertEquals(90, timeDifference);
    }

    @Test
    public void testGetLocalizedComment() {
        // Create an event with a specific comment and locale
        Event event = new Event("Event", 50, ZonedDateTime.now(), "Interesting comment", Locale.US);

        // Get the localized comment for the event
        String localizedComment = event.getLocalizedComment(Locale.US);

        // Assert that the localized comment matches the original comment
        assertEquals("The best conference ever", localizedComment);
    }
}
