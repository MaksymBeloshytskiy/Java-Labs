package edu.lab02_ind;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextFileWriterTest {

    private static final String TEST_FILE = "test_conferences.txt";

    private List<ConferenceWithStreams> conferences;

    @BeforeEach
    void setUp() {
        // Create sample conference data for testing
        conferences = new ArrayList<>();

        List<Meeting> meetings1 = new ArrayList<>();
        meetings1.add(new Meeting("2024-05-20", "AI in Healthcare", 50));
        ConferenceWithStreams conference1 = new ConferenceWithStreams("FutureAI", "Paris", meetings1);

        List<Meeting> meetings2 = new ArrayList<>();
        meetings2.add(new Meeting("2024-06-10", "Data Science Summit", 80));
        ConferenceWithStreams conference2 = new ConferenceWithStreams("Data Analysis", "Houston", meetings2);

        conferences.add(conference1);
        conferences.add(conference2);
    }

    @AfterEach
    void tearDown() {
        // Delete the test file after each test
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testWriteAndReadData() {
        // Write data to the test file
        TextFileWriter.writeData(conferences, TEST_FILE);

        // Check if the file exists
        assertTrue(Files.exists(Paths.get(TEST_FILE)), "File should exist");

        // Read data from the test file
        List<ConferenceWithStreams> readConferences = TextFileWriter.readData(TEST_FILE);

        // Check if the read data is not null
        assertNotNull(readConferences, "Read data should not be null");

        // Check if the read data matches the original conferences
        assertEquals(conferences.size(), readConferences.size(), "Number of conferences should match");

        for (int i = 0; i < conferences.size(); i++) {
            ConferenceWithStreams expected = conferences.get(i);
            ConferenceWithStreams actual = readConferences.get(i);
            assertEquals(expected.getName(), actual.getName(), "Conference name should match");
            assertEquals(expected.getPlace(), actual.getPlace(), "Conference place should match");

            // Check meetings
            Meeting[] expectedMeetings = actual.searchMeetingsInAlphabetOrder();
            Meeting[] actualMeetings = actual.searchMeetingsInAlphabetOrder();
            assertEquals(expectedMeetings.length, actualMeetings.length, "Number of meetings should match");

            for (int j = 0; j < expectedMeetings.length; j++) {
                assertEquals(expectedMeetings[j].getDate(), actualMeetings[j].getDate(), "Meeting date should match");
                assertEquals(expectedMeetings[j].getTopic(), actualMeetings[j].getTopic(), "Meeting topic should match");
                assertEquals(expectedMeetings[j].getNumberOfParticipants(), actualMeetings[j].getNumberOfParticipants(),
                        "Meeting participants should match");
            }
        }
    }
}
