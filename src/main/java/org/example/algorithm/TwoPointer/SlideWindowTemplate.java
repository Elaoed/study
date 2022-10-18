package org.example.algorithm.TwoPointer;

import org.example.algorithm.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://zhuanlan.zhihu.com/p/390570255
 * 滑动窗口适用条件: 数组 + 窗口 /  像题目中是要求窗口内最大值和最小值的差不是左右两端的，需要用到单调队列
 * Sliding Window: 快速确定移动窗口中的最大最小值
 * 1. Each time expend the window to the right;
 * 2. Shrink the left side to make it valid;
 * Key: query the min/max of a dynamic subarry
 * 因为left和right都只会走一次，所以可以直接for(int right = 0; right < length; right++) 的方式走
 */
public class SlideWindowTemplate {

    public static void template(int[] nums) {

        for (int right = 0; right < nums.length; right++) {
            // 先把滑动窗口向右拖动一格 让新元素加入
            // queue.offerLast(nums[right]);
            // 根据条件判断是否要最左侧收缩 决定left的走向，满足条件就继续 不满足条件就收缩 收缩要用while直到收缩到满足条件为止
            // queue.pollFirst()
        }
    }
    public static void main(String[] args) {

    }
}
