package com.frankie.algorithm;

/**
 * ReverseNumber
 * Description:
 *
 * @Author Frankie
 * @Date 2022/8/27
 */
public class ReverseNumber {

    public static int findNumOfReverse(int[] arr) {
        if (arr == null) {
            throw new NullPointerException();
        }

        if (arr.length == 0) {
            return 0;
        }
        int[] tmp = new int[arr.length];
        return doFindNumOfReverse(arr,tmp,0,arr.length - 1);
    }

    private  static int doFindNumOfReverse(int[] arr,int[] tmp, int start,int end) {
        if (start == end) {
            return 0;
        }
        int mid = ((end - start) >> 1) + start;
        int left = doFindNumOfReverse(arr,tmp,start,mid);
        int right = doFindNumOfReverse(arr, tmp, mid + 1, end);
        int cnt = 0;
        int i = mid;
        int j = end;
        int k = end;
        while (i >= start && j >= mid + 1) {
            if (arr[i] > arr[j]) {
                tmp[k--] = arr[i--];
                cnt += j - mid;
            } else {
                tmp[k--] = arr[j--];
            }
        }

        while (i >= start) {
            tmp[k--] = arr[i--];
        }
        while (j >= mid + 1) {
            tmp[k--] = arr[j--];
        }
        k = start;
        while (k <= end) {
            arr[k] = tmp[k];
            k++;
        }

        return cnt + left + right;
    }

    public static void main(String[] args) {
        int[] arr= {7,5,6,4};

        int num = findNumOfReverse(arr);

        System.out.println(num);
    }
}
