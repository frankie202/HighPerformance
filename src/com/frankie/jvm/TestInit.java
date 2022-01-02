package com.frankie.jvm;

public class TestInit {
    static {
        System.out.println("TestInit init");
    }

    static int value  = 123;
    final int val;

    public TestInit(int val) {
        this.val = val;
    }
}
