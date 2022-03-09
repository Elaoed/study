package org.example.algorithm.TwoPointer;

import java.util.Arrays;

/**
 * 283. Move Zeroes
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 * 像这种虽然简单，但是移动完位置之后要特别注意，不要直接p1++, 因为会出现两个0, 直接++相当于就跳过了 之前也好几次犯过这个错误
 * 这题应该不算是同向指针 不算slidewindow 而是指针汇合
 */
public class MoveZeros {

    public static void moveZeroes(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return;
        }

        int p1 = 0, p2 = nums.length - 1;
        while (p1 < p2) {
            if (nums[p1] == 0) {
                int cur = p1;
                while (cur < p2) {
                    // 考虑连续的两个怎么办
                    nums[cur] = nums[cur + 1];
                    cur++;
                    // p2 也要移的 因为最一开始p2就是呆在整个数组之外
                }
                nums[p2] = 0;
                p2--;
            }
            if (nums[p1] != 0) {
                p1++;
            }
        }

    }

    public static void main(String[] args) {
//        int[] nums = new int[]{0, 1, 0, 3, 12};
        int[] nums = new int[]{0, 0, 1};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

    }
}
