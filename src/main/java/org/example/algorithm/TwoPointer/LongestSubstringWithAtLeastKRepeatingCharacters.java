package org.example.algorithm.TwoPointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 395. Longest Substring with At Least K Repeating Characters
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of
 * each character in this substring is greater than or equal to k.
 * 核心都是一样的，有一个int longest = 0;和counter, 一旦发现counter里面被加的那个值为 < K 就要中断了, 更新longest // 错误！
 * 滑动窗口, 在这道题里面最重要的是维护当前subString中出现次数小于K的数, 当subString为空 则更新longest
 * 无法使用二分是因为不具备有二分的条件，当一个subArray满足条件的情况下 再加一个元素不一定能满足条件
 * 可枚举的东西是可以利用起来的，用来减少复杂度
 * 那啥二刷一次过 nice!
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    public static int longestSubstring2(String s, int k) {
        // 先维护一个计数器，如果计数器里面某个东西在整个字符串内小于k 那就通过字符串split, 去计算剩下的subArray
        // divide and conquer 不止是divide成两份也可能是split成n份
        // 后面算法还是用python做好了 快一点
        // 当有三个值不满足条件
        // 判空，left, right, hashMap 中
        if (s == null || s.length() < k) {
            return 0;
        }

        // 有了题解之后感觉就好写很多 思路最重要细节我自己把控
        // 通过根据s中不满足k个数的char进行分割 对每一块儿再进行递归的分割 然后挨个比较
        HashMap<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        int max = 0;
        for (Character c: counter.keySet()) {
            if (counter.get(c) < k) {
                for (String t: s.split(String.valueOf(c))) {
                    if (!t.isEmpty()) {
                        max = Math.max(max, longestSubstring2(t, k));
                    }
                }
                return max;
            }
        }
        // 整个子串都可以
        return s.length();

    }

    // 害 虽然能看懂，但是还在我的理解范围之外，起码核心思想是这样的，也不能这么说，我就是遇到这种题目不知道应该从哪个角度来解析
    public static int longestSubstring(String s, int k) {
        int res = 0;
        int[] cnt = new int[26];
        for (int curKind = 1; curKind <= 26; curKind++) {
            Arrays.fill(cnt, 0); // 也就是说 每次我们的假设在变 这个复杂度其实很高，但是第一步是需要能把题目做出来再考虑优化

            // 滑动窗口的左右两边
            int left = 0, right = 0;
            // tot是subString里面字符的数量
            int totalKind = 0;
            // sum是达标数量
            int qualifiedKind = 0;

            // 但凡右边走一步，要考虑走一步对变量的影响，并且要开始循环判断左边是否要shrink
            while (right < s.length()) {
                int rightIndex = s.charAt(right) - 'a';
                cnt[rightIndex]++;
                if (cnt[rightIndex] == 1) {
                    totalKind++;
                }
                if (cnt[rightIndex] == k) {
                    qualifiedKind++;
                }

                while (totalKind > curKind) {
                    int leftIndex = s.charAt(left) - 'a';
                    if (cnt[leftIndex] == 1) {
                        totalKind--;
                    }
                    if (cnt[leftIndex] == k - 1) {
                        qualifiedKind--;
                    }
                    left++;
                }

                if (totalKind == qualifiedKind) {
                    res = Math.max(res, right - left + 1);
                }
                right++;
            }
        }

        return res;

    }

    public static void main(String[] args) {
//        System.out.println(longestSubstring2("aaabb", 2));
        String s = "1232a3b";
        System.out.println(Arrays.toString(s.split("2|3")));
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        for (Map.Entry<Character, Integer> entry: characterIntegerHashMap.entrySet()) {

        }

    }

}
