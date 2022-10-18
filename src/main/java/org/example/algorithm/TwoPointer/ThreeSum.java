package org.example.algorithm.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 15. 3Sum
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * 按照2Sum的思路 用O(n^2) 的复杂度可以用map和二分法，还有双指针, 思路么错
 * XSum都可以用双指针汇合的方式做
 * ==========================================
 * 双指针法，求和和比大小，required: 数组必须有序
 * 之前两数之和的时候也想到过这个解法，但是因为不是有序的 所以放弃了 可以先排序呀宝，除非题目里面要求了时间的 不然都可以排序的
 * 结果最致命的是看错题目了
 * <p>
 * 大框架 是三重循环
 * 但是第二层和第三层其实是平行的，所以出现了双指针固定
 * 重要的是把题目按照题意和模型解开，我想应该不会出现按照我上面的想法分x种的类型把....
 * 中间不可重复这点卡了n久 害, 等下次再回来看看吧
 * 看最终的答案真的很简洁明了
 */

public class ThreeSum {

    public static void main(String[] args) {

//        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {1, -1, -1, 0};
//        List<List<Integer>> result = version3(nums);
//        List<List<Integer>> result = version1(nums);

//        System.out.println(result);
        System.out.println(Integer.MIN_VALUE + 1);
    }

    public static List<List<Integer>> version3(int[] nums) {
        // 需要数组去重 数组去重要怎么做？
        // 因为返回的不是index 所以可以直接进行排序
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        List<List<Integer>> res = new ArrayList<>();
        for (int first = 0; first < nums.length - 2; first++) {
            // 不仅要对z进行去重，x，y也需要
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int target = -nums[first];
            int left = first + 1, right = nums.length - 1;
            while (left < right) {
                if (left > first + 1 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }
                if (right < nums.length - 1 && nums[right] == nums[right + 1]) {
                    right--;
                    continue;
                }
                System.out.println("left: " + left + ", right: " + right + ", target: " + target);
                if (nums[left] + nums[right] == target) {
                    System.out.println("left: " + left + ", right: " + right + ", target: " + target);
                    res.add(Arrays.asList(nums[first], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (nums[left] + nums[right] > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return res;
    }


    public static List<List<Integer>> version2(int[] nums) {

        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        // 排序 quickSort
        // 双指针固定法, 假设nums有序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int first = 0; first < nums.length - 2; first++) { // 不是length - 3吗 为什么是-2, < -2 就是first最大-3,-2,-1刚好
            // TODO: 如果题解里面有不能重复的，一定要当成一个todoList
            if (first > 0 && (nums[first] == nums[first - 1])) { // 重复的不管 在这里做
                continue;
            }
            int second = first + 1;
            int third = nums.length - 1;
            // 根本性的错误，在于用例想的实在是太少了
            while (second != third) {
                // 卡了两个小时，校验要从初始化的第二个数开始校验，second最难的是在变化 = first + 1
                // 这里不能重复对于三个条件都要校验的 校验必须满足 first > 0, second > first + 1, third < nums.length - 1
                // 还是应该说 先走过一遍之后，新的和老的比，而不应该把现在的未来的比
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    second++;
                    continue;
                }
                if (third < nums.length - 1 && nums[third] == nums[third + 1]) {
                    third--;
                    continue;
                }

                int sum = nums[second] + nums[third] + nums[first];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[first], nums[second], nums[third]));
                    second++;
                    // 双指针汇合都是这样的
                } else if (sum < 0) {
                    second++;
                } else {
                    third--;
                }
            }
        }

        return result;
    }



}
