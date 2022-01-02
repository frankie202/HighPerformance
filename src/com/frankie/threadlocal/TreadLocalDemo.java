package com.frankie.threadlocal;

public class TreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<Integer> local = new ThreadLocal<>();
        local.set(4);

        System.out.println(local.get());
    }
}
