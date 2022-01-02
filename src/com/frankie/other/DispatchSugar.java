package com.frankie.other;

/**
 * DispatchSugar
 *  分发糖果
 * @author: Frankie
 */
public class DispatchSugar {

    /**
     * 相邻相同分数的小朋友不需要保持一样的糖果
     * 分为上坡下坡，
     * @param arr
     * @return
     */
    public static int dispatchSugar(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int index = nextMinPos(arr,0);
        int[] res = doDispatchSugarDown(arr, 0, index++);
        int total = res[0];
        int lbase = 1;
        int next = 0;

        while (index < arr.length) {
            if (arr[index] > arr[index - 1]) {
                lbase++;
                total += lbase;
                index++;
            } else if (arr[index] < arr[index - 1]) {
                next = nextMinPos(arr,index - 1);
                res = doDispatchSugarDown(arr,index - 1,next);
                total += res[0] - (Math.min(lbase,res[1]));
                lbase = 1;
                index++;
            } else {
                total++;
                lbase=1;
                index++;
            }
        }

        return total;
    }

    private static int nextMinPos(int[] arr,int start) {
        for (int i = start; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    private static int[] doDispatchSugarDown(int[] arr,int start,int end) {
        int total = 1;
        int base = 1;
        int[] res = new int[2];

        for (int i = start; i < end; i++) {
            if (arr[i] > arr[i + 1]) {
                base++;
                total+= base;
            }
        }
        res[0] = total;
        res[1] = base;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,2};
        int sugar = dispatchSugar(arr);
        System.out.println(sugar);
    }
}
