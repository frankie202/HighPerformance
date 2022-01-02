package com.frankie.offer;


/**
 * FindMinMissSum
 *  正数数组中的最小的缺失和
 * @author: Frankie
 */
public class FindMinMissSum {

    public static int findMinMissSum(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int[] minMaxSum = getMinMaxSum(arr);
        int len = arr.length;
        int num = minMaxSum[1] + 1;//和的个数
        boolean[] dp = new boolean[num];
        /*初始化*/
//        for (int i = 0; i < len; i++) {
//            dp[arr[i]] = true;
//        }

        /*该思路问题：无法保证每个数字只使用一次*/
        /*for (int i = minMaxSum[0]; i <= minMaxSum[1]; i++) {

            for (int j = 0; j < len; j++) {
                if (i - arr[j] == 0) {
                    dp[i] = true;
                    break;
                }
                if (i - arr[j] > 0 && dp[i - arr[j]] && i != 2 * arr[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }*/
        /*正确的解法*/
        dp[0] = true;
        for (int i = 0; i < len; i++) {

            for (int j = minMaxSum[1]; j >= arr[i]; j--) {
                dp[j] = dp[j - arr[i]] ? true : dp[j];
            }
        }
        for (int i = minMaxSum[0]; i <= minMaxSum[1]; i++) {
            if (!dp[i]) {
                return i;
            }
        }
        return minMaxSum[1] + 1;
    }

    private static int[] getMinMaxSum(int[] arr) {
        int[] res = new int[2];
        res[0] = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            res[0] = Math.min(res[0],arr[i]);
            res[1] += arr[i];
        }
        return res;
    }



    public static void main(String[] args) {
//        int[] arr = {1,2,4};
        int[] arr = {3,2,4};
//        int[] arr = {3,2,5};

        int minMissSum = findMinMissSum(arr);
        System.out.println(minMissSum);
    }
}
