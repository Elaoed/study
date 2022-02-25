package org.example.algorithm.DynamicProgramming;

/**
 * 413. Arithmetic Slices
 * <p>
 * An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 * A subarray is a contiguous subsequence of the array.
 * // 还是要寻找到状态方程
 *
 */
public class ArithmeticSlices {

    public static int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        int[] res = new int[nums.length];
        int consecutive = 0;

        // 后一个位置的结果组成由前一个结果的组成 + 1/2/0 前提是等差 还不止 连续等差的时候会指数型增长内容
        for (int i = 0; i < nums.length; i++) {
            if (i < 2) {
                res[i] = 0;
                continue;
            }
            // 这边是不对的 既然是总量
            res[i] = res[i - 1];
            // 最新的满足等差数列的可以先加一个
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                consecutive += 1;
                res[i] += consecutive;
                // 算上最新的最多可以加几个  1 2 3 (1/1) 1 2 3 4 (2/3) 1 2 3 4 5 (3/6)
            } else {
                consecutive = 0;
            }
        }
        return res[res.length - 1];

    }

    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
        System.out.println(numberOfArithmeticSlices(new int[]{1}));

    }

}

