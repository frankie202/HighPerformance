package com.frankie.testDemo;

import java.util.concurrent.ConcurrentHashMap;

public class DemoConcurrentHashMap {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

        map.put("key1","value1");


        map.put("key2","value2");

    }
}
