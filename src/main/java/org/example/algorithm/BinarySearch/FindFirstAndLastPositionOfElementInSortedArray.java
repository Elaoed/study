package org.example.algorithm.BinarySearch;

import java.util.Arrays;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 * 这道题基本上还是能一眼看出二分法的，sorted, O(log n) 都是标志
 * 二分法的基本套路是咋样的？
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    // 他妈的这才不是二分，这是dc -> divide and conquer
    // 左开有开区间
    public static int binarySearch(int[] nums, int target, int left, int right) {
        // 不够 这里还要加左右的限制条件
        int middle = (left + right) / 2;
        if (left == middle || right == middle) {
            return -1;
        }

        if (target > nums[middle]) {
            return binarySearch(nums, target, middle + 1, right);
        } else if (target < nums[middle]) {
            return binarySearch(nums, target, left, middle - 1);
        } else {
            return middle;
        }
    }

    // 为什么我总觉得是需要left and right的，不然middle的位置不好定啊
    public static int[] searchRange(int[] nums, int target) {

        int index = binarySearch(nums, target, 0, nums.length - 1);

        if (index == -1) {
            return new int[]{-1, -1};
        }

        int left = index;
        int right = index;
        while (left-- > 0 && nums[left] == nums[index]) {}
        while (right++ < nums.length && nums[right] == nums[index]) {}
        return new int[]{left + 1, right - 1};

    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println(Arrays.toString(searchRange(nums, target)));

    }

}
