package com.frankie.testDemo;

/**
 * DemoManacher
 *  字符串最长回文
 * @author: Frankie
 */
public class DemoManacher {

    public static int manacher(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        char[] arr = str.toCharArray();
        char[] array = getManacherArr(arr);
        int index = -1;
        int len = array.length;
        int pR = -1;
        int[] pArr = new int[len];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            pArr[i] = i < pR ? Math.min(pR - i, pArr[2 * index - i]) : 1;
            while (i -pArr[i] >= 0 && i + pArr[i] < len) {
                if (arr[i + pArr[i]] == arr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }

            max = Math.max(max,pArr[i]);
        }

        return max;
    }

    private static char[] getManacherArr(char[] arr) {
        int len = arr.length;
        char[] res= new char[2 * len + 1];
        int count = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : arr[count++];
        }
        return res;
    }
}
