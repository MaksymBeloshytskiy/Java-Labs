package lab00.t02.src.main;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class DivisorsFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            System.out.print("Enter a positive integer: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid positive integer.");
                scanner.next(); // Consume invalid input
            }
            number = scanner.nextInt();
        } while (number <= 0);
        scanner.close();

        int[] divisors = findDivisors(number);
        System.out.println("Divisors of " + number + ": " + Arrays.toString(divisors));
    }

    public static int[] findDivisors(int number) {
        return IntStream.range(1, number + 1)
                        .filter(x -> number % x == 0)
                        .toArray();
    }
}
