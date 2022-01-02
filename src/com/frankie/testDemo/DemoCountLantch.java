package com.frankie.testDemo;

import java.util.concurrent.*;

/**
 * DemoCountLantch
 *
 * @author: Frankie
 */
public class DemoCountLantch {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(3);

        ExecutorService exec = new ThreadPoolExecutor(5,
                5,
                1000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 2; i++) {
            exec.execute(() -> {
                System.out.println(Thread.currentThread() + " wait......");
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + " run......");
            });
        }

        for (int i = 0; i < 3; i++) {
            exec.execute(() -> {
                latch.countDown();
                System.out.println(Thread.currentThread() + " run~~~~~~~~");
            });
        }

    }

}
