package com.frankie.aqs;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class FrankieAqs {
    //acquire、acquireShared：定义资源争用的逻辑，如果没有拿到则等待
//    tryAcquire、tryAcquireShared：实际执行占用资源的操作，如何判断由使用者具体去实现
//    release、releaseShared：定义释放资源的逻辑，释放之后，通知后续节点去争抢
//    tryRelease、tryReleaseShared：实际执行资源释放的操作，具体的AQS实现着去实现

    public volatile AtomicReference<Thread> owner = new AtomicReference<>();

    public volatile LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    public volatile AtomicInteger state = new AtomicInteger(0);

    public AtomicReference<Thread> getOwner() {
        return owner;
    }

    public void setOwner(AtomicReference<Thread> owner) {
        this.owner = owner;
    }

    public LinkedBlockingQueue<Thread> getWaiters() {
        return waiters;
    }

    public void setWaiters(LinkedBlockingQueue<Thread> waiters) {
        this.waiters = waiters;
    }

    public AtomicInteger getState() {
        return state;
    }

    public void setState(AtomicInteger state) {
        this.state = state;
    }

    public boolean tryAcquire() {
        throw new UnsupportedOperationException();
    }
    public void acquire() {
        boolean addToQueue = false;

        while (!tryAcquire()) {
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

    public boolean tryRelease() {
        throw new UnsupportedOperationException();
    }

    public int tryAcquireShared() {
        throw new UnsupportedOperationException();
    }

    public void release() {
        if (tryRelease()) {
            Iterator<Thread> it = waiters.iterator();
            while (it.hasNext()) {
                Thread next = it.next();
                LockSupport.unpark(next);
            }
        }
    }

    public void acquireShared() {
        boolean addQ = false;
        while (tryAcquireShared() < 0) {
            if (!addQ) {
                waiters.offer(Thread.currentThread());
                addQ = true;
            } else {
                LockSupport.park();
            }
        }
        waiters.remove(Thread.currentThread());
    }


    public boolean tryReleaseShared() {
        throw new UnsupportedOperationException();
    }

    public void releaseShared() {
        if (tryReleaseShared()) {
            Iterator<Thread> it = waiters.iterator();
            while (it.hasNext()) {
                Thread next  = it.next();
                LockSupport.unpark(next);
            }
        }
    }
}
