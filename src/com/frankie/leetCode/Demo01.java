package com.frankie.leetCode;

public class Demo01 {

    public static int longestSubstring(String s, int k) {
        if (k > s.length()) {
            throw new RuntimeException();
        }

        int len = s.length();
        char[] arr = s.toCharArray();
        for (int i = len - 1;i >= 0;i--) {
            int j = i - 1;
            while (arr[i] == arr[j]) {
                if (i - j == k - 1) {
                    return i + 1;
                }
                j--;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int i = longestSubstring("aaabb", 3);
        System.out.println(i);
    }

    public int dfs(String s, int l, int r, int k) {
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return r - l + 1;
        }

        int i = l;
        int ret = 0;
        while (i <= r) {
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }

            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }

        return ret;
    }

}
