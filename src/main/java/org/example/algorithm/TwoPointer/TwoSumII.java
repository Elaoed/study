package org.example.algorithm.TwoPointer;

import java.util.Arrays;

/**
 * 167. Two Sum II - Input Array Is Sorted
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 * Your solution must use only constant extra space.
 *
 * 除了用双指针法，还可以用一个固定，余数用二分法查找的方式
 *
 */
public class TwoSumII {

    public static int[] twoSum(int[] numbers, int target) {

        if (numbers == null || numbers.length == 1) {
            return new int[]{};
        }

        // 正常逻辑是这样的 还是我傻逼了...
        int left = 1, right = numbers.length;
        while (left < right) {
            if (numbers[left - 1] + numbers[right - 1] == target) {
                return new int[]{left, right};
            }
            if (numbers[left - 1] + numbers[right - 1] > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{};

    }

    public static void main(String[] args) {
        int[] nums = new int[3000];
        Arrays.fill(nums, -1);
        nums[2998] = 1;
        nums[2999] = 1;
        int k = 2;
        System.out.println(Arrays.toString(twoSum(nums, k)));

    }
}
