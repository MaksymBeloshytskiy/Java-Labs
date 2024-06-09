package main;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BigDecimalOperationsJava8 {

    public static void main(String[] args) {
        List<BigDecimal> numbers = generateRandomBigDecimalList(10);
        numbers.sort((a, b) -> b.abs().compareTo(a.abs())); // Sort by decreasing absolute value

        BigDecimal product = numbers.stream()
                                    .filter(num -> num.compareTo(BigDecimal.ZERO) > 0)
                                    .reduce(BigDecimal.ONE, BigDecimal::multiply);

        System.out.println("Product of positive numbers: " + product);
    }

    public static BigDecimal calculatePositiveProduct(List<BigDecimal> numbers) {
        BigDecimal product = BigDecimal.ONE;
        for (BigDecimal num : numbers) {
            if (num.compareTo(BigDecimal.ZERO) > 0) {
                product = product.multiply(num);
            }
        }
        return product;
    }

    public static List<BigDecimal> generateRandomBigDecimalList(int size) {
        return IntStream.range(0, size)
                        .mapToObj(i -> BigDecimal.valueOf(Math.random() * 100 - 50))
                        .collect(Collectors.toList()); // Random values between -50 and 50
    }
}
