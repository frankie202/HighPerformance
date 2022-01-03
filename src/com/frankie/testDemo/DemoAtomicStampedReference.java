package com.frankie.testDemo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * DemoAtomicStampedReference
 *
 * @author: Frankie
 */
public class DemoAtomicStampedReference {

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(3,
                3,
                1000L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        AtomicInteger value = new AtomicInteger(0);

        exec.submit(() -> {
            while (true) {
                int val = value.get();
                if (val == 0) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    value.compareAndSet(0,1);
                    break;
                }
            }
            System.out.println(value.get());
        });

        exec.submit(() -> {
           while (true) {
               int val = value.get();
               value.compareAndSet(val,val +1);
               System.out.println("----------");
               val = value.get();
               value.compareAndSet(val,val - 1);
               break;
           }
        });


        exec.shutdown();
    }


    public static void test(ExecutorService exec) {
        final Counter counter = new Counter();
        exec.submit(() -> {
            int increment = counter.increment();
            System.out.println("val1 = " + increment + " ref1 = " + counter.getStamp());
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int val = counter.decrement();
            System.out.println("val2 = " + val + " ref2 = " + counter.getStamp());
        });

        exec.submit(() -> {
            try {
                Thread.sleep(15L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = counter.get();
            System.out.println("val3 = " + i + " ref3 = " + counter.getStamp());
        });
    }
    static class Counter {
        AtomicStampedReference<Integer> ref = new AtomicStampedReference<Integer>(0,0);

        public int get() {
            return ref.getReference();
        }
        public int getStamp() {
            return ref.getStamp();
        }

        public int increment() {
            int[] stamp = new int[1];
            while (true) {
                Integer val = ref.get(stamp);
                int newVal = val +1;
                boolean set = ref.compareAndSet(val, newVal, stamp[0], stamp[0] + 1);
                if (set) {
                    return newVal;
                }
            }
        }

        public int decrement() {
            int[] stamp = new int[1];
            while (true) {
                Integer val = ref.get(stamp);
                int newVal = val - 1;

                boolean ok = ref.compareAndSet(val, newVal, stamp[0], stamp[0] + 1);
                if (ok) {
                    return newVal;
                }
            }
        }
    }
}
