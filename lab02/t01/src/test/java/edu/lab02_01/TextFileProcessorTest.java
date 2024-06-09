package edu.lab02_01;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextFileProcessorTest {

    @Test
    void testSortAndFilterTextFile() {
        // Підготовка тестових даних
        List<String> inputLines = Arrays.asList(
                "Java",
                "Python",
                "C++",
                "JavaScript",
                "HTML",
                "CSS",
                "Ruby",
                "PHP",
                "Swift",
                "Kotlin"
        );
        Path inputFilePath = Path.of("input.txt");
        Path outputFilePath = Path.of("output.txt");
        try {
            // Запис тестових даних у вхідний файл
            Files.write(inputFilePath, inputLines);
            
            // Виконання коду, який ми тестуємо
            TextFileProcessor.processTextFile(inputFilePath, outputFilePath);

            // Перевірка вмісту вихідного файлу
            List<String> outputLines = Files.readAllLines(outputFilePath);
            List<String> expectedOutputLines = Arrays.asList(
                    "Java",
                    "Python",
                    "JavaScript",
                    "HTML",
                    "CSS",
                    "Ruby",
                    "PHP"
            );
            assertEquals(expectedOutputLines.size(), outputLines.size());
            assertTrue(expectedOutputLines.containsAll(outputLines));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Видалення тимчасових файлів після виконання тесту
            try {
                Files.deleteIfExists(inputFilePath);
                Files.deleteIfExists(outputFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
