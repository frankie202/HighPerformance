package com.frankie.demoStruture;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DemoCondition {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cond = lock.newCondition();
    static boolean flag = false;

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(3,3,1000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        exec.submit(() -> {
            lock.lock();
            try {
                if (!flag) {
                    cond.await();
                }
                System.out.println("out await!");
            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        });

        exec.submit(() -> {
            lock.lock();
            try {
                if (!flag) {
//                    cond.signalAll();
                    flag = true;
                }
                System.out.println("Await");
            } finally {
                lock.unlock();
            }
        });
    }
}
