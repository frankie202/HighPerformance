package com.frankie.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DProxy implements InvocationHandler{
    private Subject object;

    public DProxy(Subject object) {
        this.object = object;
    }

    public Object instance() {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before ");
        Object o = method.invoke(object,args);
        System.out.println(o);
        System.out.println("after ");
        return o;
    }
}
