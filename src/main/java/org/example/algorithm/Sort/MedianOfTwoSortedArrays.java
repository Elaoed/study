package org.example.algorithm.Sort;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 * quickSort, quickSelect
 * 这道题难就难在对时间复杂度有要求
 * 如果时间复杂度是O(m + n) 那遍历一边找到第k个数就行了
 * 但凡摊上O(log(m + n)) 就需要用到二分法 不能傻乎乎的这么干了
 * 二分法/数组切割法
 */
public class MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 如果O(n + m)的话可以先merge再用kthLargest来处理，但是这题不行
        // 可不可以理解是两个数组加起来的快速选择呢 那就没有用到有序数组的这个条件了
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        // m和n在这里非常的重要，nums1的前m个元素和nums2的前n个元素相加刚好等于k, 因为中位数就是划一条中线前面有一半的元素 也就是k = totalLength / 2;
        // 一共k个元素，分到两个数组，m + n 不得等于k
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }

        // 因为nums1总小于等于nums2 所以只可能nums1.length == 0
        if (nums1.length == 0) {
            int half = nums2.length / 2;
            return nums2.length % 2 == 1 ? nums2[half] : (double)(nums2[half] + nums2[half - 1]) / 2;
        }

        // 下面的情况两个数组都有值
        int totalLength = nums1.length + nums2.length;
        int k = totalLength / 2;
        // 分奇偶，除非有小trick
        // 先模糊的找到两个数组的中位数然后再进行比较和偏移, 通过m+1 n-1或者m-1 n+1来进行偏移满足l1 l2都小于r1 r2的条件
        int m = nums1.length / 2;
        int n = k - m;

        // 可能需要判断边界吗？ 需要的 如果出现一个列表只有1个数字的话 只可能右边不存在
        int l1 = m <= 0 ? Integer.MIN_VALUE : nums1[m - 1];
        int r1 = m >= nums1.length ? Integer.MAX_VALUE : nums1[m];
        int l2 = n <= 0 ? Integer.MIN_VALUE : nums2[n - 1];
        int r2 = n >= nums2.length ? Integer.MAX_VALUE : nums2[n];

        while (!(l1 < r2 && l2 < r1)) {
            if (l1 > r2) {
                m--;
                n++;
            }
            if (l2 > r1) {
                m++;
                n--;
            }
            if (l1 > r2 || l2 > r1) {
                l1 = m <= 0 ? Integer.MIN_VALUE : nums1[m - 1];
                r1 = m >= nums1.length ? Integer.MAX_VALUE : nums1[m];
                l2 = n <= 0 ? Integer.MIN_VALUE : nums2[n - 1];
                r2 = n >= nums2.length ? Integer.MAX_VALUE : nums2[n];
            }

        }
        // 满足上方条件以后，我们的l1 r1 l2 r2就完成了 接下来就是要取左边的最大值和右边的最小值
        double lmax = Math.max(l1, r1);
        double rmin = Math.min(l2, r2);
        return totalLength % 2 == 1 ? lmax : (lmax + rmin) / 2;

    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 4};
        int[] nums2 = new int[]{3};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

}
