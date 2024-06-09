package lab00.bonus.src.main;


import java.math.BigInteger;
import java.util.Arrays;

public class ReplaceNegativesWithAbsolute {
    public static void main(String[] args) {
        BigInteger[] numbersArray = { BigInteger.valueOf(-5), BigInteger.valueOf(10),
                                      BigInteger.valueOf(-3), BigInteger.valueOf(7),
                                      BigInteger.valueOf(-8) };

        BigInteger[] modifiedArray = Arrays.stream(numbersArray)
                                          .map(num -> num.compareTo(BigInteger.ZERO) < 0 ? num.abs() : BigInteger.ZERO)
                                          .toArray(BigInteger[]::new);

        System.out.println("Modified Array: " + Arrays.toString(modifiedArray));
    }
}
