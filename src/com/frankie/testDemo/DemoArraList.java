package com.frankie.testDemo;

import java.util.*;

public class DemoArraList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("a1");
        list.add("a2");
        list.add("a3");

        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<Integer, Integer>(3) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size() >= 3;
            }
        };

        /*Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String tmp = it.next();
            if (tmp.equals("a2")) {
                it.remove();
            }
//            it.remove();
        }



        System.out.println(list);

        int val = 100;
        getval(val);
        System.out.println(val);*/

        List<Integer> nums = new ArrayList<>();
        nums.add(5);
        nums.add(2);
        nums.add(8);

        Collections.sort(nums,(num,num2) -> {
            return num2 - num;
        });

        System.out.println(nums);
    }

    private static void getval(int val) {
        val++;
    }
}
