package com.frankie.other;

/**
 * GetMinKNum
 *
 * @author: Frankie
 */
public class GetMinKNum {

    private static int getUpMedianNum(int[] arr1,int start1,int end1,
                                      int[] arr2,int start2,int end2) {
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while (start1 < end1) {
            mid1 = ((end1 - start1) >>> 1) + start1;
            mid2 = ((end2 - start2) >>> 1) + start2;
            offset = ((end1 - start1 + 1) & 1) ^ 1;
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
        return Math.min(arr1[mid1],arr2[mid2]);
    }


    public static int getMinKNum(int[] arr1,int[] arr2,int num) {
        if (arr1 == null || arr2 == null || num > (arr1.length + arr2.length)) {
            throw new  IllegalArgumentException();
        }
        int[] arrLong = arr1.length >= arr2.length ? arr1 : arr2;
        int[] arrShort = arr1.length >= arr2.length ? arr2 : arr1;
        int lLen = arrLong.length;
        int sLen = arrShort.length;

        if (sLen >= num) {
            return getUpMedianNum(arrLong,0,num-1,arrShort,0,num-1);
        }
        if (lLen < num) {
            if (arrShort[num - lLen -1] > arrLong[lLen - 1]) {
                return arrShort[num -lLen - 1];
            }
            if (arrLong[num - sLen - 1] > arrShort[sLen - 1]) {
                return arrLong[num - sLen - 1];
            }
            return getUpMedianNum(arrLong,num -sLen,lLen - 1,
                    arrShort,num - lLen,sLen - 1);
        }

        if (arrShort[sLen - 1] <= arrLong[num - sLen - 1]) {
            return arrLong[num - sLen - 1];
        }

        return getUpMedianNum(arrLong,num - sLen,num - 1,
                arrShort,0,sLen - 1);
    }
}
