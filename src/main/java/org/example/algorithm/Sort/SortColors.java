package org.example.algorithm.Sort;

import org.example.algorithm.ArrayHelper;

import java.util.Arrays;

/**
 * 75. Sort Colors
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function. 不让使用是因为可以使用
 * 如果只有0，1，2的话可以使用三路快排的思想 扫描一边把0放最前面 把2放最后面
 * 如果有更多路的话 就只能使用快排了
 * 这道题就和removeElements形成对比，一个是要关心，一个是不关心
 * 什么是循环不变量: 应该是要定义 每一个变量的停止循环的条件吧。之前我都只想用两个指针就可以了，现在发现哎可能需要一个指向当前的指针。
 */
public class SortColors {

    public static void sortColors(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int index0 = 0;
        int index2 = nums.length - 1;
        int i = index0;
        // i 决定的当前的值，如果当前的值不用看了就跑路++，如果还要看的话就不动，比如跟index2的内容做交换以后，不知道交换回来的值
        // index0永远指向0后面一代数组的头，index2永远指向2前面一代数组的尾，所以=2的时候还需要判断
        // 出现三个区间，初始化的时候一定要保证三个区间是空区间
        // what if 0212202
        while (i <= index2) {
            if (nums[i] == 0) {
                ArrayHelper.swap(nums, i, index0);
                index0++;
                i++; // 因为交换过来的数前面早就读过了所以不可能是2，也不可能是0
            } else if (nums[i] == 1) {
                // 无需做任何交换
                i++;
            } else {
                ArrayHelper.swap(nums, i, index2);
                index2--;
                // 通过i不加的方式来控制 模拟我上面的while循环
            }

        }

    }

    // 妙啊 但是我想不出来
    public void miao(int[] nums) {
        int num0 = 0, num1 = 0, num2 = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                nums[num2++] = 2;
                nums[num1++] = 1;
                nums[num0++] = 0;
            }else if(nums[i] == 1) {
                nums[num2++] = 2;
                nums[num1++] = 1;
            }else {
                nums[num2++] = 2;
            }
        }
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

}
