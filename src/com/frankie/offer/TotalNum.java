package com.frankie.offer;

/**
 * TotalNum
 *  统计逆序数
 * @author: Frankie
 */
public class TotalNum {

    public static int getTotalNum(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] tmpArr = new int[arr.length];
        int num = doGetNum(arr, tmpArr, 0, arr.length - 1);
        return num;
    }

    private static int doGetNum(int[] arr,int[] tmpArr,int start,int end) {
        if (start == end) {
            return 0;
        }
        int mid = ((end - start) >>> 1) + start;
        int leftNum = doGetNum(arr,tmpArr,start,mid);
        int rightNum = doGetNum(arr,tmpArr,mid + 1,end);
        int l = mid;
        int r = end;
        int tmp = end;
        int count = 0;
        while (l >= start && r > mid) {
            if (arr[l] > arr[r]) {
                tmpArr[tmp--] = arr[l--];
                count += r - mid;
            } else {
                tmpArr[tmp--] =arr[r--];
            }
        }
        while (l >= start) {
            tmpArr[tmp--] = arr[l--];
        }

        while (r > mid) {
            tmpArr[tmp--] = arr[r--];
        }

        tmp = start;
        while (tmp <= end) {
            arr[tmp] = tmpArr[tmp];
            tmp++;
        }
        return leftNum + rightNum + count;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int totalNum = getTotalNum(arr);
        System.out.println(totalNum);
    }
}
