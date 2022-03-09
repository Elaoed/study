package org.example.algorithm.Sort;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 * quickSort, quickSelect
 * 这道题难就难在对时间复杂度有要求
 * 如果时间复杂度是O(m + n) 那遍历一边找到第k个数就行了
 * 但凡摊上O(log(m + n)) 就需要用到二分法 不能傻乎乎的这么干了
 * // 二分法/数组切割法
 */
public class MedianOfTwoSortedArrays {

    public static int merge(int[] nums1, int[] nums2, int k) {
        return 0;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {


        int totalNums = nums1.length + nums2.length;
        int res;
        if ((totalNums) % 2 == 1) {
            // 取第几个？ len1 + len2 取下标为len1 + len2
            res = merge(nums1, nums2, totalNums / 2 + 1);
        } else {
            // 取下标为(len1 + len2) / 2, (len1 + len2) / 2 - 1
            final int n1 = merge(nums1, nums2, totalNums / 2);
            final int n2 = merge(nums1, nums2, totalNums / 2 + 1);
            res = n1 + n2 / 2;

        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

}
