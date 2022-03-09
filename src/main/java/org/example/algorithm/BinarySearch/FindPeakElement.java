package org.example.algorithm.BinarySearch;

/**
 * 162. Find Peak Element
 * A peak element is an element that is strictly greater than its neighbors.
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 * You may imagine that nums[-1] = nums[n] = -∞.
 * You must write an algorithm that runs in O(log n) time.
 *
 * 看这里需要考虑的几个点
 * 最最明显的就是两个边界 可以直接考虑掉
 * 其次是原来是做山峰的，没有山谷这么一说，代码里面要考虑新的山谷的情况
 * 这种二分都是先写return的条件比较简单和明了 然后不是山顶的时候 考虑下左坡和右坡，else再考虑山谷
 */
public class FindPeakElement {

    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        if (nums[0] > nums[1]) {
            return 0;
        }

        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (middle > 0 && middle < nums.length - 1 && nums[middle] > nums[middle - 1] && nums[middle] > nums[middle + 1]) {
                return middle;
            }

            // 这段要怎么处理
            if (middle > 0 && nums[middle] > nums[middle - 1]) {
                left = middle;
            } else if (nums[middle] > nums[middle + 1]) {
                right = middle;
            } else {
                // 谷底的情况, 随便往左边或者右边走一下
                if (middle > 0) {
                    right -= 2;
                } else {
                    left += 2;
                }
            }

        }
        return 0;

    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 3, 1})); // 应该返回2
    }

}
