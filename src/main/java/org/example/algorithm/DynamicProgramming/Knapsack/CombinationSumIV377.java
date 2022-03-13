package org.example.algorithm.DynamicProgramming.Knapsack;

import java.util.Arrays;

/**
 * Given an array of distinct integers nums and a target integer target,
 * return the number of possible combinations that add up to target.
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 * 这就跟518一样是求所有的数量的 完全背包求所有路径
 * 而且这一题还是有顺序的
 * 如果组合问题需考虑元素之间的顺序，需将 target 放在外循环，将 arrs 放在内循环，且内循环正序。
 * 组合问题要找开始的1 以及看是不是需要有序的
 */
public class CombinationSumIV377 {

    public static int combinationSum4(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[][] dp = new int[nums.length + 1][target + 1];
        Arrays.fill(dp[0], 0);
        dp[0][0] = 1;

        int[] dp1 = new int[target + 1];
        dp1[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp1[i] += dp1[i - num]; // 组合问题统一解法
                }
            }
        }

        return dp1[target];
//        for (int i = 1; i <= nums.length; i++) {
//            int num = nums[i - 1];
//            for (int j = 0; j <= target; j++) {
//                if (j >= num) {
//                    dp[i][j] = dp[i - 1][j] + dp[i][j - num];
//                } else {
//                    dp[i][j] = dp[i - 1][j];
//                }
//            }
//        }

    }

    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));
    }

}
