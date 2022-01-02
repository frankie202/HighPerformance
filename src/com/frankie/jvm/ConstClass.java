package com.frankie.jvm;

public class ConstClass {
    static {
        System.out.println("ConstClass init");
    }
    static final String HELLO = "Hello World!";
}
