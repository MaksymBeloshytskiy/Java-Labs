package edu.lab05_ind00;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FunctionPlotterTest {

    private final FunctionPlotter functionPlotter = new FunctionPlotter();

    @Test
    public void testFormatFunction() {
        assertEquals("Math.sin(x)", functionPlotter.formatFunction("sin(x)"));
        assertEquals("Math.pow(x,2)", functionPlotter.formatFunction("x^2"));
        assertEquals("Math.sin(x)*Math.cos(x)", functionPlotter.formatFunction("sin(x) * cos(x)"));
    }

    @Test
    public void testGenerateXValues() {
        List<Double> xValues = functionPlotter.generateXValues(0, 1, 0.5);
        assertEquals(3, xValues.size());
        assertEquals(0.0, xValues.get(0));
        assertEquals(0.5, xValues.get(1));
        assertEquals(1.0, xValues.get(2));
    }

}
