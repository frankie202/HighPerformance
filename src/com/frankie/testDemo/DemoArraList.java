package com.frankie.testDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DemoArraList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("a1");
        list.add("a2");
        list.add("a3");

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String tmp = it.next();
            if (tmp.equals("a2")) {
                it.remove();
            }
//            it.remove();
        }


        System.out.println(list);
    }


}
