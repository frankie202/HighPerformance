package com.frankie.datastructure.heap;

import java.util.Arrays;

public class BinaryHeap {
    private int[] arr;
    private int size = 0;
    private int curPos = -1;

    public BinaryHeap(int size) {
        this.size = size;
        this.arr = new int[this.size];
    }

    public BinaryHeap(int[] arr) {
        this.size = arr.length;
        this.arr = arr;
        this.curPos = arr.length - 1;
        this.siftDown();
    }

    private void siftDown() {
        for (int i = this.curPos / 2; i >= 0 ; i--) {
            this.doSiftDown(i);
        }
    }

    private void doSiftDown(int val) {
        int child = 0;
        for (int i = val;(child = leftChild(i)) < this.size; i = child) {
            if (child + 1 < this.size && arr[child + 1] < arr[child]) {
                child = child +1;
            }

            if (arr[child] < arr[val]) {
                swap(child,val);
            } else {
                break;
            }
        }
    }

    private void swap(int left,int right) {
        int tmp = this.arr[left];
        arr[left] =arr[right];
        arr[right] = tmp;
    }

    private int leftChild(int parent) {
        return 2 * parent + 1;
    }

    public void add(int value) {
        if (this.curPos >= this.size - 1) {
            throw new RuntimeException();
        }

        this.arr[++curPos] = value;
        this.siftUp();
    }

    private void siftUp() {
        for (int i = curPos; i / 2 >= 0 ; i /= 2) {
            if (arr[i / 2] > arr[i]) {
                swap(i,i / 2);
            } else {
                break;
            }
        }
    }

    public void show() {
        System.out.println(Arrays.toString(this.arr));
    }

    public static void main(String[] args) {
        /*int[] arr = {5,8,9,2,6,4,3};
        BinaryHeap heap = new BinaryHeap(arr);*/

        BinaryHeap heap = new BinaryHeap(10);
        heap.add(5);
        heap.add(8);
        heap.add(9);
        heap.add(2);

        heap.show();


    }

}
