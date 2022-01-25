package com.frankie.other;

import java.util.concurrent.*;

/**
 * DeadLock
 *
 * @author: Frankie
 */
public class DeadLock {

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(2,
                2,
                1000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        final Object lock1 = new Object();
        final Object lock2 = new Object();

        exec.execute(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        exec.execute(() -> {
            synchronized (lock2) {
                try {
                    Thread.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        exec.shutdown();
    }
}
