package com.frankie.multiThread.producerconsumer;

import java.util.Queue;
import java.util.Random;

/**
 * Producer
 *
 * @author: Frankie
 */
public class Producer {
    private Queue<Integer> queue;
    private int maxSize;

    Producer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    public void produce() {
        synchronized (queue) {
            while (queue.size() == maxSize) {
                try {
                    System.out.println("已满");
                    queue.wait();
                    System.out.println("已唤醒生产者---");
                } catch (InterruptedException e) {
                    System.out.println("生产者报错");
                }
            }
            Random random = new Random();
            int i = random.nextInt();
            System.out.println("Produce " + i);
            queue.add(i);
            queue.notify();
        }
    }
}
