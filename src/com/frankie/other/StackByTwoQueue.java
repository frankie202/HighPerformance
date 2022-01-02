package com.frankie.other;

import java.util.LinkedList;
import java.util.Queue;

/**
 * StackByTwoQueue
 *
 * @author: Frankie
 */
public class StackByTwoQueue {
    private Queue<Integer> putQueue = null;
    private Queue<Integer> tmpQueue = null;

    public StackByTwoQueue() {
        this.putQueue = new LinkedList<>();
        this.tmpQueue = new LinkedList<>();
    }

    public int pop() {
        int res = 0;
        int size = putQueue.size();
        if (size > 0) {
            while (size > 1) {
                tmpQueue.add(putQueue.poll());
                size--;
            }
            res = putQueue.remove();
        } else {
            throw new NullPointerException();
        }
        Queue<Integer> tmp = tmpQueue;
        tmpQueue = putQueue;
        putQueue = tmp;

        return res;
    }

    public void push(int value) {
        this.putQueue.add(value);
    }

    public static void main(String[] args) {
        StackByTwoQueue stack = new StackByTwoQueue();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        int pop = stack.pop();
        System.out.println(pop);
        int pop1 = stack.pop();
        System.out.println(pop1);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        //System.out.println(stack.pop());
    }
}
