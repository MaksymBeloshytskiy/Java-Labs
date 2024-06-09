package edu.lab04_add00;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class FieldInfoTest {
    @Test
    public void testFieldInfo() {
        // Prepare input
        String input = "java.lang.String\n"; // Assuming java.lang.String has public fields

        // Redirect System.in
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Redirect System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the main method of FieldInfo class
        FieldInfo.main(new String[0]);

        // Restore System.in
        System.setIn(System.in);

        // Get the output
        String output = outputStream.toString();

        // Assert that the output contains expected message
        assertEquals("Public fields of class java.lang.String:\n", output.substring(0, 44));
    }
}

