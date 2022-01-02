package com.frankie.offer;

import java.util.Arrays;

/**
 * FindTwoNum
 *  在一个整数数组中，只有两个数字出现一次，其他的数字都出现两次，求这两个数字
 * @author: Frankie
 */
public class FindTwoNum {

    public static int[] findTwoNum(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        int num = findNum(arr);
        int count = getFirstOnePos(num);

        return doGetNum(arr,count);
    }

    private static int findNum(int[] arr) {
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            res = res ^ arr[i];
        }
        return res;
    }

    private static int getFirstOnePos(int num) {
        int pos = 1;
        while ((num & 1) == 0) {
            num >>>= 1;
            pos++;
        }
        return pos;
    }

    private static int[] doGetNum(int[] arr, int count) {
        int[] res = new int[2];
        res[0] = 0;
        res[1] = 0;
        for (int i = 0; i < arr.length; i++) {
            if (((arr[i] >> count) & 1) == 1) {
                res[0] ^= arr[i];
            } else {
                res[1] ^= arr[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2,3,3,4,4,6,6};
        int[] num = findTwoNum(arr);

        System.out.println(Arrays.toString(num));
    }
}
