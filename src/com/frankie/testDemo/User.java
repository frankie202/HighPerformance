package com.frankie.testDemo;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private transient int age;
    private transient String store;
    int aa;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", store='" + store + '\'' +
                '}';
    }
}
