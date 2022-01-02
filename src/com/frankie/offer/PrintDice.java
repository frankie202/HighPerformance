package com.frankie.offer;

import java.util.Arrays;

/**
 * PrintDice
 *  打印每种骰子概率
 * @author: Frankie
 */
public class PrintDice {
    private static int NUM_DICE = 6;

    public static void printAllDice(int num) {
        if (num < 1) {
            return;
        }
        int sum = NUM_DICE * num;
        int[][] res = new int[2][sum + 1];
        int flag = 0;
        for (int i = 1; i <= NUM_DICE; i++) {
            res[flag][i] = 1;
        }

        for (int i = 2; i <= num; i++) {
            for (int j = 0; j < sum+1; j++) {
                res[1-flag][j] = 0;
            }

            for (int k = i; k < sum+1; k++){
                for (int j = 1; j <= NUM_DICE; j++) {
                    if (k - j > 0) {
                        res[1 - flag][k] += res[flag][k - j];
                    }
                }
            }
            flag = 1-flag;
        }

        System.out.println(Arrays.toString(res[flag]));
    }

    public static void main(String[] args) {
        printAllDice(3);
    }
}
