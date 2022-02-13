package org.example.algorithm.TwoPointer;

import java.util.Arrays;

/**
 * 424. Longest Repeating Character Replacement
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 */
public class LongestRepeatingCharacterReplacement {

    // 有了这两个值就好做了
    public static int calcMostCharAndDiffNum(String s, int left, int right) {

        int[] counter = new int[26];
        Arrays.fill(counter, 0);
        for (int i = left; i <= right; i++) {
            counter[s.charAt(i) - 'A']++;
        }
        int mostIndex = 0;
        int diffNum = 0;
        for (int i = 0; i < counter.length; i++) {
            diffNum += counter[i];
            if (counter[i] > counter[mostIndex]) {
                mostIndex = i;
            }
        }
        return diffNum - counter[mostIndex];

    }

    public static int characterReplacement(String s, int k) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int[] counter = new int[26];
        Arrays.fill(counter, 0);
        // 滑动窗口 最重要的是什么时候往左指针前进, diffCharNum的时候左边前进 只是这个differentChar很难计算，干脆就不维护了，每次计算好了
        // 同理mostChar也不维护了
        int left = 0, right = 1, longest = 0;
        int maxn = 0;
        while (right < s.length()) {
            // 右指针前进会加，左指针前进会减 所以不用每次都写，
            counter[s.charAt(right) - 'A']++;
            // 想法是一样的, 拿diffN 和maxn原理是一样的, 如果只是需要最多的值的数量，相当于是把数组分成了两类，最多的和其他的，用一个maxn就够了

            // 每次走过去的时候计算一下好了，左边也过去的时候不再重新计算一下？
            // 只有在右指针往前走的时候，新的这个元素才可能成为maxn
            maxn = Math.max(maxn, counter[s.charAt(right) - 'A']);
            while (right - left + 1 - maxn > k) {
                // 左边走过去的时候不需要重新计算maxn吗, mostChar和secondMostChar
                // 不用吧 不管怎么样都不会超过原来的xn
                counter[s.charAt(left++) - 'A']--;
            }

            // 满足update longest的条件
            // diff <= k;
            longest = Math.max(longest, right - left + 1);
            right++;

        }

        return longest;

    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABABBBB", 2));
    }

}
