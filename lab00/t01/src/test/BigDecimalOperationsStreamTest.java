package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import main.BigDecimalOperationsStream;

public class BigDecimalOperationsStreamTest {

    @Test
    public void testPositiveProduct() {
        List<BigDecimal> numbers = BigDecimalOperationsStream.generateRandomBigDecimalList(10);
        BigDecimal product = BigDecimalOperationsStream.calculatePositiveProduct(numbers);

        assertTrue(product.compareTo(BigDecimal.ZERO) >= 0);
    }
}
