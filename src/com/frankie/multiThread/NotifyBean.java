package com.frankie.multiThread;

import java.util.concurrent.*;

/**
 * NotifyBean
 *
 * @author: Frankie
 */
public class NotifyBean {
    private int value = 0;
    private Object obj = new Object();

    public int get() {
        synchronized (obj) {
            int res = 0;
            while (value <= 0) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            res = value--;
            obj.notify();
            return res;
        }
    }

    public void set() {
        synchronized (obj) {
            while (value > 0) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            value++;
            obj.notify();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(3,
                3,1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        final NotifyBean bean = new NotifyBean();

        exec.execute(bean::get);
        exec.execute(bean::get);
        exec.execute(bean::set);
        exec.shutdown();
    }
}
