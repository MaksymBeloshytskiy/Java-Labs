package edu.lab02_03;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import edu.lab02_03.AcademicGroup;
import edu.lab02_03.JsonTask;
import edu.lab02_03.Student;

public class JsonTaskTest {

    @Test
    void testCreateAcademicGroup() {
        AcademicGroup group = JsonTask.createAcademicGroup();
        assertNotNull(group);
        assertEquals("CS101", group.getName());
        assertEquals(2, group.getStudents().size());
        // Add more assertions for students if needed
    }

    @Test
    void testWriteAndReadJsonFiles() {
        // Test writing data to JSON file
        AcademicGroup group = JsonTask.createAcademicGroup();
        JsonTask.writeToJsonFiles(group);
        File file = new File("academic_group.json");
        assertTrue(file.exists());

        // Test reading data from JSON file
        JsonTask.processJsonFiles();
        // Add assertions to verify the data read from the file
    }

    @Test
    void testProcessJsonFilesForNonExistentFile() {
        // Test processing JSON files when the file does not exist
        assertThrows(IOException.class, JsonTask::processJsonFiles);
    }
}

