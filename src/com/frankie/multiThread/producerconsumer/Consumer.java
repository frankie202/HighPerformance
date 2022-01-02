package com.frankie.multiThread.producerconsumer;

import java.util.Queue;

/**
 * Consumer
 *
 * @author: Frankie
 */
public class Consumer {

    private Queue<Integer> queue;
    private int maxSize;

    Consumer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    public void consume() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    System.out.println("已空");
                    queue.wait();
                    System.out.println("已唤醒消费者---");
                } catch (InterruptedException e) {
                    System.out.println("消费者报错");
                }
            }
            int v = queue.remove();
            System.out.println("Consume " + v);
            queue.notify();
        }
    }
}
