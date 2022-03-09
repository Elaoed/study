package org.example.algorithm.TwoPointer;

/**
 * Given a string s, return the longest palindromic substring in s.
 */
public class LongestPalindromicSubstring {

    public static String longestPalindrome(String s) {

        if (s == null || s.isEmpty()) {
            return "";
        }

        if (s.length() == 1) {
            return s;
        }

//        // 224455  6 3
//        // 22355 5 2
//        int startIndex = 0, endIndex = 0;
//        int right = s.length() / 2, left = s.length() % 2 == 1 ? s.length() / 2 : s.length() / 2 - 1;
//        while (left >= 0 && right < s.length()) {
//            int curLeft = left, curRight = right;
//            for (; curLeft >= 0; curLeft--, curRight++) {
//                if (s.charAt(curLeft) != s.charAt(curRight)) {
//                    if (curRight - curLeft - 2 > endIndex - startIndex) {
//                        startIndex = curLeft;
//                        endIndex = curRight;
//                    }
//                    break;
//                }
//            }
//            left--;
//            right--;
//        }
//        right = s.length() / 2;
//        left = s.length() % 2 == 1 ? s.length() / 2 : s.length() / 2 - 1;
//        while (left >= 0 && right < s.length()) {
//            int curLeft = left - 1, curRight = right + 1;
//            for (; curRight < s.length(); curLeft--, curRight++) {
//                if (s.charAt(curLeft) != s.charAt(curRight)) {
//                    if (curRight - curLeft - 2 > endIndex - startIndex) {
//                        startIndex = curLeft;
//                        endIndex = curRight;
//                    }
//                    break;
//                }
//            }
//            left++;
//            right++;
//        }
        // 就算按照我原先这么写 时间复杂度也不会变少的，还是老老实实从头for把
        int start = 0, end = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            // 1. left = i, right = i or left = i, right = i + 1;
            int[] index1 = expandAroundCenter(s, i, i);
            int[] index2 = expandAroundCenter(s, i, i + 1);

            if (index1[1] - index1[0] > end - start) {
                end = index1[1];
                start = index1[0];
            }

            if (index2[1] - index2[0] > end - start) {
                end = index2[1];
                start = index2[0];
            }

        }
        // ?
        return s.substring(start + 1, end);

    }

    // remember
    public static int[] expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        // 0 - 0 + 1 = 1
        return new int[]{left, right};

    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("ac"));
    }
}
