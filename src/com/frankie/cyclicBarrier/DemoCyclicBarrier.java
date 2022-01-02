package com.frankie.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoCyclicBarrier {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, ()-> {
            System.out.println("4 个线程执行");
            for (int i = 0; i < 4; i++) {
                System.out.println("---------");
            }
        });

        for (int i = 0; i < 10; i++) {
            exec.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println(" 插入完毕");
            });
        }
    }
}
