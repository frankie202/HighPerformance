package com.frankie.demoStruture;

import java.util.concurrent.CopyOnWriteArraySet;

public class DemoCopyOnWriteArraySet {

    public static void main(String[] args) {
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();

        set.add(2);
        set.add(3);
        set.add(2);
    }
}
