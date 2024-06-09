package main;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BigDecimalOperationsLoop {

    public static void main(String[] args) {
        List<BigDecimal> numbers = generateRandomBigDecimalList(10);
        Collections.sort(numbers, (a, b) -> b.abs().compareTo(a.abs())); // Sort by decreasing absolute value

        BigDecimal product = BigDecimal.ONE;
        for (BigDecimal num : numbers) {
            if (num.compareTo(BigDecimal.ZERO) > 0) {
                product = product.multiply(num);
            }
        }

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
        List<BigDecimal> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(BigDecimal.valueOf(Math.random() * 100 - 50)); // Random values between -50 and 50
        }
        return list;
    }
}
