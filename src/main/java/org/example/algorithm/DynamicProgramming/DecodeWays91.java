package org.example.algorithm.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 浓浓的dp味儿
 * 这种题目把转移方程的定位放到第一要务
 * 因为'0'的存在，把当前的分为三块儿
 * 1. '0', 需要和[i - 1]合并，所以保持不变
 * 2. !'0' 不能和之前的合并处理，也是保持不变
 * 3. !'0' 可以和之前的合并处理，也可以分开处理，再多加1
 *
 * 其他细节：由于题目存在前导零，而前导零属于无效 item。可以进行特判，但个人习惯往字符串头部追加空格作为哨兵，
 * 追加空格既可以避免讨论前导零，也能使下标从 1 开始，简化 f[i-1] 等负数下标的判断。
 * 我以为跟i - 1, i - 2有关呢
 */
public class DecodeWays91 {

    public static boolean isValid(String s) {
        if (s.charAt(0) > 2) {
            return false;
        }
        if (s.charAt(0) == 2 && s.charAt(1) > 6) {
            return false;
        }
        if (s.charAt(0) == '0') {
            return false;
        }
        return true;
    }

    public static int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }

        // dp里面装的都是到这一步的时候 除了转移方程 考虑dp里面装的到底是什么
        // climbingStairs dp里面装的是当前的几种步数，这不是一样的吗, 还不太一样 那边求的是走几步能走到
        // 一模一样的宝，how many distinct ways
        // 这里就多了一个'0'的判断选项
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, 1);

        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) == '0') {
                dp[i] = dp[i - 1];
            }
            if (isValid(s.substring(i - 1, i + 1))) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));

    }
}
