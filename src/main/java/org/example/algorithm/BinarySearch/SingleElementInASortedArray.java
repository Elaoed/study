package org.example.algorithm.BinarySearch;

/**
 * 540. Single Element in a Sorted Array
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * Return the single element that appears only once.
 * Your solution must run in O(log n) time and O(1) space.
 * // 这里需要考虑的点
 * // 应该说整一个二分或者算法要考虑的点
 * 1. 考虑题干，分析出来有哪些特征，需要用什么数据结构
 * 2. 写先行校验条件, 基本上要考虑到第二个元素为止, 还有最尾部条件的考虑, 所以list题目都有dummyHead和dummyTail
 * 3. 考虑循环停止的条件 left < right 肯定是要写的，列表中能不能找到具体的数字, 如果找不到的话, 要不要考虑返回
 * 4. 特别是什么时候走right = middle, 什么时候走left = middle + 1, 这道题就是很好的一个二分的特殊例子
 * 5. 注意返回的到底是下标还是值
 *
 */
public class SingleElementInASortedArray {

    public static int singleNonDuplicate(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums[1] > nums[0]) {
            return 0;
        }

        if (nums[nums.length - 2] < nums[nums.length - 1]) {
            return nums.length - 1;
        }

        int left = 0, right = nums.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (middle > 0 && middle < nums.length - 1 && nums[middle - 1] < nums[middle] && nums[middle] < nums[middle + 1]) {
                return nums[middle];
            }
            // 应该是判断middle的到底左边是连数还是右边是连数来判断左右, 那不是发热, 后面多加一对也会对这个造成影响
            // 所以差不多 还是要先判断左边还是右边，然后找到左边
            if (nums[middle] == nums[middle - 1]) {
                middle --;
            }
            // 现在middle都指向左边那个
            if (middle % 2 == 0) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
//        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }
}
