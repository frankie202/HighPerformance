package com.frankie.demoStruture;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WReentrantWriteReadLock {

    public static void main(String[] args) {

        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();


        /*readLock.lock();
        readLock.unlock();

        writeLock.lock();
        writeLock.unlock();*/
        ExecutorService exec = new ThreadPoolExecutor(4, 4, 1000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        /*exec.submit(() -> {
            readLock.lock();
            System.out.println("get read lock.....");
        });*/

        exec.submit(() -> {
            writeLock.lock();
            System.out.println("get write lock.....");
        });


        exec.submit(() -> {
            readLock.lock();
        });
    }
}
