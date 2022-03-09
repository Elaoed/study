package org.example.algorithm.TwoPointer;

/**
 * 1004. Max Consecutive Ones III
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's
 * in the array if you can flip at most k 0's.
 * 通常题目的含义可以通过更程序化的语言再描述一遍，两者必须要等价
 * can be translated as: 在给定的字符串中寻找一个包含两个0的最长子区间
 */
public class MaxConsecutiveOnesIII {

    public static int longestOnes(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0, longest = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                k--;
            }
            // while也带上了if
            while (k < 0) {
                if (nums[left] == 0) {
                    k++;
                }
                left++;
            }
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }
}
