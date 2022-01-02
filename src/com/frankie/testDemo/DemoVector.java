package com.frankie.testDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

public class DemoVector {
    static final Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        test2();
    }


    public static void test() {
        while (true) {
            for (int i = 0; i < 10; i++) {
//                System.out.println("add:");
                vector.add(i);
            }

            Thread remove = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
//                    System.out.println("remove:");
                    vector.remove(i);
                }
            });

            Thread get = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
//                    System.out.println("get:");
//                    System.out.println(vector.get(i));
                    vector.get(i);
                }
            });

            remove.start();
            get.start();

            while (Thread.activeCount() > 20);
        }
    }


    public static void test2() throws InterruptedException, ExecutionException {
        ExecutorService exec = new ThreadPoolExecutor(18,18,1000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()) {
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
//                printException(r, t);

                Future<?> f = (Future<?>) r;
                try {
                    Object o = f.get();
                    System.out.println(o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
            while (true) {
                for (int i = 0; i < 10; i++) {
                    vector.add(i);
                }
                exec.submit(() -> {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                });

                exec.submit(() -> {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                });


                while (Thread.activeCount() > 20) {
                    System.out.println("--------");
                }
        }
    }

    private static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone())
                    future.get();
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // ignore/reset
            }
        }
        if (t != null)
            System.out.println(t);
    }
}
