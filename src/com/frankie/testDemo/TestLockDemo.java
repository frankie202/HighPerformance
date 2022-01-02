package com.frankie.testDemo;

import com.frankie.aqs.TonyLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

public class TestLockDemo {
    volatile int i = 0;
    Lock lock = new TonyLock();
    public  void add() {
        lock.lock();
        try {
            i++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        TestLockDemo demo = new TestLockDemo();
        /*for (int j = 0; j < 4; j++) {
            exec.submit(() -> {
                for (int k = 0; k < 10000; k++) {
                    demo.add();
                }
            });
        }*/
        for (int j = 0; j < 4; j++) {
            new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    demo.add();
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(demo.i);
        new Thread().start();
    }
}
