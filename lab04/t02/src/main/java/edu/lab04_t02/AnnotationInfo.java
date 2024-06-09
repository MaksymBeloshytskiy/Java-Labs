package edu.lab04_t02;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Scanner;

public class AnnotationInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user to enter the class name and method name
        System.out.println("Enter the class name:");
        String className = scanner.nextLine();

        System.out.println("Enter the method name:");
        String methodName = scanner.nextLine();

        scanner.close();

        try {
            // Load the class by name
            Class<?> cls = Class.forName(className);

            // Get the method object by name
            Method method = cls.getMethod(methodName);

            // Get the annotations of the method
            Annotation[] annotations = method.getDeclaredAnnotations();

            if (annotations.length == 0) {
                System.out.println("The method has no annotations");
            } else {
                System.out.println("Annotations of the method " + methodName + ":");
                for (Annotation annotation : annotations) {
                    System.out.println(annotation);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e);
        } catch (NoSuchMethodException e) {
            System.out.println("Method not found: " + e);
        }
    }
}
