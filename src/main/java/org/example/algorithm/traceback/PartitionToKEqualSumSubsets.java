package org.example.algorithm.traceback;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {

    // 这是从桶的角度，去遍历每个球要不要放进来最终组成target 递归进去的是第几个桶吧 所以需要一个used判断这个球到底放过了没有

    /**
     * 每个桶的for循环，里面判断每个球有没有被用过能不能被加到这个桶里面 while + for
     * k号桶在考虑是不是把nums[start]这个球加进来
     */
    // k是第几个桶，bucket是当前桶的总和
    public static boolean tracebackTwo(int k, int bucket, int[] nums, int start, boolean[] used, int target) {
        if (k == 0) {
            return true;
        }
        if (bucket == target) {
            // 换下一个桶
            return tracebackTwo(k - 1, 0, nums, 0, used, target);
        }
        // 为什么不从0开始？
        for (int i = start; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (nums[i] + bucket > target) {
                continue;
            }
            used[i] = true;
            if (tracebackTwo(k, bucket + nums[i], nums, i + 1, used, target)) {
                return true;
            }
            used[i] = false;

        }
        return false;

    }

    // 这是从球放到哪个桶的思维角度去考虑，一个球中遍历每个桶
    // 这里target不能 == 0 因为要判断所有的bucket和等不等于
    public static boolean traceback(int[] nums, int start, int[] bucket, int target) {
        // 判断数组有没有用完
        if (start == nums.length) {
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != target) {
                    return false;
                }
            }
            return true;
        }

        // 穷举每个桶
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] + nums[start] > target) {
                continue;
            }
            bucket[i] += nums[start];
            if (traceback(nums, start + 1, bucket, target)) {
                return true;
            }

            bucket[i] -= nums[start];
        }
        // 装哪个都不行
        return false;
    }

    // 元素可重不可复选 target = sum() / k; 组合 / 前后不可一样？
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || k == 0 || nums.length == 0) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        for (int num : nums) {
            if (num > target) {
                return false;
            }
        }
        Arrays.sort(nums);
        // 这个倒序的方式妙啊
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
//        return traceback(nums, 0, new int[k], target);
        return tracebackTwo(k, 0, nums, 0, new boolean[nums.length], target);
    }

    public static void main(String[] args) {

        boolean b = canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4);
        System.out.println(b);

    }
}
