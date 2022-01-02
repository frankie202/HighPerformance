package com.frankie.demoStruture;

import java.util.concurrent.*;

public class DemoCyclicBarrier {

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(3,3,1000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        CyclicBarrier barrier = new CyclicBarrier(4);

        for (int i = 0; i < 3; i++) {
            exec.submit(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + " come out!");
            });
        }

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Main Thread out ");
    }
}
