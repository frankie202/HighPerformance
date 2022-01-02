package com.frankie.multiThread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * NotifyWaitDemo
 *
 * @author: Frankie
 */
public class NotifyWaitDemo {

    private Object lock = new Object();
    private Integer value = null;

    public void set(Integer value) {
        synchronized (lock) {
            while (this.value != null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.value = value;
            lock.notifyAll();
        }
    }

    public int get() {
        synchronized (lock) {
            while (this.value == null) {
//                System.out.println("----------");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("=============");
            }
            Integer val = value;
            value = null;
            lock.notifyAll();
            return val;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        int size = 80;
        ExecutorService exec = new ThreadPoolExecutor(size,size,1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        final NotifyWaitDemo bean = new NotifyWaitDemo();
        for (int i = 0; i < 40; i++) {
            exec.execute(()->{
                int i1 = bean.get();
                System.out.println(Thread.currentThread().toString() + i1);
            });
        }

        Thread.sleep(200L);

        for (int i = 0; i < 40; i++) {
            final int fi = i;
            exec.submit(() -> {
                bean.set(fi);
            });
        }

        exec.shutdown();
    }
}
