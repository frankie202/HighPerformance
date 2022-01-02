package com.frankie.other;

/**
 * FindUpMedianNum
 *
 * @author: Frankie
 */
public class FindUpMedianNum {

    public static int getUpMedianNum(int[] arr1,int[] arr2) {
        if (arr1 == null || arr2 == null || arr2.length < 1 ||
                arr1.length != arr2.length) {
            throw new IllegalArgumentException();
        }
        int start1 = 0;
        int start2 = 0;
        int end1 = arr1.length - 1;
        int end2 = arr2.length - 1;
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while (start1 < end1) {
            mid1 = ((end1 - start1) >>> 1) + start1;
            mid2 = ((end2 - start2) >>> 1) + start2;
            offset = ((end1 - start1 + 1) & 1 ) ^ 1;
            if (arr1[mid1] == arr2[mid2]) {
                return arr1[mid1];
            } else if (arr1[mid1] > arr2[mid2]) {
                end1 = mid1;
                start2 = mid2 + offset;
            } else {
                start1 = mid1 + offset;
                end2 = mid2;
            }
        }
        return Math.min(arr1[start1],arr2[start2]);
    }

    public static void main(String[] args) {
        int[] arr1= {2,4,6,8,10};
        int[] arr2= {1,3,5,7,9};

        int upMedianNum = getUpMedianNum(arr1, arr2);
        System.out.println(upMedianNum);
    }
}
