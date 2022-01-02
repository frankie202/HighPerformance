package com.frankie.offer;

/**
 * CountNumberOfOne
 *      统计 1- n中数字1出现的个数
 * @author: Frankie
 */
public class CountNumberOfOne {

    /**
     * 遍历所有的数字找出所有包含1的数字
     * @param n
     * @return
     */
    public static int getNumberOfOne(int n) {
        int count  = 0;
        for (int i = 1; i <= n; i++) {
            String str = String.valueOf(i);
            char[] array = str.toCharArray();
            for (int j = 0; j < array.length; j++) {
                if (array[j] == '1') {
                    count++;
                }
            }
        }
        return count;
    }

    public static int getNumOfOne(int n) {
        if (n <= 0) {
            return 0;
        }
        String str = String.valueOf(n);
        char[] arr = str.toCharArray();
        return doGetNumOfOne(arr,0,arr.length);
    }

    protected static int doGetNumOfOne(char[] arr,int start,int len) {
        /*此处判断的是字符，不应该是数字*/
        if (start == len - 1 && arr[start] > '0') {
            return 1;
        }
        if (start == len - 1 && arr[start] == '0') {
            return 0;
        }
        int count = 0;
        /*得到该数字的大小*/
        int firstNum = arr[start] - '0';
        if (firstNum > 1) {
            count = powerBase10(len - 1 - start);
        } else if (firstNum == 1) {
            String s = new String(arr, start + 1, len - 1 - start);
            Integer value = Integer.valueOf(s);
            /*为什么+1*/
            count =  value +1;
        }
        int otherNum = firstNum * (len - start - 1) * powerBase10(len - start -2);
        int recNum = doGetNumOfOne(arr,++start,len);
        return count + otherNum + recNum;
    }

    protected static int powerBase10(int num) {
        int res = 1;
        for (int i = 0; i < num; i++) {
            res *= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        int num = 215369;
        int number = getNumberOfOne(num);
        System.out.println(number);


        int num1 = getNumOfOne(num);
        System.out.println(num1);
    }
}
