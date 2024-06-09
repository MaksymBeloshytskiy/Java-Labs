package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import main.BigDecimalOperationsLoop;

public class BigDecimalOperationsLoopTest {

    @Test
    public void testPositiveProduct() {
        List<BigDecimal> numbers = BigDecimalOperationsLoop.generateRandomBigDecimalList(10);
        BigDecimal product = BigDecimalOperationsLoop.calculatePositiveProduct(numbers);

        assertTrue(product.compareTo(BigDecimal.ZERO) >= 0);
    }
}
