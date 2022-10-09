package org.example.algorithm.Sort;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 1. quick Sort
 * 2. quick Select -> a side of quick sort 快速选择在快速排序的基础之上还蛮简单的。稍微改造一下 主要k要传进去 而且数值要变一下
 * 打脸了，蛮简单的，然后我看代码有点看不懂。
 * 实际上是可以减少一边的排序，如果要第K个，getIndex的结果比K小，说明只要排右边，左边不用排了，反正都比K小，反之只要排左边。
 * 我们可以引入随机化来加速过程 防止性能倒退到O(N^2)
 */
public class KthLargestElement {

    // quickSort
    private final static Random random = new Random(System.currentTimeMillis());

    public static int priorityQueueVersion(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        // 有了最小堆之后，拿掉前面的n - k
    }

    public static int findKthLargest(int[] nums, int k) {
        // int randomIndex = left + random.nextInt(right - left + 1);
        // swap(nums, left, randomIndex);
        // 找个地方塞进去

        if (nums.length <= 1) {
            return nums[0];
        }
//        QuickSort.quickSort(nums, 0, nums.length - 1);
        quickSelect(nums, 0, nums.length - 1, nums.length - k);
        return nums[nums.length - k];
    }

    public static void quickSelect(int[] nums, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int index = QuickSort.getIndex(nums, left, right);
        if (index > k) {
            quickSelect(nums, left, index - 1, k);
        } else if (index < k) {
            quickSelect(nums, index + 1, right, k);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        int i = findKthLargest(nums, k);
        System.out.println(i); // answer should be 5
    }

}
