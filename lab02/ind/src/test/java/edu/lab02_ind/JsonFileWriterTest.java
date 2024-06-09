package edu.lab02_ind;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonFileWriterTest {

    private static final String TEST_FILE = "test_conferences.json";

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
    void testWriteData() {
        // Write data to the test file
        JsonFileWriter.writeData(conferences, TEST_FILE);

        // Check if the file exists
        assertTrue(Files.exists(Paths.get(TEST_FILE)), "File should exist");

        // Read the content of the file and verify it
        String content = null;
        try {
            content = Files.readString(Paths.get(TEST_FILE));
        } catch (IOException e) {
            fail("Failed to read file content: " + e.getMessage());
        }

        assertNotNull(content, "File content should not be null");

        // Verify that the JSON content contains conference names and places
        assertTrue(content.contains("FutureAI") && content.contains("Paris"), "Conference 1 should be present");
        assertTrue(content.contains("Data Analysis") && content.contains("Houston"), "Conference 2 should be present");
    }
}

