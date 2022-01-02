package com.frankie.multiThread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * PrintABC
 *  顺序打印A->B->C
 * @author: Frankie
 */
public class PrintABC {
    private static ExecutorService exec = new ThreadPoolExecutor(3,
            3,
            1000L,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy());


    public static void printByCondition() {
        final ReentrantLock lock = new ReentrantLock();
        final Condition conditionA = lock.newCondition();
        final Condition conditionB = lock.newCondition();
        final Condition conditionC = lock.newCondition();

        exec.execute(() -> {
            lock.lock();
            try {

                System.out.println("A");
                conditionB.signal();
                conditionA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        exec.execute(() -> {
            lock.lock();
            try {
                System.out.println("B");
                conditionB.await();
                conditionC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        exec.execute(() -> {
            lock.lock();
            try {
                System.out.println("C");
                conditionC.await();
                conditionA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
    }


    public static void printBySemaphore() {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(0);

        exec.execute(() -> {
            while (true) {
                try {
                    semaphore1.acquire();
                    System.out.println("A");
                    semaphore2.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        exec.execute(() -> {
            while (true) {
                try {
                    semaphore2.acquire();
                    System.out.println("B");
                    semaphore3.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        exec.execute(() -> {
            while (true) {
                try {
                    semaphore3.acquire();
                    System.out.println("C");
                    semaphore1.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
    }
    public static void main(String[] args) {
        printBySemaphore();
    }
}
