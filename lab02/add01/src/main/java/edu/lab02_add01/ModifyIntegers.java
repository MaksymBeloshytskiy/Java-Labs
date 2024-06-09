package edu.lab02_add01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ModifyIntegers {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            Files.lines(Paths.get(inputFile))
                 .map(Integer::parseInt)
                 .map(Math::abs) // Заміна від'ємних значень модулями
                 .map(num -> num < 0 ? Math.abs(num) : 0) // Додатні значення - нулі
                 .forEach(num -> {
                     try {
                         Files.writeString(Paths.get(outputFile), num + "\n");
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

