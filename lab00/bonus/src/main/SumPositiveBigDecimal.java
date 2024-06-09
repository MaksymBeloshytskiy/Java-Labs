package lab00.bonus.src.main;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SumPositiveBigDecimal {
    public static void main(String[] args) {
        BigDecimal[] initialValues = { BigDecimal.valueOf(3.5), BigDecimal.valueOf(-2.2),
                                        BigDecimal.valueOf(1.8), BigDecimal.valueOf(-4.6),
                                        BigDecimal.valueOf(5.2) };

        List<BigDecimal> numbers = Arrays.asList(initialValues);

        BigDecimal sumOfPositive = numbers.stream()
                                          .filter(num -> num.compareTo(BigDecimal.ZERO) > 0)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Sum of positive elements: " + sumOfPositive);
    }
}
