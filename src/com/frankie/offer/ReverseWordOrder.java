package com.frankie.offer;

/**
 * ReverseWordOrder
 *  1、反转单词的顺序
 *  2、字符串的左旋操作
 * @author: Frankie
 */
public class ReverseWordOrder {

    public static String doReverseWordOrder(String string) {
        if (string == null || string.length() < 1) {
            return string;
        }
        char[] array = string.toCharArray();
        /*1、将整个句子整体反转*/
        doReverse(array,0,array.length - 1);
        /*2、将每个单词反转*/
        int start = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                doReverse(array,start,i - 1);
                start = i + 1;
            }
        }
        return new String(array);
    }

    private static void doReverse(char[] arr, int start,int end) {
        char tmp;
        while (start < end) {
            tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }


    public static String doLeftReverseWord(String str,int num) {
        if (str == null || str.length() < 2) {
            return str;
        }
        int len = str.length();
        if (len < num) {
            throw new RuntimeException();
        }
        char[] array = str.toCharArray();
        doReverse(array,0,num - 1);
        doReverse(array,num,len-1);
        doReverse(array,0,len-1);
        return new String(array);
    }
    public static void main(String[] args) {
        String str = "abcdefg";
        String s = doLeftReverseWord(str, 2);
        System.out.println(s);
    }
}
