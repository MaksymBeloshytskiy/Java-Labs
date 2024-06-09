package test;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import main.DataFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataFormatTest {

    @Test
    public void testDataFormatAppWithValidInput() {
        String input = "25.12.2023"; // Valid input for date format
        String expectedOutput = "LocalDate: 2023-12-25\n" +
                                "GregorianCalendar: Wed Dec 25 00:00:00 EET 2023\n" +
                                "Date: Wed Dec 25 00:00:00 EET 2023";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        DataFormat.main(new String[0]);

        assertEquals(expectedOutput, getConsoleOutput());
    }

    @Test
    public void testDataFormatAppWithInvalidInput() {
        String input = "12-25-2023"; // Invalid input for date format
        String expectedOutput = "Введений рядок не відповідає формату дати dd.mm.yyyy";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        DataFormat.main(new String[0]);

        assertEquals(expectedOutput, getConsoleOutput());
    }

    private String getConsoleOutput() {
        InputStream inputStream = System.in;
        System.setIn(System.in);

        InputStream capturedOutputStream = new ByteArrayInputStream(inputStream.toString().getBytes());
        System.setIn(capturedOutputStream);

        Scanner scanner = new Scanner(System.in);
        StringBuilder output = new StringBuilder();
        while (scanner.hasNextLine()) {
            output.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        return output.toString().trim();
    }
}
