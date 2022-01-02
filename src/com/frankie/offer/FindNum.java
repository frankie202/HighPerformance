package com.frankie.offer;

/**
 * FindNum
 *  在旋转数组找到最小的数字
 * @author: Frankie
 */
public class FindNum {

    public static int findMinNum(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        int min = Integer.MAX_VALUE;
        if (arr[start] < arr[end]) {
            return arr[start];
        }
        while (start < end -1) {
            mid = ((end - start) >>> 1) + start;
            if (arr[mid] == arr[start] && arr[mid] == arr[end]) {
                for (int i = 0; i <=end ; i++) {
                    min = Math.min(min,arr[i]);
                }
                return min;
            } else if (arr[mid] > arr[start]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return Math.min(arr[start],arr[end]);
    }


    public static void main(String[] args) {
        int[] arr = {3,4,5,6,7,1,2};

        int num = findMinNum(arr);
        System.out.println(num);
    }
}
