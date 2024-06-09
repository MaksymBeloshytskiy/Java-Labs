package edu.lab02_04;

import org.junit.jupiter.api.Test;

import java.io.File;
import edu.lab02_04.DOMParserExample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DOMParserExampleTest {

    @Test
    void testDOMParser() {
        // Перевіримо, чи успішно відбувається модифікація XML-файлу
        DOMParserExample.main(new String[0]);

        // Перевіримо, чи був створений новий файл
        File modifiedFile = new File("modified_students.xml");
        assertTrue(modifiedFile.exists());

        // Перевіримо, чи змінено групу у всіх студентів
        XMLReader xmlReader = new XMLReader(modifiedFile);
        assertEquals("CS103", xmlReader.getGroupById(1));
        assertEquals("CS103", xmlReader.getGroupById(2));

        // Видалення створеного файлу після тесту
        modifiedFile.delete();
    }
}

