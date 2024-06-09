package edu.lab04_t00;

import java.lang.reflect.Field;
import java.util.Scanner;

public class FieldViewer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the fully qualified name of the class (e.g., edu.lab04_ind.Conference):");
        String className = scanner.nextLine();

        try {
            Class<?> clazz = Class.forName(className);
            Field[] fields = clazz.getDeclaredFields();

            System.out.println("Fields of class " + className + ":");
            for (Field field : fields) {
                field.setAccessible(true); // Allow access to private fields
                System.out.println("Name: " + field.getName() + ", Type: " + field.getType().getName());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
