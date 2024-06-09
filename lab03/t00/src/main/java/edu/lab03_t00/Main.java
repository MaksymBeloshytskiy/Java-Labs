package edu.lab03_t00;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main {
    public static void main(String[] args) {
        // Створення початкового списку дійсних чисел
        ObservableList<Double> numbers = FXCollections.observableArrayList(3.5, -2.0, 1.0, -4.5, 2.5, -1.5);
        
        // Виведення початкового списку
        System.out.println("Starter List:");
        System.out.println(numbers);
        
        // Створення нового списку, який міститиме додатні числа, а потім від'ємні у протилежному порядку
        ObservableList<Double> sortedNumbers = FXCollections.observableArrayList();
        
        // Додавання додатних чисел до нового списку
        for (Double number : numbers) {
            if (number > 0) {
                sortedNumbers.add(number);
            }
        }
        
        // Додавання від'ємних чисел у протилежному порядку до нового списку
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) < 0) {
                sortedNumbers.add(numbers.get(i));
            }
        }
        
        // Виведення відсортованого списку
        System.out.println("Sorted List:");
        System.out.println(sortedNumbers);
    }
}
