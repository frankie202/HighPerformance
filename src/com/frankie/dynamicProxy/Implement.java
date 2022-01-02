package com.frankie.dynamicProxy;

public class Implement implements Subject {
    @Override
    public String say() {
        return "Hello Dynamic Proxy!";
    }
}
