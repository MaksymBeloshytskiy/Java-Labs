package edu.lab02_add02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ModifyIntegers2 {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            Files.lines(Paths.get(inputFile))
                 .map(Integer::parseInt)
                 .map(num -> num % 2 == 0 ? num / 2 : num * 2) // Парні / Непарні
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
