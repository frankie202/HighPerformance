package com.frankie.other;

import java.util.LinkedList;

/**
 * DemoHas
 *
 * @author: Frankie
 */
public class DemoHas {

    private static int x=100;
     /*public static void main(String args[]){
         DemoHas hs1=new DemoHas();
         hs1.x++;
         DemoHas  hs2=new DemoHas();
         hs2.x++;
         hs1=new DemoHas();
         hs1.x++;
         DemoHas.x--;
         System.out.println("x="+x);
     }*/
    public static void main(String[] args) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        for (int i = 0; i < stack.size(); i++) {
            System.out.println(stack.get(i));
        }
    }
}
