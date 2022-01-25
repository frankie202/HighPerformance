package com.frankie.other;

/**
 * DemoDoubleCheckLock
 *
 * @author: Frankie
 */
public class DemoDoubleCheckLock {

    private static volatile DemoDoubleCheckLock INSTANCE;

    private DemoDoubleCheckLock() {
    }

    public static DemoDoubleCheckLock getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (DemoDoubleCheckLock.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DemoDoubleCheckLock();
                }
            }
        }
        return INSTANCE;
    }
}
