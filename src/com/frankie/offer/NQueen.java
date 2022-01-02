package com.frankie.offer;

/**
 * NQueen
 *      N皇后，递归回溯
 * @author: Frankie
 */
public class NQueen {
    static  int count  = 0;
    public void dealNQueen(int size) {
        if (size < 1) {
            throw new RuntimeException("size的个数必须大于0");
        }
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }

        this.doDealNQueen(arr,0);
    }

    private void doDealNQueen(int[] arr,int start) {
        if (start == arr.length) {
            if (isQueen(arr)) {
                count++;
            }
        }

        for (int i = start; i < arr.length; i++) {
            swap(arr,start,i);
            doDealNQueen(arr, start + 1);
            swap(arr,i,start);
        }
    }

    private boolean isQueen(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (Math.abs(j -i) == Math.abs(arr[j]- arr[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    private void swap(int[] arr,int left, int right) {
        if (left != right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;

        }
    }

    public static void main(String[] args) {
        NQueen queen = new NQueen();
        queen.dealNQueen(8);
        System.out.println(count);
    }
}
