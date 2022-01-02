package com.frankie.stream;

import java.util.ArrayList;
import java.util.List;

public class DemoStream {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        list.stream()
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);
    }
}
