package edu.lab03_t00;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testSortingNumbers() {
        ObservableList<Double> numbers = FXCollections.observableArrayList(3.5, -2.0, 1.0, -4.5, 2.5, -1.5);
        ObservableList<Double> expectedSortedNumbers = FXCollections.observableArrayList(3.5, 1.0, 2.5, -1.5, -2.0, -4.5);

        ObservableList<Double> sortedNumbers = Main.sortNumbers(numbers);

        assertEquals(expectedSortedNumbers, sortedNumbers);
    }

    @Test
    public void testSortingEmptyList() {
        ObservableList<Double> numbers = FXCollections.observableArrayList();
        ObservableList<Double> expectedSortedNumbers = FXCollections.observableArrayList();

        ObservableList<Double> sortedNumbers = Main.sortNumbers(numbers);

        assertEquals(expectedSortedNumbers, sortedNumbers);
    }

    @Test
    public void testSortingOnlyPositiveNumbers() {
        ObservableList<Double> numbers = FXCollections.observableArrayList(3.5, 2.0, 1.0, 4.5, 2.5, 1.5);
        ObservableList<Double> expectedSortedNumbers = FXCollections.observableArrayList(3.5, 2.0, 1.0, 4.5, 2.5, 1.5);

        ObservableList<Double> sortedNumbers = Main.sortNumbers(numbers);

        assertEquals(expectedSortedNumbers, sortedNumbers);
    }

    @Test
    public void testSortingOnlyNegativeNumbers() {
        ObservableList<Double> numbers = FXCollections.observableArrayList(-3.5, -2.0, -1.0, -4.5, -2.5, -1.5);
        ObservableList<Double> expectedSortedNumbers = FXCollections.observableArrayList(-3.5, -2.0, -1.0, -4.5, -2.5, -1.5);

        ObservableList<Double> sortedNumbers = Main.sortNumbers(numbers);

        assertEquals(expectedSortedNumbers, sortedNumbers);
    }

    @Test
    public void testSortingWithZero() {
        ObservableList<Double> numbers = FXCollections.observableArrayList(3.5, -2.0, 0.0, -4.5, 2.5, -1.5);
        ObservableList<Double> expectedSortedNumbers = FXCollections.observableArrayList(3.5, 0.0, 2.5, -1.5, -2.0, -4.5);

        ObservableList<Double> sortedNumbers = Main.sortNumbers(numbers);

        assertEquals(expectedSortedNumbers, sortedNumbers);
    }
}

