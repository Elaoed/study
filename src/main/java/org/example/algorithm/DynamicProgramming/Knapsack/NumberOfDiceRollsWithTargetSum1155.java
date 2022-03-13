package org.example.algorithm.DynamicProgramming.Knapsack;

/**
 * You have n dice and each die has k faces numbered from 1 to k.
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the
 * dice so the sum of the face-up numbers equals target. Since the answer may be too large,
 * return it modulo 109 + 7.
 * 窝tm这是分组背包🎒
 * 在分组背包问题中我们提到，分组背包不仅仅有「组内物品最多选择一个」的情况，还存在「组内物品必须选择一个」的情况
 */
public class NumberOfDiceRollsWithTargetSum1155 {

    // n次 k面 target和 这是属于有序组合背包 (也只有组合背包会出现有序的问题)
    // 0, 1问题 0, N问题
    public static int numRollsToTarget(int n, int k, int target) {

        if (target < n || target > n * k) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // target在外面 nums在里面

        // dp[i][j] = 表示考虑前i个物品组，凑成价值为j的方案数。
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        // 枚举物品组 nums
        for (int i = 1; i <= n; i++) {
            // 枚举背包容量
            for (int j = 0; j <= target; j++) {
                // 枚举决策 K面的色子 为什么从i开始？
                for (int z = i; z <= k; z++) {
                    if (j >= k) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % 100000007;
                    }
                }
            }
        }

        return dp[k + 1][target + 1];


    }

    public static void main(String[] args) {
        System.out.println(numRollsToTarget(1, 6, 3));
    }

}
