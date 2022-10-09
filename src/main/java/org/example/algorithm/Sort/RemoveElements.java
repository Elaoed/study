package org.example.algorithm.Sort;

import org.example.algorithm.ArrayHelper;

import java.util.Arrays;

/**
 * 27. Remove Element
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The relative order of the elements may be changed.
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * Return k after placing the final result in the first k slots of nums.
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 */
public class RemoveElements {

    // 以这题为例 原先我想的也是双指针，左右两边的指针慢慢靠拢，双指针还是你双指针，只不过可以通过一个shift值来进行偏移
    // 右边的值要移过来多少位，剩下的概不关心
    public static int removeElement(int[] nums, int val) {

//        int shift = 0;
//        for (int i = 0; i < nums.length; i++) {
//            nums[i - shift] = nums[i];
//            // 如果出现了这种情况，就要多移一位过来了，这种情况适合概不关心的情况
//            // 抓住核心
//            if (nums[i] == val) {
//                shift += 1;
//            }
//        }
//
//        return nums.length - shift;

        // 讲道理也是可以的但是好像
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // index0, 永远指向val列表的下一个对象
        // 并且这道题是要把val都丢到队尾，所以index0永远指向val列表的上一个对象
        // 如果是从队尾过来的就不需要i++，因为当前被缓过来的值还没读过
        // 不行这里还需要保证顺序，用这种方法是不能保证顺序的
        int index0 = nums.length - 1;
        int i = 0;
        while (i <= index0) {
            if (nums[i] == val) {
                ArrayHelper.swap(nums, i, index0);
                index0 --;
            } else {
                i++;
            }
        }
        return index0 + 1;
    }

    public static void main(String[] args) {
//        int[] nums = {4, 5};
//        int val = 4;
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        int i = removeElement(nums, val);
        System.out.println(Arrays.toString(nums));
        System.out.println(i);
    }
}
