package org.example.algorithm.DynamicProgramming.Knapsack;

import java.util.Arrays;

/**
 * You are given an integer array nums and an integer target.
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 * 0, 1 背包
 * 两种情况 组合成为target 求组合的数量
 * dp的下标代表target dp的内容代表当前target下最大的总数
 * 问题是如何把operator融入到里面去, 这部分是要放到转移方程里面去 代替原先的选与不选
 * 这题还有一个要注意的点就是在画dp表格的时候 同样有两个1 得到target = 0有两种情况 +1 -1 和 -1 +1
 * 还有non-negative 是0和正整数 总是要考虑0对整个公式的影响
 */
public class TargetSum494 {

    public static int findTargetSumWays(int[] nums, int target) {

        int sum = Arrays.stream(nums).sum();
        // 绝对值范围超过了sum的绝对值范围则无法得到
        if (Math.abs(sum) < Math.abs(target)) {
            return 0;
        }

        int len = nums.length;
        // - 0 + TODO: 重点 这个其实是表的宽度, 也就是说target也是隐性的, 所以平时放target + 1只是因为target + 1和表宽是一样的
        int t = sum * 2 + 1;
        int[][] dp = new int[len][t];

        // 初始化 初始化成为了最右边的人 为什么呢？ 最右边的人哦宝，而是最中间的那个 那为什么等于2呢
        // 初始化 初始化其实是初始化第一行的数据
//        if (nums[0] == 0) {
//            // 因为0的时候 +0 -0 都能达到目的 所以初始化是2
//            dp[0][sum] = 2;
//        } else {
//            // 这边也是 为什么等于1呢 左右的对应target区域通过 +/-能直接得到
//            dp[0][sum + nums[0]] = 1;
//            dp[0][sum - nums[0]] = 1;
//        }
        // 初始化初始化 还是跟第一个元素 nums[0]有关系的 初始化的简化版本
        dp[0][sum + nums[0]]++;
        dp[0][sum - nums[0]]++;

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < t; j++) {
                // 边界 从-x到0到+x
                // 这代码写的舒服 再考虑边界
                int l = Math.max((j - nums[i]), 0);
                int r = (j + nums[i]) < t ? j + nums[i] : 0;
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }
        }
        return dp[len - 1][sum + target];
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1, 1, 1, 1, 1};
        int target = 3;

        System.out.println(findTargetSumWays(nums, target));

        // for num
        // for operation❌
        // for target
//        for (int num : nums) {
//            for (int i = target; i > 0; i--) {
//                // 因为涉及到有加有减 所以不能用一维的数组了 不管是正序还是倒序(按照公式应该用倒序) 总有一边是要被先覆盖的
//                // dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]] 妙啊
//                // 这时候就是j从0慢慢变大 上一层已经都经历过了 所以不管是 j - nums[i] 还是 j + nums[i] 都会有值
//                // j 是什么 target
//
//            }
//        }


    }
}
