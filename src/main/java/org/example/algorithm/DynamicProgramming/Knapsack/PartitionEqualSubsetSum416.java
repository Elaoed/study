package org.example.algorithm.DynamicProgramming.Knapsack;

import java.util.Arrays;

/**
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 */
public class PartitionEqualSubsetSum416 {

    public static boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int total = Arrays.stream(nums).sum();
        if (total % 2 == 1) {
            return false;
        }

        // 抓住隐含条件target
        int target = total / 2;
        for (int num : nums) {
            if (num > target) {
                return false;
            }
        }

        // 0, 1背包可以用boolean来做
        boolean[] dp = new boolean[nums.length + 1];
        // 重量为0的背包能不能被当前的石子满足？ 可
        dp[0] = true;
        // dp[i] 依赖于 dp[i - num]
        // dp里面存的肯定是当前这个重量能不能满足条件
        for (int num : nums) {
            // target到num之间，背包能不能被满足，target = num的时候肯定能，再往下就不关心了
            // 但是这个为什么是慢慢从上往下呢 dp不是从下往上吗, 这里就是原先我困惑的问题，后面的放前面是最优解怎么办
            // i = target, i >= num 的语义是 关心从target -> num 的过程中产生的最优解 拿这个最优解冲
            for (int i = target; i >= num; i--) {
                // 问题是怎么找到这个转移方程
                dp[i] = dp[i] || dp[i - num];
                // dp[i] = dp[i] + dp[i - num];
                // 如果只有两步的话是 dp[i] = dp[i - 1] + dp[i - 2];

            }
        }

        return dp[target];

    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));

    }

}
