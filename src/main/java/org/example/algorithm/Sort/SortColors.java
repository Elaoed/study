package org.example.algorithm.Sort;

import java.util.Arrays;

/**
 * 75. Sort Colors
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 * 如果只有0，1，2的话可以使用三路快排的思想 扫描一边把0放最前面 把2放最后面
 * 如果有更多路的话 就只能使用快排了
 */
public class SortColors {

    public static void sortColors(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int index0 = 0;
        int index2 = nums.length - 1;
        while (index0 < nums.length) {
            if (nums[index0] != 0) {
                break;
            }
            index0++;
        }

        while (index2 >= 0) {
            if (nums[index2] != 2) {
                break;
            }
            index2--;
        }

        int tmp;
        for (int i = index0; i <= index2 ; i++) {
            while (nums[i] != 1) {
                if (nums[i] == 0) {
                    tmp = nums[index0];
                    nums[index0] = nums[i];
                    nums[i] = tmp;
                    index0++;
                }
                if (nums[i] == 2) {
                    tmp = nums[index2];
                    nums[index2] = nums[i];
                    nums[i] = tmp;
                    index2--;
                }
            }
        }
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        int[] nums = new int[]{2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

}
