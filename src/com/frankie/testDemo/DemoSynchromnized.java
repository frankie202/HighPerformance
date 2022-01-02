package com.frankie.testDemo;

import java.util.concurrent.*;

public class DemoSynchromnized {

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        ExecutorService exec = new ThreadPoolExecutor(3,3,1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

//        exec.submit(new Consumer(queue));

        exec.submit(new Producer(queue));
        exec.submit(new Producer(queue));

//        exec.submit(new Consumer(queue));
        exec.shutdown();
    }

    static class  Producer implements Runnable {
        SynchronousQueue<String> queue;

        public Producer(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println("start");
//                while (true)
                    queue.put("Hello SynchronousQueue");
                //System.out.println("put after");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        SynchronousQueue<String> queue;

        public Consumer(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String poll = null;
            try {
                while (true) {
                    poll = queue.take();
                    System.out.println(Thread.currentThread() + poll);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
