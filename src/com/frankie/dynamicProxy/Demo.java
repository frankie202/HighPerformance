package com.frankie.dynamicProxy;

public class Demo {

    public static void main(String[] args) {
        DProxy proxy = new DProxy(new Implement());

        Subject instance = (Subject) proxy.instance();
        instance.say();
    }
}
