package com.frankie.demoStruture;

import java.util.concurrent.*;

public class DemoCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch count = new CountDownLatch(3);

        ExecutorService exec = new ThreadPoolExecutor(3,3,1000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 3; i++) {
            exec.submit(() -> {
                System.out.println(Thread.currentThread());
                count.countDown();
            });
        }

        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all threads execute finished");
    }
}
