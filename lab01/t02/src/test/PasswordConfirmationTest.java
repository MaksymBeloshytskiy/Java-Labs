import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import main.PasswordConfirmation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordConfirmationTest {

    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    public void testPasswordConfirmationWithValidPassword() {
        String input = "Strong@123"; // Valid password
        String expectedOutput = "Пароль відповідає вимогам безпеки.";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        PasswordConfirmation.main(new String[0]);

        assertEquals(expectedOutput, getConsoleOutput());
    }

    @Test
    public void testPasswordConfirmationWithInvalidPassword() {
        String input = "weak"; // Invalid password
        String expectedOutput = "Пароль не відповідає вимогам безпеки."
                + System.lineSeparator()
                + "Пароль повинен містити принаймні одну маленьку літеру, одну велику літеру, одну цифру, один спеціальний символ (_ - *), і бути принаймні 8 символів у довжину.";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        PasswordConfirmation.main(new String[0]);

        assertEquals(expectedOutput, getConsoleOutput());
    }

    private String getConsoleOutput() {
        return outputStreamCaptor.toString().trim();
    }
}
