package edu.lab04_t00;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FieldViewerTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream inputStream;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @Test
    public void testFieldViewerWithValidClassName() {
        // Prepare input
        String input = "edu.lab04_ind.Conference\n";

        // Redirect System.in
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Run the main method of FieldViewer class
        FieldViewer.main(new String[0]);

        // Get the output
        String output = outputStream.toString();

        // Assert that output contains fields information
        assertTrue(output.contains("Fields of class edu.lab04_ind.Conference:"));
        assertTrue(output.contains("Name: name, Type: java.lang.String"));
        assertTrue(output.contains("Name: place, Type: java.lang.String"));
        assertTrue(output.contains("Name: meetings, Type: java.util.List"));
    }

    @Test
    public void testFieldViewerWithInvalidClassName() {
        // Prepare input
        String input = "InvalidClass\n";

        // Redirect System.in
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Run the main method of FieldViewer class
        FieldViewer.main(new String[0]);

        // Get the output
        String output = outputStream.toString();

        // Assert that output contains error message
        assertTrue(output.contains("Class not found: InvalidClass"));
    }
}
