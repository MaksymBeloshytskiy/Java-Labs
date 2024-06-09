package lab01.t01.src.test;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import lab01.t01.src.Kyivstar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KyivstarTest {

    @Test
    public void testKyivstarAppWithValidInput() {
        String input = "+380675555555"; // Valid Kyivstar phone number
        String expectedOutput = "Введений номер телефону є номером оператора Київстар.";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Kyivstar.main(new String[0]);

        assertEquals(expectedOutput, getConsoleOutput());
    }

    @Test
    public void testKyivstarAppWithInvalidInput() {
        String input = "+380444444444"; // Invalid Kyivstar phone number
        String expectedOutput = "Введений номер телефону не є номером оператора Київстар.";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Kyivstar.main(new String[0]);

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
