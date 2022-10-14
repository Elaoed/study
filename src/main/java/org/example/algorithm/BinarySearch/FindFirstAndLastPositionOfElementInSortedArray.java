package org.example.algorithm.BinarySearch;

import java.util.Arrays;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 * 这道题基本上还是能一眼看出二分法的，sorted, O(log n) 都是标志 有一边的数据都是一样的比如bed version
 * 二分法的基本套路是咋样的？
 * 递归二分和迭代二分，必选迭代二分
 * 左右区间要搞清楚，左右都是开区间 left = 0, right = nums.length - 1
 * left <= right / 如果没找到默认返回 -1
 * 外层再对默认值进行判断
 *
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    // 每次要弄清楚 开区间还是闭区间
    public static int iterationVersion(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int middle;
        // while (left != right && right >= 0 && left < nums.length)
        // 上面这一段可以用left <= right来替代，原来的越界说白了不就是left > right了吗
        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        // 如果啥都没找到
        return -1;
    }

    // 他妈的这才不是二分，这是dc -> divide and conquer
    // 左开有开区间
    // recursive version
    public static int recursiveVersion(int[] nums, int target, int left, int right) {
        // 不够 这里还要加左右的限制条件
        int middle = (left + right) / 2;

        // 上下两个方式是等价的
//        if (left == right)  {
//            return nums[right] == target ? right : -1;
//        }
//        if (left > right) {
//            return -1;
//        }

        // 左右相等之后最后再判断一下
        if (left >= right && nums[middle] != target)  {
            return -1;
        }

        if (target > nums[middle]) {
            return recursiveVersion(nums, target, middle + 1, right);
        } else if (target < nums[middle]) {
            return recursiveVersion(nums, target, left, middle - 1);
        } else {
            return middle;
        }
    }

    // 为什么我总觉得是需要left and right的，不然middle的位置不好定啊
    public static int[] searchRange(int[] nums, int target) {

//        if (nums == null || nums.length < 2) {
//            return nums;
//        }

//        int index = binarySearch(nums, target, 0, nums.length - 1);
        int index = iterationVersion(nums, target);

        if (index == -1) {
            return new int[]{-1, -1};
        }

        int left = index;
        int right = index;
        while (left-- > 0 && nums[left] == nums[index]) {}
        while (right++ < nums.length - 1 && nums[right] == nums[index]) {}
        return new int[]{left + 1, right - 1};

    }

    public static void main(String[] args) {
//        int[] nums = {5, 7, 7, 8, 8, 10};
//        int target = 8;
        int[] nums = {1};
        int target = 1;
        System.out.println(Arrays.toString(searchRange(nums, target)));

    }

}
