package edu.lab05_t02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathExpressionInterpreterTest {

    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    @BeforeEach
    public void setUpStreams() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    public void testMathExpressionInterpreter() {
        // Test a simple addition expression
        String input = "2 + 3";
        String expectedOutput = "Результат: 5\n";
        executeTest(input, expectedOutput);

        // Test a more complex expression
        input = "Math.pow(2, 3)";
        expectedOutput = "Результат: 8\n";
        executeTest(input, expectedOutput);

        // Test variable assignment and usage
        input = "x = 5";
        expectedOutput = "x = 5\n";
        executeTest(input, expectedOutput);

        input = "x + 3";
        expectedOutput = "Результат: 8\n";
        executeTest(input, expectedOutput);

        // Test division by zero
        input = "1 / 0";
        expectedOutput = "Помилка в виразі: Division by zero\n";
        executeTest(input, expectedOutput);
    }

    private void executeTest(String input, String expectedOutput) {
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        MathExpressionInterpreter.main(new String[]{});

        assertEquals(expectedOutput, outputStream.toString());
    }
}

