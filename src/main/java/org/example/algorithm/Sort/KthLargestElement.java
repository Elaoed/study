package org.example.algorithm.Sort;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 1. quick Sort
 * 2. quick Select -> a side of quick sort 快速选择在快速排序的基础之上还蛮简单的 稍微改造一下 主要k要传进去 而且数值要变一下
 */
public class KthLargestElement {

    private static int getIndex(int[] nums, int left, int right) {
        // 先把最左边的放着
        // 再找到最右边比tmp小的放到左边
        // 再找到最左边比tmp大的放右边
        int tmp = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= tmp) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= tmp) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = tmp;
        return left;
    }

    // quickSort
    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = getIndex(nums, left, right);
        quickSort(nums, left, index - 1);
        quickSort(nums, index + 1, right);
    }

    public static int findKthLargest(int[] nums, int k) {
        if (nums.length <= 1) {
            return nums[0];
        }
//        quickSort(nums, 0, nums.length - 1);
        quickSelect(nums, 0, nums.length - 1, nums.length - k);
        return nums[nums.length  - k];
    }

    public static void quickSelect(int[] nums, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int index = getIndex(nums, left, right);
        if (index < k) {
            quickSelect(nums, index + 1, right, k);
        } else if (index > k) {
            quickSelect(nums, left, index - 1, k);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        int i = findKthLargest(nums, k);
        System.out.println(i); // answer should be 5
    }

}
