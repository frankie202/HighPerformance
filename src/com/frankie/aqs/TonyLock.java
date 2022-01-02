package com.frankie.aqs;

import sun.misc.Unsafe;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class TonyLock implements Lock {
    volatile AtomicReference<Thread> owner = new AtomicReference<>();

    volatile LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();
    @Override
    public boolean tryLock() {
        return owner.compareAndSet(null,Thread.currentThread());
    }

    @Override
    public void lock() {
        boolean addToQueue = false;
        while (!tryLock()) {
            if (!addToQueue) {
//                没有拿到锁，加入到等待集合
                waiters.offer(Thread.currentThread());
                addToQueue = true;
            } else {
//                阻塞，挂起当前线程
                LockSupport.park();//伪唤醒
            }
        }
        waiters.remove(Thread.currentThread());
    }

    @Override
    public void unlock() {
        /*释放掉锁*/
        if (owner.compareAndSet(Thread.currentThread(),null)) {
            Iterator<Thread> iterator = waiters.iterator();
            /*如果等待队列中有等待线程*/
            while (iterator.hasNext()) {
                Thread next = iterator.next();
                /*唤醒等待线程*/
                LockSupport.unpark(next);
            }

        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
