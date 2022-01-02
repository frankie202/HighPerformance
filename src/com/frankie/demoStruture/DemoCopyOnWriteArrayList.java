package com.frankie.demoStruture;

import java.util.concurrent.*;

public class DemoCopyOnWriteArrayList {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        ExecutorService exec = new ThreadPoolExecutor(4,4,1000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        exec.execute(() -> {
           list.add(0,"key");

//           list.remove(1);
        });

    }
}
