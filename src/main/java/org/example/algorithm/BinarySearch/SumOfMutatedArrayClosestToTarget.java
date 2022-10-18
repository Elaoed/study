package org.example.algorithm.BinarySearch;

import java.util.Arrays;

/**
 * 1300. Sum of Mutated Array Closest to Targether
 * Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.
 * In case of a tie, return the minimum such integer.
 * Notice that the answer is not neccesarilly a number from arr.
 * 要进行二分法查找必须排序
 * 常规的思路是先排序 然后挨个遍历 把数组内大于当前数的数都变成当前数 和target进行比较
 * 暴力拆解法 要保证arr的数量不大于10^5, 如果是10^9就不能这么做
 * 看我这次能不能跨过之前的坎 跨过了就去打游戏 芜湖 跨过去了
 */
public class SumOfMutatedArrayClosestToTarget {

    public static int findBestValueVersion3(int[] arr, int target) {
        Arrays.sort(arr);
        int len = arr.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            // 这个值非常的重要
            int avg = (target - sum) / (len - i);
            // 拿之前需要的平均数和当前的值比较
            if (avg <= arr[i]) {
                // 转换成double
                double curAvg = (target * 1.0 - sum) / (len - i);
                if (curAvg - avg <= 0.5) {
                    return avg;
                } else {
                    return avg + 1;
                }
            }
            // avg >= arr[i] 说明当前的这个值还不够打的 要往右边再找找
            sum += arr[i];
        }
        return arr[len - 1];
    }

    /**
     * 我真的给搞裂开了 没事了宝 搞定了 现在看看优秀者们的思路
     */
    public static int findBestValueVersion2(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int length = arr.length;
        int[] prefixSum = new int[length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < length; i ++) {
            prefixSum[i] = arr[i] + prefixSum[i - 1];
        }

        // System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(prefixSum));

        int left = 0, right = length;
        // 考虑边界的时候 里面有==所以 = 0的时候还要来走一道然后选择right 因为right没有变
        while (left < right) {
            int mid = left + (right - left) / 2;
            // System.out.println("left: " + left + ", right: " + right + ", mid: " + mid);
            if (prefixSum[mid] > target) {
                right = mid;
            } else {
                // prefixSum[mid]就是mid位置及其前面所有的和
                // 找出一个数 == target那最好 >target的话 right = mid, 找到最后的right 结果是arr[right - 1];
                // 我错了宝 这里是比value要大的值都改成value而不是右侧 排序啊傻逼 还是对的
                int value = prefixSum[mid] + (length - 1 - mid) * arr[mid];
                // System.out.println("value: " + value);
                if (value == target) {
                    return arr[mid];
                } else if (value > target) {
                    right = mid; // 比他大 可能是我们要的右边的值
                } else {
                    left = mid + 1;
                }
            }
        }

        // System.out.println("right: " + right);
        // 答案比right[0]还要小， 只能直接除了 左边界
        // 提前考虑边界的重要性
        int res;
        if (right == 0) {
            res = target / length;
            return Math.abs(res * length - target) > Math.abs((res + 1) * length - target) ? res + 1: res;
        }
        // 右边界 就只要最右边一个数就行了
        if (right == length) {
            return arr[length - 1];
        }

        // 这里right = 1 答案应该是right - 1  sorry 这里只考虑了左边界 没考虑右边界
        // 这道题真的好绕 as close as possible 就是要目标数两边要比较
        int lastTarget = target - prefixSum[right - 1];
        int lastNum = length - right;
        res = lastTarget / lastNum;
        int v1 = Math.abs(res * lastNum - lastTarget);
        int v2 = Math.abs((res + 1) * (lastNum) - lastTarget);
        return v1 > v2 ? res + 1 : res;
    }

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
