package edu.lab05_t03;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PiCalculation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть значення ε для точності обчислення π: ");
        double epsilon = scanner.nextDouble();

        PiCalculator piCalculator = new PiCalculator(epsilon);
        Thread calculationThread = new Thread(piCalculator);
        calculationThread.start();

        while (true) {
            System.out.print("Введіть 'status' для отримання поточного значення π, 'count' для кількості обчислених елементів або 'exit' для завершення: ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("exit")) {
                piCalculator.stopCalculation();
                break;
            } else if (input.equalsIgnoreCase("status")) {
                System.out.println("Поточне значення π: " + piCalculator.getCurrentPi());
            } else if (input.equalsIgnoreCase("count")) {
                System.out.println("Кількість обчислених елементів: " + piCalculator.getTermCount());
            }
        }

        try {
            calculationThread.join();
            System.out.println("Остаточне значення π: " + piCalculator.getCurrentPi());
            System.out.println("Кількість обчислених елементів: " + piCalculator.getTermCount());
        } catch (InterruptedException e) {
            System.out.println("Обчислювальний потік був перерваний.");
        }

        scanner.close();
    }
}

class PiCalculator implements Runnable {
    private final double epsilon;
    private final AtomicReference<Double> currentPi = new AtomicReference<>(0.0);
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final AtomicInteger termCount = new AtomicInteger(0);

    public PiCalculator(double epsilon) {
        this.epsilon = epsilon;
    }

    public double getCurrentPi() {
        return currentPi.get();
    }

    public int getTermCount() {
        return termCount.get();
    }

    public void stopCalculation() {
        running.set(false);
    }

    @Override
    public void run() {
        double pi = 0.0;
        double term;
        int i = 0;

        while (running.get()) {
            term = 4.0 * Math.pow(-1, i) / (2 * i + 1);
            pi += term;

            currentPi.set(pi);
            termCount.incrementAndGet();

            if (Math.abs(term) < epsilon) {
                running.set(false);
                break;
            }

            i++;

            try {
                Thread.sleep(100); // Затримка для імітації довготривалих обчислень
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
