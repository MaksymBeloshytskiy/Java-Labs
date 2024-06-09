package lab01.t03.src;

import java.util.ArrayList;
import java.util.List;

public class Separation {
    public static void main(String[] args) {
        String inputString = "ab12c3def456gh789ij10";
        System.out.println("Вхідний рядок: " + inputString);

        // Створення списку для зберігання підрядків
        List<String> substrings = new ArrayList<>();
        
        // Змінна для зберігання поточного підрядка
        StringBuilder currentSubstring = new StringBuilder();
        
        // Перебір символів у вхідному рядку
        for (char c : inputString.toCharArray()) {
            if (Character.isDigit(c)) {
                // Якщо зустрілася цифра, додати поточний підрядок до списку і очистити його
                if (currentSubstring.length() > 0) {
                    substrings.add(currentSubstring.toString());
                    currentSubstring.setLength(0);
                }
            } else {
                // Додати символ до поточного підрядка
                currentSubstring.append(c);
            }
        }
        
        // Додати останній підрядок до списку (якщо він існує)
        if (currentSubstring.length() > 0) {
            substrings.add(currentSubstring.toString());
        }
        
        // Вивести знайдені підрядки
        System.out.println("Знайдені підрядки:");
        for (String substring : substrings) {
            System.out.println(substring);
        }
    }
}
