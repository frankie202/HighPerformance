package com.frankie.testDemo;

import java.util.concurrent.*;

public class DemoLinkedTransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue();

        /*try {
            queue.transfer("key");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(queue.take());*/

        ExecutorService exec = new ThreadPoolExecutor(3,3,1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        exec.submit(new Producer(queue));
//        exec.submit(new Consumer(queue));
//        exec.submit(new Consumer(queue));

        exec.shutdown();
    }

    static class Producer implements Runnable {
        LinkedTransferQueue<String> queue;

        public Producer(LinkedTransferQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

                while (true) {
                    queue.put("Hello LinkedTransferQueue");
//                    queue.transfer("Hello LinkedTransferQueue");
//                    queue.transfer("Hello ~");
                }

        }
    }

    static class Consumer implements Runnable {
        LinkedTransferQueue<String> queue;

        public Consumer(LinkedTransferQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String take = null;
            try {
                while (true)
                    take = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread()+take);

        }
    }
}
