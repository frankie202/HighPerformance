package com.frankie.semaphore;

import com.frankie.aqs.FrankieAqs;

public class FankieSemaphore {
    FrankieAqs aqs = new FrankieAqs() {
        @Override
        public int tryAcquireShared() {
            while (true) {
                int count = getState().get();
                int n = count - 1;
                if (count <= 0 || n < 0) {
                    return -1;
                }
                if (getState().compareAndSet(count, n)) {
                    return 1;
                }
            }
        }

        @Override
        public boolean tryReleaseShared() {
            return getState().incrementAndGet() >= 0;
        }
    };

    public FankieSemaphore(int count) {
        aqs.getState().set(count);
    }

    public void release() {
        aqs.releaseShared();
    }

    public void acquire() {
        aqs.acquireShared();
    }
}
