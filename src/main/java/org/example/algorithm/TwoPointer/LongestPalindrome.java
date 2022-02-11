package org.example.algorithm.TwoPointer;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 409. Longest Palindrome
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 * <p>
 * 括号可以用stack 但是这个不行，这个定义是严格从中间一刀切开，只能用双向指针
 * 审题啊审题 我滴个亲爹, 这是说拿这个字符串去组成一个回文，最大可以组成多少数字的
 * int left = s.length() / 2, right = s.length() / 2;
 * if (s.length() % 2 == 0) {
 * left = s.length() / 2 - 1;
 * }
 * 不过这个倒是背向指针一定会有的代码
 * index = 0 的length / 2之后一定会出现在右半部分的第一个, 如果是双数的话，单数会刚好出现在那一个单数上
 * 优化 hashMap看可不可以用nums优化
 */
public class LongestPalindrome {

    public static int longestPalindrome2(String s) {
        int[] cnt = new int[58]; // 干练
        Arrays.fill(cnt, 0);
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a'] += 1;
        }
        // 填充完毕

        int res = 0;
        for (int i : cnt) {
            res += i - (i & 1);
        }
        // 开始累加 i - (i & 1) 很好的判断了是否是odd 如果是odd的话就-1

        // 和原串对比 如果一样说明都是偶数， 不然就是奇数需要加回来
        return res < s.length() ? res + 1 : res;

    }

    public static int longestPalindrome(String s) {

        // 11122111   2221222
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 0);
            }
            map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        }

        int total = 0;
        boolean hasOdd = false;
        for (Integer value : map.values()) {
            if (value % 2 == 1) {
                hasOdd = true;
                total += value - 1;
            } else {
                total += value;
            }
        }
        return hasOdd ? total + 1 : total;


    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcccccdd"));
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("bb"));

    }
}
