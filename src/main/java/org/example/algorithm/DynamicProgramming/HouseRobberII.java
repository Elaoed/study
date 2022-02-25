package org.example.algorithm.DynamicProgramming;

/**
 * 198. House Robber
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 **/
public class HouseRobberII {

    //
    public static int rob(int[] nums) {

//        int[] amount = new int[nums.length];
//
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (i <= 1) {
//                amount[i] = nums[i];
//                continue;
//            }
//            amount[i] = amount[i - 2] + nums[i];
//        }
//
//        for (int i = 1; i < nums.length; i++) {
//            amount[i] = amount[i - 2] + nums[i];
//        }
//
//        return Math.max(amount[nums.length - 1], amount[nums.length - 2]);
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
    }

}
