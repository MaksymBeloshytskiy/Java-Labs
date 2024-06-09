package edu.lab02_add00;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RealNumbersSum {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            double sum = Files.lines(Paths.get(inputFile))
                              .mapToDouble(Double::parseDouble)
                              .sum();

            Files.writeString(Paths.get(outputFile), String.valueOf(sum));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

