package org.example.algorithm.TwoPointer;

import java.util.Arrays;

/**
 * 16. 3Sum Closest
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution. // 不需要去重
 * <p>
 * 一看这个题目就是ThreeSum的变种版，就是那种from exactly has result in given array to find closest
 */
public class ThreeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        Arrays.sort(nums);

        // 无非从原来的返回条件 == 变成了 Math.min();
        Integer closest = null;
        for (int first = 0; first < nums.length - 2; first++) {
            int second = first + 1;
            int third = nums.length - 1;
            while (second < third) {
                int sum = nums[first] + nums[second] + nums[third];
                // 更贴近 这个closest怎么算
                if (closest == null || Math.abs(target - sum) < Math.abs(target - closest)) {
                    closest = sum;
                }
                // 这里是不是 需要两次遍历？
                // 这里应该是有判断条件决定 是左++还是右--
                if (target == sum) {
                    return target;
                    // TODO: 我这里怎么老师搞错
                } else if (target > sum) {
                    second++;
                } else {
                    third--;
                }
            }

        }
        return closest;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 2, 1, -3};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
        nums = new int[]{0, 0, 0};
        System.out.println(threeSumClosest(nums, target));

    }

}
