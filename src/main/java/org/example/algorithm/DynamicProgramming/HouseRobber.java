package org.example.algorithm.DynamicProgramming;

/**
 * 198. House Robber
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems
 * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * 这种就感觉是很典型的dp题 重叠子问题，自下而上求最终值
 * 最重要的就是学会怎么找到状态转移方程
 * 其实也差不多，从左抢到右，有一个数组会记下来抢到当前这个房子为止，最多能抢多少
 */
public class HouseRobber {

    public static int rob(int[] nums) {

        int[] amount = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i <= 1) {
                amount[i] = nums[i];
                continue;
            }
            amount[i] = amount[i - 2] + nums[i];
        }

        return Math.max(amount[nums.length - 1], amount[nums.length - 2]);
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
    }

}
