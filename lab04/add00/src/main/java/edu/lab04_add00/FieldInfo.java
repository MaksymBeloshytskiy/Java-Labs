package edu.lab04_add00;

import java.lang.reflect.Field;
import java.util.Scanner;

public class FieldInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user to enter the class name
        System.out.println("Enter the class name:");
        String className = scanner.nextLine();

        scanner.close();

        try {
            // Load the class by name
            Class<?> cls = Class.forName(className);

            // Get all public fields of the class
            Field[] fields = cls.getFields();

            if (fields.length == 0) {
                System.out.println("The class has no public fields");
            } else {
                System.out.println("Public fields of class " + className + ":");
                for (Field field : fields) {
                    System.out.println(field.getName());
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e);
        }
    }
}
