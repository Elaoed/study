package org.example.algorithm.TwoPointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 * A substring is a contiguous sequence of characters within the string.
 * 每次遇到slideWindow的题，一定要先手画一下shrink and extend的流程
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {

        if (s == null || s.isEmpty()) {
            return s;
        }

        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Integer count = counter.getOrDefault(t.charAt(i), 0);
            counter.put(t.charAt(i), ++count);
        }

        // 必须
        Map<Character, LinkedList<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            indexMap.put(t.charAt(i), new LinkedList<>());
        }

        int left = 0, right = 0;
        int[] res = new int[]{-1, -1};
        while (right < s.length()) {

            // 不搭嘎
            if (!counter.containsKey(s.charAt(right))) {
                right++;
                continue;
            }

            char newChar = s.charAt(right);
            LinkedList<Integer> indexList = indexMap.get(newChar);
            Integer count = counter.get(newChar);
            indexList.add(right);
            // 这里 count就不做加减了 只做对比
            // 满足 这个条件之后 左边开始shrink
            while (indexList.size() > count) {
                final Integer pop = indexList.pop();
                left = Math.max(pop, left) + 1; // 我是要闭区间的 所以会考虑把left 放到pop + 1的位置
            }
            while (!counter.containsKey(s.charAt(left))) {
                left++;
            }


            // 果然需要check 贼麻烦 这里出循环的条件是 left == right 或者达到一个满足条件的状态了 所以需要一个check的函数
            // 所以说先做出来 再管复杂度，练习的时候可以看题解看下思路对不对 而且可以先整个的过一遍
            // 扣多了就不满足条件了 要左边shrink到满足条件 所有的元素都 >= 0;
            // 既然只有newChar扣多了 shrink到newChar == 0为止，但是这势必会带来其他char的删除 需要在hashMap里面加回去
//            while (counter.get(s.charAt(right)) < 0) {
//                Integer leftCounter = counter.get(s.charAt(left));
//                if (leftCounter != null) {
//                    counter.put(s.charAt(left), leftCounter + 1);
//                }
//                left++;
//                // 原来是这里错了 minium 就是麻烦在这里 ，无关的字符串也要直接跳过 怎么在原有的框架里面跳过那几个数呢
//                // hashMap只能记数量能不能记位置呢 但是这里会有多个位置 脑瓜子疼
//            }
            // left 的最后位置在 跟rightChar相等的char的右边一格 完全满足要求

            // 最小也比最大更不好搞
            if (left != right && qualified(counter, indexMap) && (res[1] == -1 || right - left < res[1] - res[0])) {
                res = new int[]{left, right};
                // 给我整不会了
                // 先跳过把
            }

            right++;
        }

        return s.substring(res[0], res[1] + 1);

    }

    //
    public static boolean qualified(Map<Character, Integer> counter, Map<Character, LinkedList<Integer>> indexMap) {
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            if (entry.getValue() != indexMap.get(entry.getKey()).size()) {
                return false;
            }
        }
        return true;
    }

    // 所有的count都 >= 0, 右指针可以继续了, 所以这里要判断但凡有一个<0 左指针就要继续
//    public static boolean hasLessThanZero(Map<Character, Integer> counter) {
//        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
//            if (entry.getValue() < 0) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static void main(String[] args) {
//        System.out.println(minWindow("ADBECOEBAC", "ABC"));
        String s = "ABC";
        System.out.println(s.indexOf('C'));
        System.out.println(s.indexOf('C'));


    }
}
