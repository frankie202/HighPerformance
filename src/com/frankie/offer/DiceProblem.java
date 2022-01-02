package com.frankie.offer;

import java.util.Arrays;

/**
 * DiceProblem
 *  骰子问题： n个骰子各个点数之和所占的概率
 * @author: Frankie
 */
public class DiceProblem {

    public static void doDiceProblem(int num) {
        if (num < 1) {
            return;
        }
        int NUM = 6;
        int[][] arr = new int[2][6 * num + 1];
        boolean flag = true;
        for (int i = 1; i <=  NUM; i++) {
            arr[0][i] = 1;
        }

        for (int i = 1; i < num; i++) {
            for (int j = 1; j <= NUM; j++) {
                if (flag) {
                    for (int k = 1; k < 6*num +1 ; k++) {
                        if (k -j > 0) {
                            arr[1][k] += arr[0][k - j];
                        }
                    }
                } else {
                    for (int k = 1; k < 6*num +1 ; k++) {
                        if (k -j > 0) {
                            arr[0][k] += arr[1][k - j];
                        }
                    }
                }
            }
            if (flag) {
                Arrays.fill(arr[0],0);
            } else {
                Arrays.fill(arr[1],0);
            }
            flag = !flag;
        }

        if (flag) {
            System.out.println(Arrays.toString(arr[0]));

        } else {
//            System.out.println(Arrays.toString(arr[0]));
            System.out.println(Arrays.toString(arr[1]));
        }
    }


    public static void doDiceRecursive(int num) {
        if (num < 1) {
            return;
        }
        int total = (int) Math.pow(6,num);
        int[] arr = new int[6 * num  - num + 1];

        getCount(num,arr,0,num);
        int count = 0;
        for (int i = 0; i < 5 * num +1; i++) {
            System.out.println(arr[i]);
            count += arr[i];
        }

        System.out.println("total = " + total);
        System.out.println("cal = " + count);
    }

    private static void getCount(int num,int[] arr,int sum,int count) {
        if (num == 1) {
            for (int i = 1; i <= 6 ; i++) {
                arr[sum + i - count]++;
            }
        } else {
            for (int i = 1; i <= 6; i++) {
                getCount(num - 1,arr,sum + i,count);
            }
        }
    }

    public static void main(String[] args) {
        doDiceRecursive(3);
        doDiceProblem(3);
    }
}
