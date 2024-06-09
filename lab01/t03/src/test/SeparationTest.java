import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import main.Separation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeparationTest {

    private final PrintStream originalOutput = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOutput);
    }

    @Test
    public void testSeparationWithValidInput() {
        String inputString = "ab12c3def456gh789ij10";
        String expectedOutput = "Вхідний рядок: ab12c3def456gh789ij10\n" +
                "Знайдені підрядки:\n" +
                "ab\n" +
                "c\n" +
                "def\n" +
                "gh\n" +
                "ij";

        Separation.main(new String[0]);

        assertEquals(expectedOutput, getConsoleOutput().trim());
    }

    private String getConsoleOutput() {
        return outputStreamCaptor.toString().trim();
    }
}
