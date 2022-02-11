package org.example.algorithm.BinarySearch;

import java.util.Arrays;

/**
 * 1300. Sum of Mutated Array Closest to Targether
 * Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.
 * In case of a tie, return the minimum such integer.
 * Notice that the answer is not neccesarilly a number from arr.
 * 要进行二分法查找必须排序
 *
 * 常规的思路是先排序 然后挨个遍历 把数组内大于当前数的数都变成当前数 和target进行比较
 * 暴力拆解法 要保证arr的数量不大于10^5, 如果是10^9就不能这么做
 * 这不就是前缀和吗？ 挨个遍历的时候 后面n * 当前value + 前缀和
 *
 */
public class SumOfMutatedArrayClosestToTarget {

    public static int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        return 1;

    }

    public static void main(String[] args) {

    }
}
