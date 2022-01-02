package com.frankie.testDemo;

import java.util.concurrent.*;

public class DemoScheduleThread {

    public static void main(String[] args) {
        ScheduledExecutorService exec = new ScheduledThreadPoolExecutor(1,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        exec.scheduleAtFixedRate(() -> {
            System.out.println("Hello World!");
        },0L,2000,TimeUnit.MILLISECONDS);
    }
}
