package com.frankie.testDemo;

import java.util.concurrent.*;

public class DemoThreadBindCpu {

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(1,1
                ,0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());


        exec.submit(() -> {
            System.out.println("Thread before sleep:" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread after sleep:" + Thread.currentThread().getName());
        });

        for (int i = 0; i < 4; i++) {
            int finalI = i;
            exec.submit(() -> {
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                System.out.println("Thread " + finalI  + " "+ Thread.currentThread().getName());
            });
        }

        exec.shutdown();
    }
}
