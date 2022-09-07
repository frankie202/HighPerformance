package com.frankie.jvm.savePoint;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SleepForSafePoint
 * Description:
 *
 * @Author Frankie
 * @Date 2022/9/7
 */
public class SleepForSafePoint {

    public static void main(String[] args) {
        testSleepForSavePoint(false);
        testSleepForSavePoint(true);
    }

    private static AtomicInteger num = new AtomicInteger(0);
    public static void testSleepForSavePoint(boolean sleepy) {

        ExecutorService exec = new ThreadPoolExecutor(2,2,1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 2; i++) {
            exec.execute(() -> {

                for (int j = 0; j < 100000000; j++) {
                    num.getAndAdd(1);
                }
                System.out.println(Thread.currentThread().getName() + " finish");
            });
        }

        if (sleepy) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("current num = " + num.get());

        exec.shutdown();
    }
}
