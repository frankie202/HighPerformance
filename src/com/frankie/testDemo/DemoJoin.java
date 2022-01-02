package com.frankie.testDemo;

/**
 * DemoJoin
 *
 * @author: Frankie
 */
public class DemoJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000L);
                System.out.println(Thread.currentThread()+" t1 .....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                Thread.sleep(2000L);
                System.out.println(Thread.currentThread() + " t2 .....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
//        t1.join();

        System.out.println("main.....");
    }
}
