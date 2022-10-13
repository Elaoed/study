package org.example.algorithm.BaseDatastructure.Heap;

import java.util.Arrays;

/**
 * 88. Merge Sorted Array
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 *
 */
public class MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int pm = m - 1;
        int pn = n - 1;
        for (int i = nums1.length - 1; i >= 0; i--) {
            if (pm < 0) {
                nums1[i] = nums2[pn];
                pn--;
            } else if (pn < 0) {
                nums1[i] = nums1[pm];
                pm--;
            } else if (nums1[pm] > nums2[pn]) {
                nums1[i] = nums1[pm];
                pm--;
            } else {
                nums1[i] = nums2[pn];
                pn--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        merge(nums1, 3, new int[]{2, 5, 6}, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
