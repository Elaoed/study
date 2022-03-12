package org.example.algorithm.DynamicProgramming.Knapsack;

import java.util.Arrays;

/**
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * target = sum([]) / 2;
 * 0, 1背包, 是否存在
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

        // 这里一定是target + 1
        final boolean[] dp = new boolean[target + 1];
        Arrays.fill(dp, false);
        dp[0] = true;

        // 外层什么时候用for 什么时候用fori 很简单 如果有多个条件要通过i来取对应值的时候 比如背包中的value跟weight
        // 0, 1背包 内部倒序
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }

        // 都是target啊小弟
        return dp[target];

    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));

    }

}
