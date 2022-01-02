package com.frankie.offer;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * FindFixedSum
 *  1、在有序数组中，找到两个数的和为给定的值
 *  2、在有序数组中，找到所有和为正整数（不少于两个）的和为给定值
 * @author: Frankie
 */
public class FindFixedSum {

    public static int[] findTwoNum(int[] arr, int sum) {
        if (arr == null || arr.length < 2) {
            return null;
        }
        int[] res = new int[2];

        int start = 0;
        int end = arr.length - 1;
        while (end > start) {
            if (arr[start] + arr[end] == sum) {
                res[0] = arr[start];
                res[1] = arr[end];
                break;
            } else if (arr[start] + arr[end] < sum) {
                start ++;

            } else {
                end--;
            }
        }

        return res;
    }

    public static int findTwoNumFixedSumTotal(int[] arr,int sum) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int total = 0;
        int start = 0;
        int end = arr.length - 1;
        while (end > start) {
            if (arr[start] + arr[end] == sum) {
                start++;
                end--;
                total++;
            } else if (arr[start] + arr[end] < sum) {
                start ++;

            } else {
                end--;
            }
        }

        return total;
    }


    public static void printAllFixedSum(int[] arr,int sum) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int start = 0;
        int end = 1;
        int total = arr[0] + arr[1];

        while (end < arr.length - 1) {
            if (total == sum) {
                print(arr,start,end);
                total -= arr[start];
                start++;
                end++;
                total += arr[end];
            } else if (total > sum) {
                total -= arr[start];
                start++;
            } else {
                end++;
                total+= arr[end];
            }
        }
    }

    private static void print(int[] arr,int start,int end) {
        if (start == end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,12,15};
        printAllFixedSum(arr,15);
    }
}
