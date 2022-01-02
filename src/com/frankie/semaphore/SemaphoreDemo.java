package com.frankie.semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        SemaphoreDemo demo = new SemaphoreDemo();
        FankieSemaphore semaphore = new FankieSemaphore(5);
//        Semaphore semaphore = new Semaphore(5);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 8; i++) {
            String str = "VIP -- 00" + i;
            exec.submit(() -> {

                try {
                    semaphore.acquire();
                    demo.service(str);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
    }

    public void service(String string) throws InterruptedException {
        System.out.println(string + " come in");
        Thread.sleep(new Random().nextInt(3000));
        System.out.println(string + " out");
    }
}
