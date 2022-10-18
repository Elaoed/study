package org.example.algorithm.TwoPointer;

import java.util.Arrays;

/**
 * Given a string s, return the longest palindromic substring in s.
 */
public class LongestPalindromicSubstring {

    public static int[] extendFromIndex(String s, Integer left, Integer right) {
        while (left >= 0 && right <= s.length() - 1) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        return new int[]{++left, --right};
    }

    public static String longestPalindrome(String s) {

        if (s == null || s.isEmpty()) {
            return "";
        }

        if (s.length() == 1) {
            return s;
        }

        String res = s.substring(0, 1);
        // 原先写的是i = 1; 后来发现"bb"这样的字符串就没办法了，既然extendFromIndex已经把校验做了就老老实实 0, s.length() - 1就好了
        for (int i = 0; i < s.length() - 1; i++) {
            int[] pos1 = extendFromIndex(s, i, i);
            int[] pos2 = extendFromIndex(s, i, i + 1);
            System.out.println(Arrays.toString(pos1) + ", " + Arrays.toString(pos2));
            int[] pos = pos1[1] - pos1[0] > pos2[1] - pos2[0] ? pos1 : pos2;

            if (res.length() < pos[1] - pos[0] + 1) {
                res = s.substring(pos[0], pos[1] + 1);
            }
        }
        return res;

    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("ac"));
    }
}
