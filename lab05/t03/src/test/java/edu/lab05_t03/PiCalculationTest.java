package edu.lab05_t03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PiCalculationTest {

    @Test
    public void testPiCalculation() {
        double epsilon = 0.0001; // Set a small epsilon for precision
        PiCalculator piCalculator = new PiCalculator(epsilon);

        Thread calculationThread = new Thread(piCalculator);
        calculationThread.start();

        // Wait for the calculation thread to complete
        try {
            calculationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Retrieve the calculated value of pi and the number of terms computed
        double calculatedPi = piCalculator.getCurrentPi();
        int termCount = piCalculator.getTermCount();

        // Verify the calculated value of pi using a known approximation
        double expectedPi = Math.PI;
        assertEquals(expectedPi, calculatedPi, epsilon, "Calculated pi does not match expected pi");

        // Verify that the number of terms computed is reasonable for the given epsilon
        int expectedTermCount = (int) (Math.PI / epsilon) + 1; // Expected term count based on epsilon
        assertEquals(expectedTermCount, termCount, "Number of terms computed is incorrect");
    }
}
