package edu.lab05_t00;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class PrimeFactorsAppTest {

    @Test
    public void testFindPrimeFactors() {
        PrimeFactorsApp primeFactorsApp = new PrimeFactorsApp();
        
        // Test for a prime number
        int primeNumber = 13;
        List<Integer> primeFactors = primeFactorsApp.findPrimeFactors(primeNumber);
        assertEquals(1, primeFactors.size());
        assertEquals(primeNumber, primeFactors.get(0));
        
        // Test for a non-prime number
        int nonPrimeNumber = 36;
        List<Integer> nonPrimeFactors = primeFactorsApp.findPrimeFactors(nonPrimeNumber);
        assertEquals(4, nonPrimeFactors.size());
        assertEquals(2, nonPrimeFactors.get(0));
        assertEquals(2, nonPrimeFactors.get(1));
        assertEquals(3, nonPrimeFactors.get(2));
    }
}
