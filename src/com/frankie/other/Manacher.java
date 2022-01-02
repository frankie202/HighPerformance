package com.frankie.other;

/**
 * Manacher
 *  字符串匹配
 * @author: Frankie
 */
public class Manacher {

    public static int manacher(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        char[] array = str.toCharArray();
        char[] arr = getTmpCharArray(array);
        int len = arr.length;
        int[] pArr = new int[len]; /*以i为回文半径，最大回文半径*/
        int index = -1; //最近一次回文更新时，回文中心的位置
        int pR = -1;  /*之前遍历所有字符的所有回文半径中，最右即将到达的位置*/
        int max= Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            /*说明：2 * index - i 是左侧的i节点 ---> 可以通过数学进行证明*/
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i],pR -i) : 1;
            /*以i为中心点向两侧进行扩展*/
            while (i + pArr[i] < len && i -pArr[i] > -1) {
                if (arr[i + pArr[i]] == arr[i - pArr[i]]) {
                    pArr[i] ++;
                } else {
                    break;
                }
            }
            /*判断是否需要更新中心点*/
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }

            max = Math.max(max,pArr[i]);
        }
        return max - 1;
    }

    private static char[] getTmpCharArray(char[] arr) {
        int len = arr.length;
        char[] res = new char[2 * len + 1];
        int count = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : arr[count++];
        }

        return res;
    }


}
