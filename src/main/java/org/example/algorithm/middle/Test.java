package org.example.algorithm.middle;

import java.util.Arrays;

/**
 * 还是想错方向了，尽管花了这么久，每次思维变更之后，还是要好好梳理一下条件的变化，省的来回调试
 * 21，22，23，24 如果是这种情况，简单的从后面撞就不行了，根据奇偶数会一会儿特别大一会儿特别小
 * 这个就看经验了，从小撞到大，从大撞到小(两种情况，撞完之后新数也纳入计算最大最小/还是不纳入)，最大最小撞 -- 这种就是普通迭代思维，如果是动态规划就是递归思维
 * 但是一般递归会简单一点
 */
public class Test {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 4, 1, 8, 1};
        Arrays.sort(nums);
//        int last = nums[nums.length - 1];
//        for (int i = nums.length - 2; i >= 0; i--) {
//            last = Math.abs(last - nums[i]);
//        }
//        System.out.println(last);
        boolean flag = true;
        int last;
        while (flag) {
            for (int i = 0; i < nums.length / 2; i++) {
                last = nums[i] - nums[nums.length - i];
                if (last > 0) {
                    nums[i] = last;
                    nums[nums.length - 1] = 0;
                } else if (last < 0) {
                    nums[nums.length - 1] = last;
                    nums[0] = 0;
                } else {
                    nums[i] = 0;
                    nums[nums.length - 1] = 0;
                }
            }
        }

    }

}
