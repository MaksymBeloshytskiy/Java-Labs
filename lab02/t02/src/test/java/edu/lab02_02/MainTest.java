package edu.lab02_02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {

    @Test
    void testWriteAndReadFromXML() {
        // Створення об'єктів студентів
        Student student1 = new Student("John Doe", 21, "Computer Science");
        Student student2 = new Student("Alice Smith", 20, "Mathematics");
        Student student3 = new Student("Bob Johnson", 22, "Physics");

        // Створення академічної групи та додавання до неї студентів
        AcademicGroup group = new AcademicGroup("CS101", "Computer Science");
        group.addStudent(student1);
        group.addStudent(student2);
        group.addStudent(student3);

        // Запис даних у XML файл
        Main.writeToXML(group, "test_group.xml");

        // Читання даних з XML файлу
        AcademicGroup restoredGroup = Main.readFromXML("test_group.xml");

        // Перевірка, що група була відновлена
        assertNotNull(restoredGroup);

        // Перевірка, що назва групи збігається
        assertEquals(group.getGroupCode(), restoredGroup.getGroupCode());

        // Перевірка, що кількість студентів збігається
        assertEquals(group.getStudents().size(), restoredGroup.getStudents().size());
    }
}
