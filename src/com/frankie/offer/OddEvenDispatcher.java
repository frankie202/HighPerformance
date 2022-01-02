package com.frankie.offer;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * OddEvenDispatcher
 *  将一个数组中奇数放在偶数的前面
 * @author: Frankie
 */
public class OddEvenDispatcher {
    /**
     * 奇数排在偶数的前面
     * @param arr
     */
    public static void doSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int start =0;
        int end = arr.length - 1;
        while (start < end) {
            while (start < end && (arr[start] & 1) == 1) {
                start++;
            }
            while (start < end && (arr[end] & 1) == 0) {
                end--;
            }
            if (start < end) {
                int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
            }
        }
    }


    private static boolean fun(int num,Function<Integer,Boolean> function) {
        return function.apply(num);
    }
}
