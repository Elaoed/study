package org.example.algorithm.TwoPointer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that
 * the absolute difference between any two elements of this subarray is less than or equal to limit.
 * 我感觉这道题的核心，slideWindow解法中特别强的一点就是，如果一个子队列不满足条件 max - min <= limit, right再往右也没用，必须left往左
 */
public class LongestContinuousSubarrayWithAbsoluteDiff {

    /**
     * Monotonic版本，上下两种解法的模板都是一样的
     * 只是用的东西不一样，两个单调队列可以在O(n)的时间维护一个区间内的最大最小值
     */
    public static int monotonicVersion(int[] nums, int limit) {
        // 单调变大队列
        Deque<Integer> maxQueue = new LinkedList<>();
        // 单调变小队列
        Deque<Integer> minQueue = new LinkedList<>();
        // 单调递增队列会保护最好的小数，单调递减队列会保护最好的大数, 先塞一个8，再塞一个2 递增队列的8就没了 递增是要从最小开始，递减是要从最大开始 = = 所以是递减[0] - 递增[0]
        int maxDiff = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // 从单纯的插入变成了 判断条件选择是否插入两个单调队列 每个元素都要入队列，只是看会把老油条挤走多少个
            while (!maxQueue.isEmpty() && nums[right] < maxQueue.peekLast()) {
                maxQueue.pollLast();
            }
            while (!minQueue.isEmpty() && nums[right] > minQueue.peekLast()) {
                minQueue.pollLast();
            }
            maxQueue.offerLast(nums[right]);
            minQueue.offerLast(nums[right]);

            // 判断区间内的最大值和最小值的差和limit的关系，决定left的走向
            while (minQueue.peekFirst() - maxQueue.peekFirst() > limit) {
                // 随着left右移 窗口shrink 需要把单调队列里面最左边的数据删除 简单的==
                if (maxQueue.peekFirst() == nums[left]) {
                    maxQueue.pollFirst();
                }
                if (minQueue.peekFirst() == nums[left]) {
                    minQueue.pollFirst();
                }
                left++;
            }

            // 稳定了之后再看Diff
            maxDiff = Math.max(maxDiff, right - left + 1);

        }
        return maxDiff;
    }

    public static int longestSubarray(int[] nums, int limit) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        Deque<Integer> queue = new LinkedList<>();
        int maxDiff = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            queue.offerLast(nums[right]);
            // 为什么实例代码里面要用TreeMap因为可以帮忙给出最大最小值啊！ 这里需要的不是peekLast和peekFirst而是最大最小值！
            // TreeMap插入排序O(logn) * n长的列表 所有复杂度是O(nlongn)
            // 别急 过两天就刷到sliding window了
            while (!queue.isEmpty() && Math.abs(queue.peekLast() - queue.peekFirst()) > limit) {
                queue.pollFirst();
                left++;
            }
            maxDiff = Math.max(maxDiff, right - left + 1);
        }
        return maxDiff;

    }

    public static void main(String[] args) {
        System.out.println(monotonicVersion(new int[]{8, 2, 4, 7}, 4));
    }
}
