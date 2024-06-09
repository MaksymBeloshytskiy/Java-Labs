package edu.lab03_t01;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorAppTest {

    @Test
    public void testAddition() {
        CalculatorApp calculator = new CalculatorApp();
        calculator.numberField1.setText("5");
        calculator.numberField2.setText("3");
        calculator.calculate();
        assertEquals("8.0", calculator.resultField.getText());
    }

    @Test
    public void testSubtraction() {
        CalculatorApp calculator = new CalculatorApp();
        calculator.numberField1.setText("5");
        calculator.numberField2.setText("3");
        calculator.operationGroup.selectToggle(calculator.subtractRadioButton);
        calculator.calculate();
        assertEquals("2.0", calculator.resultField.getText());
    }

    @Test
    public void testMultiplication() {
        CalculatorApp calculator = new CalculatorApp();
        calculator.numberField1.setText("5");
        calculator.numberField2.setText("3");
        calculator.operationGroup.selectToggle(calculator.multiplyRadioButton);
        calculator.calculate();
        assertEquals("15.0", calculator.resultField.getText());
    }

    @Test
    public void testDivision() {
        CalculatorApp calculator = new CalculatorApp();
        calculator.numberField1.setText("10");
        calculator.numberField2.setText("2");
        calculator.operationGroup.selectToggle(calculator.divideRadioButton);
        calculator.calculate();
        assertEquals("5.0", calculator.resultField.getText());
    }

    @Test
    public void testDivisionByZero() {
        CalculatorApp calculator = new CalculatorApp();
        calculator.numberField1.setText("10");
        calculator.numberField2.setText("0");
        calculator.operationGroup.selectToggle(calculator.divideRadioButton);
        calculator.calculate();
        assertEquals("Error: Division by zero", calculator.resultField.getText());
    }
}

