package com.frankie.multiThread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SequencePrint
 *  顺序打印A->B->C
 *
 * @author: Frankie
 */
public class SequencePrint {

    public static void sequencePrint() {
        ExecutorService exec = new ThreadPoolExecutor(3,
                3,
                1000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        final ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        exec.execute(() -> {
            while (true) {
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
            }
        });

        exec.execute(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println("B");
                    conditionC.signal();
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        exec.submit(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println("C");
                    conditionA.signal();
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
    }

    public static void main(String[] args) {
        sequencePrint();
    }
}
