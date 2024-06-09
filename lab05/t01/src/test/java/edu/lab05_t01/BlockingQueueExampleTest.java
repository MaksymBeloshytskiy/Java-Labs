package edu.lab05_t01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExampleTest {

    @Test
    public void testNumberProducer() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        int numbersToAdd = 5;

        BlockingQueueExample.NumberProducer producer = new BlockingQueueExample.NumberProducer(queue, numbersToAdd);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        producerThread.join();

        // Ensure all numbers are added to the queue
        for (int i = 1; i <= numbersToAdd; i++) {
            assertEquals(i, queue.take().intValue());
        }
    }
}

