package org.example.algorithm.TwoPointer;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 76. Minimum Window Substring
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 * A substring is a contiguous sequence of characters within the string.
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {

        if (s == null || s.isEmpty()) {
            return s;
        }

        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Integer count = counter.getOrDefault(t.charAt(i), 0);
            counter.put(t.charAt(i), count + 1);
        }

        int left = 0, right = 0;
        int[] res = new int[]{0, 0};
        while (right < s.length()) {
            Integer rightCount = counter.get(s.charAt(right));
            // 不搭嘎
            if (rightCount == null) {
                right++;
                continue;
            }
            counter.put(s.charAt(right), rightCount - 1);

            // 果然需要check 贼麻烦
            while (counter.get(s.charAt(right)) <= 0) {
                Integer leftCounter = counter.get(s.charAt(left));
                if (leftCounter != null) {
                    counter.put(s.charAt(left), leftCounter + 1);
                }
                left++;
            }

            if (res[1] - res[0] == 0 || right - left < res[1] - res[0]) {
                res = new int[]{left, right};
            }

            right++;
        }

        return s.substring(res[0], res[1] + 1);

    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));

    }
}
