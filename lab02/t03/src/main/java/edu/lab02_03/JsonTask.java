package edu.lab02_03;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONTokener;
import java.util.ArrayList;

public class JsonTask {
    public static void main(String[] args) {
        // Створення об'єктів студентів та академічної групи
        AcademicGroup group = createAcademicGroup();
        
        // Запис даних в JSON-файли
        writeToJsonFiles(group);
        
        // Читання даних з JSON-файлів та їх обробка
        processJsonFiles();
    }

    // Метод для створення об'єктів студентів та академічної групи
    private static AcademicGroup createAcademicGroup() {
        // Створення об'єктів студентів
        Student student1 = new Student("John", 20);
        Student student2 = new Student("Alice", 21);
        // Інші студенти...

        // Створення академічної групи та додавання студентів до неї
        AcademicGroup group = new AcademicGroup("CS101");
        group.addStudent(student1);
        group.addStudent(student2);
        // Додавання інших студентів...

        return group;
    }

    // Метод для запису даних в JSON-файли
    private static void writeToJsonFiles(AcademicGroup group) {
        try (FileWriter file = new FileWriter("academic_group.json")) {
            JSONObject jsonGroup = group.toJson(); // Конвертація академічної групи в JSON
            file.write(jsonGroup.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для читання даних з JSON-файлів та їх обробки
    private static void processJsonFiles() {
        try (FileReader reader = new FileReader("academic_group.json")) {
            // Читання JSON-об'єкта з файлу
            JSONObject jsonGroup = new JSONObject(new JSONTokener(reader));
            
            // Отримання даних з JSON та їх обробка
            String groupName = jsonGroup.getString("name");
            JSONArray studentsArray = jsonGroup.getJSONArray("students");
            for (int i = 0; i < studentsArray.length(); i++) {
                JSONObject jsonStudent = studentsArray.getJSONObject(i);
                String name = jsonStudent.getString("name");
                int age = jsonStudent.getInt("age");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class AcademicGroup {
    private String name;
    private ArrayList<Student> students;

    public AcademicGroup(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public JSONObject toJson() {
        JSONObject jsonGroup = new JSONObject();
        jsonGroup.put("name", name);
        JSONArray studentsArray = new JSONArray();
        for (Student student : students) {
            studentsArray.put(student.toJson());
        }
        jsonGroup.put("students", studentsArray);
        return jsonGroup;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public JSONObject toJson() {
        JSONObject jsonStudent = new JSONObject();
        jsonStudent.put("name", name);
        jsonStudent.put("age", age);
        return jsonStudent;
    }

    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
