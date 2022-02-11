package org.example.algorithm.BinarySearch;

import java.util.Arrays;

/**
 * 1300. Sum of Mutated Array Closest to Targether
 * Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.
 * In case of a tie, return the minimum such integer.
 * Notice that the answer is not neccesarilly a number from arr.
 * 要进行二分法查找必须排序
 * <p>
 * 常规的思路是先排序 然后挨个遍历 把数组内大于当前数的数都变成当前数 和target进行比较
 * 暴力拆解法 要保证arr的数量不大于10^5, 如果是10^9就不能这么做
 * 这不就是前缀和吗？ 挨个遍历的时候 后面n * 当前value + 前缀和
 */
public class SumOfMutatedArrayClosestToTarget {

    public static int calTotal(int index, int[] prefixSum, int[] arr) {
        int prefixSumOne = index == 0 ? 0 : prefixSum[index - 1];
        return prefixSumOne + (arr.length - index) * arr[index];
    }

    public static int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);

        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        int n = 1;
        // n++ 永远要 < arr.length - 1
        while (n++ < arr.length - 1) {
            prefixSum[n] = arr[n] + prefixSum[n - 1];
        }

        int divideResult = target / arr.length;
        if (divideResult < arr[0]) {
            return target - divideResult * divideResult < (divideResult + 1) * (divideResult + 1) - target ? divideResult + 1 : divideResult;
        }

        int left = 0, right = arr.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            int total = calTotal(middle, prefixSum, arr);

            if (total == target) {
                return middle;
            }
            if (target < total) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        int leftResult = calTotal(left - 1, prefixSum, arr);
        int rightResult = calTotal(left, prefixSum, arr);
        return leftResult - target < rightResult - target ? left - 1 : left;
    }

    public static void main(String[] args) {
        System.out.println(findBestValue(new int[]{4, 9, 3}, 10));
//        System.out.println(findBestValue(new int[]{2, 3, 5}, 10));
//        System.out.println(findBestValue(new int[]{60864, 25176, 27249, 21296, 20204}, 56803));

    }
}
