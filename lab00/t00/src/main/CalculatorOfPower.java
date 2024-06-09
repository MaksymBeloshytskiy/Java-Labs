package lab00.t00.src.main;

import java.math.BigInteger;
import java.security.SecureRandom;

public class CalculatorOfPower {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) {
        BigInteger base = generateRandomBigInteger(100);
        int exponent = 10;

        BigInteger power1 = calculatePowerUsingPow(base, exponent);
        BigInteger power2 = calculatePowerUsingMultiplication(base, exponent);

        System.out.println("Base number: " + base);
        System.out.println("Exponent: " + exponent);
        System.out.println("Power using pow(): " + power1);
        System.out.println("Power using multiplication: " + power2);
        System.out.println("Results are equal: " + power1.equals(power2));
    }

    private static BigInteger generateRandomBigInteger(int numDigits) {
        byte[] numBytes = new byte[numDigits / 2];
        RANDOM.nextBytes(numBytes);
        return new BigInteger(numDigits, new SecureRandom(numBytes)).abs();
    }

    public static BigInteger calculatePowerUsingPow(BigInteger base, int exponent) {
        return base.pow(exponent);
    }

    public static BigInteger calculatePowerUsingMultiplication(BigInteger base, int exponent) {
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < exponent; i++) {
            result = result.multiply(base);
        }
        return result;
    }
}
