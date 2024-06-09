package edu.lab02_02;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створення об'єктів студентів
        Student student1 = new Student("John Doe", 21, "Computer Science");
        Student student2 = new Student("Alice Smith", 20, "Mathematics");
        Student student3 = new Student("Bob Johnson", 22, "Physics");

        // Створення академічної групи та додавання до неї студентів
        AcademicGroup group = new AcademicGroup("CS101", "Computer Science");
        group.addStudent(student1);
        group.addStudent(student2);
        group.addStudent(student3);

        // Запис даних в XML файл
        writeToXML(group, "academic_group.xml");

        // Читання даних з XML файлу
        AcademicGroup restoredGroup = readFromXML("academic_group.xml");
        System.out.println("Restored Group:");
        System.out.println(restoredGroup);
    }

    // Метод для запису даних у XML файл
    public static void writeToXML(Object object, String fileName) {
        XStream xStream = new XStream(new DomDriver());
        String xml = xStream.toXML(object);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для читання даних з XML файлу
    public static AcademicGroup readFromXML(String fileName) {
        XStream xStream = new XStream(new DomDriver());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            return (AcademicGroup) xStream.fromXML(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// Клас Student
class Student implements Serializable {
    private String name;
    private int age;
    private String major;

    public Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

// Клас AcademicGroup
class AcademicGroup implements Serializable {
    private String groupCode;
    private String department;
    private List<Student> students;

    public AcademicGroup(String groupCode, String department) {
        this.groupCode = groupCode;
        this.department = department;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AcademicGroup{" +
                "groupCode='" + groupCode + '\'' +
                ", department='" + department + '\'' +
                ", students=\n");
        for (Student student : students) {
            stringBuilder.append(student.toString()).append("\n");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    } 

    public String getGroupCode() {
        return groupCode;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
