package com.frankie.multiThread;

import java.util.concurrent.*;

/**
 * MultiCoreTest
 *
 * @author: Frankie
 */
public class MultiCoreTest {
    static int NUM = 4;
    public static void main(String[] args) {

        ExecutorService exec = new ThreadPoolExecutor(NUM,
                NUM,
                1000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < NUM; i++) {
            exec.execute(() -> {
                int sum = 0;
                while (true) {
                    sum++;
                }
            });
        }
    }
}
