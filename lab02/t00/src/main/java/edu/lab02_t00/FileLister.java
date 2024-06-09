package edu.lab02_t00;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileLister {
    public static void main(String[] args) {
        // Введення шляху теки з клавіатури
        String directoryPath = args.length > 0 ? args[0] : "";
        
        // Пошук файлів з використанням java.io.File
        System.out.println("Використання java.io.File:");
        listFilesUsingFile(directoryPath);
        
        // Пошук файлів з використанням java.nio.file
        System.out.println("\nВикористання java.nio.file:");
        listFilesUsingNIO(directoryPath);
    }

    // Метод для виведення списку файлів та підкаталогів з використанням java.io.File
    public static void listFilesUsingFile(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            System.out.println("Тека не існує.");
            return;
        }
        listFiles(directory);
    }

    private static void listFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
                if (file.isDirectory()) {
                    listFiles(file);
                }
            }
        }
    }

    // Метод для виведення списку файлів та підкаталогів з використанням java.nio.file
    public static void listFilesUsingNIO(String directoryPath) {
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні файлів.");
        }
    }
}
