package com.frankie.datastructure.search;

public class BinarySearch {
    /*逼近原理：最左侧的元素需要从右侧逼近，最后一个等于的元素即为所求*/
    public static int findLeftEle(int[] arr,int value,int left, int right) {
        if (left == right) {
            return left;
        }
        int mid = (left + right) / 2;
        if (arr[mid] < value) {//确定最左侧的元素为不小于待求元素
            return findLeftEle(arr,value,mid+ 1,right);
        } else {
            return findLeftEle(arr,value,left,mid);
        }
    }

    public static int findRightEle(int[] arr,int value,int left, int right) {
        if (left == right) {
            return left;
        }
        int mid = (left + right +1) / 2;
        if (arr[mid] > value) {
            return findRightEle(arr, value, left, mid - 1);
        } else {
            return findRightEle(arr, value, mid, right);
        }
    }

    public static void main(String[] args) {
//        int[] arr = {1,2,3,5,5,5,6,7,8};
        /*System.out.println(findLeftEle(arr,5,0,arr.length - 1));
        
        System.out.println(findRightEle(arr,5,0,arr.length - 1));*/

   /*     System.out.println(getFirstPos(arr,5));
        System.out.println(getLastPos(arr,5));*/

        int[] arr= {3,4,5,6,1,2};
        int element = findMinElement(arr);
        System.out.println(element);
    }


    public static int getFirstPos(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int start =0;
        int end = arr.length - 1;
        int mid = -1;

        while (end > start + 1) {
            mid = ((end - start) >>> 1) + start;
            if (arr[mid] < target) {
                start = mid;
            } else {
                end = mid -1;
            }
        }
        if (arr[end] == target) {
            return end;
        }
        if (arr[start] == target) {
            return start;
        }
        return -1;
    }

    public static int getLastPos(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int start =0;
        int end = arr.length - 1;
        int mid = -1;

        while (end > start + 1) {
            mid = ((end - start) >>> 1) + start;
            if (arr[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (arr[start] == target) {
            return start;
        }
        if (arr[end] == target) {
            return end;
        }
        return -1;
    }

    public static int findMinElement(int[] arr) {
        if(arr == null || arr.length < 1) {
            throw new IllegalArgumentException();
        }
        int start = 0;
        int end  =arr.length - 1;
        int mid = -1;
        int res = Integer.MAX_VALUE;
        while (end > start) {
            mid = ((end -start) >>> 1) + start;
            if (arr[mid] == arr[start] && arr[mid] == arr[end]) {
                for (int i = 0;i <= end;i++) {
                    res = Math.min(res,arr[i]);
                }
                return res;
            } else if (arr[mid] >= arr[start]) {
                start = mid +1;
            } else {
                end = mid;
            }
        }
        return arr[start];
    }

}
