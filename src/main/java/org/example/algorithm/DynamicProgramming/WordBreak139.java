package org.example.algorithm.DynamicProgramming;

import java.util.Arrays;
import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation
 * // 台阶问题 只不过这里的步数是一个List里面的内容 从尾巴开始到后面
 * leetcode = fund(leet) + code
 * 那用什么结构来存储呢
 * dp里面存的应该是下标把
 * 从这一题引申出背包问题？
 * https://leetcode-cn.com/problems/word-break/solution/dong-tai-gui-hua-ji-yi-hua-hui-su-zhu-xing-jie-shi/
 */
public class WordBreak139 {

    public static boolean wordBreak(String s, List<String> wordDict) {

        if (s == null || s.isEmpty() || wordDict == null || wordDict.size() == 0) {
            return true;
        }

        // 加个哨兵
        s = " " + s;
        boolean[] dp = new boolean[s.length()];
        Arrays.fill(dp, false);
        dp[0] = true;

        // 每次i都是上一个单词的最后
        // word = 4, s = " " + word = 5, dp = 5
        // 如果要考虑前一位的影响就需要加个不碍事的哨兵(满足条件)
        for (int i = 1; i < s.length(); i++) {
            for (String word : wordDict) {
                int nextWordFirstPos = i + word.length();
                // nextWordFirstPos = 5
                // 这里乱七八糟的加一减一其实只要拿1-2个case测试一下就行了
                if (dp[i - 1]
                        && nextWordFirstPos - 1 < s.length()
                        && word.equals(s.substring(i, nextWordFirstPos))) {
                    dp[nextWordFirstPos - 1] = true;
                }
            }
        }

        return dp[dp.length - 1];

    }

    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
    }

}
