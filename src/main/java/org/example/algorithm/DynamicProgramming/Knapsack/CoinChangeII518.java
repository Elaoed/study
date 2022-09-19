package org.example.algorithm.DynamicProgramming.Knapsack;

import java.util.Arrays;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount
 * representing a total amount of money.
 * Return the number of combinations that make up that amount. If that amount of money cannot be
 * made up by any combination of the coins, return 0.
 * You may assume that you have an infinite number of each kind of coin.
 * The answer is guaranteed to fit into a signed 32-bit integer.
 * // signed 32-bit integer 可正可负吗
 */
public class CoinChangeII518 {

    public static int change(int amount, int[] coins) {

        if (coins == null || coins.length == 0) {
            return 0;
        }
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, 0);
        }
//        Arrays.fill(dp[0], 0);
        // 0个硬币 组成0元的时候 有一种解法，and so? 其实还是0 不然每个都可以加一个0 上去
        // 类似斐波那契这种初始一定是有值的 不然都是0的话 后面所有都是0
//        dp[0][0] = 0;
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            int coin = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                // 最重要的是转移方程怎么写
                if (j >= coin) {
                    // 当数量少一个的时候 这个目标会有几种拼法 + 数量多一个的时候 是什么样的拼法
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coin];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length][amount];
    }

    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1, 2, 5}));
        System.out.println(change(2, new int[]{3}));
    }
}
