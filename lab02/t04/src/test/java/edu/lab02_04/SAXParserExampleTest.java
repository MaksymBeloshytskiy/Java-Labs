package edu.lab02_04;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SAXParserExampleTest {

    @Test
    void testSAXParserOutput() {
        // Збережемо поточний стандартний вивід
        PrintStream originalOut = System.out;

        try {
            // Створимо тестовий XML-рядок
            String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<students>" +
                    "    <student>" +
                    "        <id>1</id>" +
                    "        <firstName>John</firstName>" +
                    "        <lastName>Doe</lastName>" +
                    "        <group>CS101</group>" +
                    "    </student>" +
                    "    <student>" +
                    "        <id>2</id>" +
                    "        <firstName>Jane</firstName>" +
                    "        <lastName>Smith</lastName>" +
                    "        <group>CS102</group>" +
                    "    </student>" +
                    "</students>";

            // Встановимо новий вивід для тесту
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // Викличемо метод парсингу з тестовими даними
            SAXParserExample.main(new String[0]);

            // Перевіримо, чи виведені очікувані дані
            assertTrue(outContent.toString().contains("ID: 1"));
            assertTrue(outContent.toString().contains("Ім'я: John"));
            assertTrue(outContent.toString().contains("Прізвище: Doe"));
            assertTrue(outContent.toString().contains("Група: CS101"));
            assertTrue(outContent.toString().contains("ID: 2"));
            assertTrue(outContent.toString().contains("Ім'я: Jane"));
            assertTrue(outContent.toString().contains("Прізвище: Smith"));
            assertTrue(outContent.toString().contains("Група: CS102"));
        } finally {
            // Відновимо стандартний вивід
            System.setOut(originalOut);
        }
    }
}

