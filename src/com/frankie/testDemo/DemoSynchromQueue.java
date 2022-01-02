package com.frankie.testDemo;

import java.util.concurrent.*;

public class DemoSynchromQueue {
    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(3,3,1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        exec.submit(() -> {
           queue.offer(Thread.currentThread() + " one");
        });

        exec.submit(() -> {
            try {
                queue.put(Thread.currentThread() + " two");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
