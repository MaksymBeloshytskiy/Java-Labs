package edu.lab02_01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TextFileProcessor {
    public static void main(String[] args) {
        // Вхідний та вихідний шляхи файлів
        Path inputFilePath = Paths.get("C:\\Users\\maksy\\OneDrive\\Documents\\Projects\\JavaLabs\\lab02\\t01\\target\\files\\input.txt");
        Path outputFilePath = Paths.get("C:\\Users\\maksy\\OneDrive\\Documents\\Projects\\JavaLabs\\lab02\\t01\\target\\files\\output.txt");

        try {
            // Прочитати рядки з вхідного файлу, відсортувати за довжиною та фільтруємо за наявністю літери "a"
            List<String> lines = Files.lines(inputFilePath)
                    .sorted(Comparator.comparingInt(String::length))
                    .filter(line -> line.contains("a"))
                    .collect(Collectors.toList());

            // Запис результуючих рядків у вихідний файл
            Files.write(outputFilePath, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
