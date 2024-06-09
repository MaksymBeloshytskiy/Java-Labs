package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import main.BigDecimalOperationsJava8;

public class BigDecimalOperationsJava8Test {

    @Test
    public void testPositiveProduct() {
        List<BigDecimal> numbers = BigDecimalOperationsJava8.generateRandomBigDecimalList(10);
        BigDecimal product = BigDecimalOperationsJava8.calculatePositiveProduct(numbers);

        assertTrue(product.compareTo(BigDecimal.ZERO) >= 0);
    }
}
