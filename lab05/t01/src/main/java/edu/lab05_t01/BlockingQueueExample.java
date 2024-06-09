package edu.lab05_t01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    private static final int QUEUE_CAPACITY = 10;
    private static final int NUMBERS_TO_ADD = 20;

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

        Thread producerThread = new Thread(new NumberProducer(queue, NUMBERS_TO_ADD));
        Thread consumerThread = new Thread(new AverageCalculator(queue));

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.interrupt();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class NumberProducer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final int numbersToAdd;

        public NumberProducer(BlockingQueue<Integer> queue, int numbersToAdd) {
            this.queue = queue;
            this.numbersToAdd = numbersToAdd;
        }

        @Override
        public void run() {
            for (int i = 1; i <= numbersToAdd; i++) {
                try {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class AverageCalculator implements Runnable {
        private final BlockingQueue<Integer> queue;

        public AverageCalculator(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                int sum = 0;
                int count = 0;

                while (!Thread.currentThread().isInterrupted()) {
                    Integer number = queue.take();
                    sum += number;
                    count++;
                    double average = (double) sum / count;
                    System.out.println("Consumed: " + number + ", Current Average: " + average);
                    System.out.println();
                }
            } catch (InterruptedException e) {
                // Thread interrupted, exit gracefully
                System.out.println("Average calculation interrupted.");
            }
        }
    }
}
