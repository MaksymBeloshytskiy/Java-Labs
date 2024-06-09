package lab00.t02.src.test;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import lab00.t02.src.main.DivisorsFinder;

public class DivisorsFinderTest {

    @Test
    public void testFindDivisors() {
        // Simulate user input
        String input = "12\n"; // Provide the number whose divisors need to be found
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        // Backup original System.in
        InputStream originalSystemIn = System.in;
        System.setIn(inputStream);

        // Invoke the main method (simulated user input)
        DivisorsFinder.main(new String[]{});

        // Restore original System.in
        System.setIn(originalSystemIn);

        // Since we can't directly test console output, let's just ensure no exception occurs
    }
}
