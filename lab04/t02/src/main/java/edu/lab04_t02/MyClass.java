package edu.lab04_t02;

import java.lang.annotation.*;

class MyClass {
    @MyAnnotation("Annotation 1")
    public void method1() {
        // Method implementation
    }

    @MyAnnotation("Annotation 2")
    @AnotherAnnotation(description = "Another Annotation")
    public void method2() {
        // Method implementation
    }

    @MyAnnotation("Annotation 3")
    @AnotherAnnotation(description = "Yet Another Annotation")
    public void method3() {
        // Method implementation
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AnotherAnnotation {
    String description();
}
