package org.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 * 和2Sum有点不太一样，2Sum是通过余数查Map的方式
 * 解题思路:  0 0 0 (out) -1, 0, 1
 * 先找0 然后找对数 用map
 * n个0就有n个组合, 还有3个0
 * 其次是1负数2正数
 * 再次是2负数1正数
 * 就简化成为2Sum了
 * 2 -->
 * 双指针法，求和和比大小，required: 数组必须有序
 * 之前两数之和的时候也想到过这个解法，但是因为不是有序的 所以放弃了
 * 结果最致命的是看错题目了
 */

public class ThreeSum {

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {1, 1, -2};
//        List<List<Integer>> result = version2(nums);
        List<List<Integer>> result = version1(nums);

        System.out.println(result);
    }

    public static List<List<Integer>> version1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int zeroNum = 0;
//        Map<Integer, Integer>
        HashSet<Integer> negativeSet = new HashSet<>();
        HashSet<Integer> positiveSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroNum += 1;
            } else if (nums[i] < 0) {
                // 这样是不行的老铁 可能要用那种支持duplicatedKey的Map了
                negativeSet.add(nums[i]);
            } else {
                positiveSet.add(nums[i]);
            }
        }

        if (zeroNum >= 3) {
            result.add(Arrays.asList(0, 0, 0));
        }

        if (negativeSet.size() == 0 || positiveSet.size() == 0) {
            return result;
        }

//        HashMap<Integer, Integer> tmp = new HashMap<>();
        for (Integer positive : positiveSet) {
            for (Integer negative : negativeSet) {
                int reminder = -positive - negative;
                if (positiveSet.contains(reminder) || negativeSet.contains(reminder)) {
                    result.add(Arrays.asList(positive, negative, reminder));
                }
            }
        }

        return result;
    }

    public static List<List<Integer>> version2(int[] nums) {
        // 排序 quickSort
        // 双指针固定法, 假设nums有序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int base = 0; base < nums.length - 2; base++) {
            int start = base + 1;
            int end = nums.length - 1;
            // 根本性的错误，在于用例想的实在是太少了
            while ((start != end)) {
                int sum = nums[start] + nums[end] + nums[base];
                if (sum == 0) {
                    if (result.size() == 0) {
                        result.add(Arrays.asList(nums[base], nums[start], nums[end]));
                    } else {
                        boolean flag = false;
                        for (List<Integer> ints : result) {
                            if (ints.get(0) == nums[base] && ints.get(1) == nums[start] && ints.get(2) == nums[end]) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            result.add(Arrays.asList(nums[base], nums[start], nums[end]));
                        }
                    }
                    start++;
                } else if (sum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        return result;
    }
}
