package com.frankie.offer;

/**
 * GetSerialNums
 *      连续数列的数为给定和
 * @author: Frankie
 */
public class GetSerialNums {

    public static void getNumSerial(int[] arr,int sum) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException();
        }
        int left = 0;
        int right = 1;
        int cnt = arr[left] + arr[right];
        while (left < right) {
            if (cnt < sum) {
                right++;
                cnt += arr[right];
            } else if (cnt == sum) {
                print(arr,left,right);
                cnt -= arr[left];
                left++;
            } else {
                cnt -= arr[left];
                left++;
            }
        }
    }

    private static void print(int[] arr,int start,int end) {
        for (int i = start; i <=end ; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};

        getNumSerial(arr,15);
    }
}
