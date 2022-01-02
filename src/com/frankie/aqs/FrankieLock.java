package com.frankie.aqs;

public class FrankieLock extends FrankieAqs {
    @Override
    public boolean tryAcquire() {
        return owner.compareAndSet(null,Thread.currentThread());
    }

    public void lock() {
        if (!tryAcquire()) {
            waiters.add(Thread.currentThread());
        }
    }

    @Override
    public boolean tryRelease() {
        return owner.compareAndSet(Thread.currentThread(),null);
    }
}
