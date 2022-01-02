package com.frankie.testDemo;

import java.util.concurrent.*;

public class DemoLinkedTransferQueue1 {

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(3,3,1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        exec.submit(() -> {
           queue.put( Thread.currentThread()+ " : one");
            System.out.println(Thread.currentThread() + " one");
        });

        exec.submit(() -> {
           queue.add(Thread.currentThread() + " :two");
            System.out.println(Thread.currentThread() + " two");
        });
    }
}
