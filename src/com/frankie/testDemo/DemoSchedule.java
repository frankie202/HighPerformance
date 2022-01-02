package com.frankie.testDemo;

import java.util.concurrent.*;

public class DemoSchedule {

    public static void main(String[] args) {
        ScheduledExecutorService exe = Executors.newSingleThreadScheduledExecutor();

        exe.scheduleAtFixedRate(
                () -> {
                    System.out.println("Schedule out ...");
                }, 0,4,TimeUnit.SECONDS
        );

    }
}
