package edu.lab02_t00;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

class FileListerTest {

    @Test
    void testListFilesUsingFile() {
        // Створюємо тимчасову теку для тестування
        File tempDir = new File(System.getProperty("java.io.tmpdir"), "testDir");
        tempDir.mkdirs();

        // Створюємо тимчасові файли та підкаталоги
        File file1 = new File(tempDir, "file1.txt");
        File file2 = new File(tempDir, "file2.txt");
        File subDir = new File(tempDir, "subDir");
        subDir.mkdirs();
        File file3 = new File(subDir, "file3.txt");

        try {
            // Відновлюємо стандартний вивід
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // Викликаємо метод для тестування
            FileLister.listFilesUsingFile(tempDir.getAbsolutePath());

            // Отримуємо очікуваний результат
            String expectedOutput = file1.getAbsolutePath() + System.lineSeparator() +
                    file2.getAbsolutePath() + System.lineSeparator() +
                    subDir.getAbsolutePath() + System.lineSeparator() +
                    file3.getAbsolutePath() + System.lineSeparator();

            // Перевіряємо, чи виведено правильний результат
            assertEquals(expectedOutput, outContent.toString());
        } finally {
            // Видаляємо тимчасові файли та теку
            file1.delete();
            file2.delete();
            file3.delete();
            subDir.delete();
            tempDir.delete();
        }
    }

    @Test
    void testListFilesUsingNIO() {
        // Створюємо тимчасову теку для тестування
        File tempDir = new File(System.getProperty("java.io.tmpdir"), "testDir");
        tempDir.mkdirs();

        // Створюємо тимчасові файли та підкаталоги
        File file1 = new File(tempDir, "file1.txt");
        File file2 = new File(tempDir, "file2.txt");
        File subDir = new File(tempDir, "subDir");
        subDir.mkdirs();
        File file3 = new File(subDir, "file3.txt");

        try {
            // Відновлюємо стандартний вивід
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // Викликаємо метод для тестування
            FileLister.listFilesUsingNIO(tempDir.getAbsolutePath());

            // Отримуємо очікуваний результат
            String expectedOutput = file1.toPath() + System.lineSeparator() +
                    file2.toPath() + System.lineSeparator() +
                    subDir.toPath() + System.lineSeparator() +
                    file3.toPath() + System.lineSeparator();

            // Перевіряємо, чи виведено правильний результат
            assertEquals(expectedOutput, outContent.toString());
        } finally {
            // Видаляємо тимчасові файли та теку
            file1.delete();
            file2.delete();
            file3.delete();
            subDir.delete();
            tempDir.delete();
        }
    }

    @Test
    void testListFilesUsingFileWithNonExistingDirectory() {
        // Встановлюємо ім'я неіснуючої теки
        String nonExistingDir = "nonExistingDir";

        // Відновлюємо стандартний вивід
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Викликаємо метод для тестування
        FileLister.listFilesUsingFile(nonExistingDir);

        // Отримуємо очікуваний результат
        String expectedOutput = "Тека не існує." + System.lineSeparator();

        // Перевіряємо, чи виведено правильний результат
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testListFilesUsingNIOWithNonExistingDirectory() {
        // Встановлюємо ім'я неіснуючої теки
        String nonExistingDir = "nonExistingDir";

        // Відновлюємо стандартний вивід
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Викликаємо метод для тестування
        FileLister.listFilesUsingNIO(nonExistingDir);

        // Отримуємо очікуваний результат
        String expectedOutput = "Тека не існує." + System.lineSeparator();

        // Перевіряємо, чи виведено правильний результат
        assertEquals(expectedOutput, outContent.toString());
    }
}
