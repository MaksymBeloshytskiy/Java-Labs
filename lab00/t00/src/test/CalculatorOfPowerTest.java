package lab00.t00.src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import lab00.t00.src.main.CalculatorOfPower;

import java.math.BigInteger;

public class CalculatorOfPowerTest {
    @Test
    public void testCalculatePowerUsingPow() {
        BigInteger base = new BigInteger("12345");
        int exponent = 3;
        BigInteger expected = new BigInteger("1881365963625");
        BigInteger result = CalculatorOfPower.calculatePowerUsingPow(base, exponent);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCalculatePowerUsingMultiplication() {
        BigInteger base = new BigInteger("12345");
        int exponent = 3;
        BigInteger expected = new BigInteger("1881365963625");
        BigInteger result = CalculatorOfPower.calculatePowerUsingMultiplication(base, exponent);
        Assertions.assertEquals(expected, result);
    }
}
