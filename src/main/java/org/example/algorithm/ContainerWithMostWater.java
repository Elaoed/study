package org.example.algorithm;

/**
 * 作为一个长方体，长乘以高是解题的唯一思路
 * 最直白的方案当然就是两层循环，取最大的那个 达咩
 * 现在脑子里就多了点东西不是，滑动窗口，双指针，跟前面的双指针不一样的是，前面有给定的数值，这里只要求最大
 * 还是我naive了，虽然没做过的时候一定会这么做
 * 不难的，但是怎么就这么粗心？？ 还是要解题 理解题目意思
 */

public class ContainerWithMostWater {

    public static void main(String[] args) {

        int[] height = new int[]{2, 3, 4, 5, 18, 17, 6};
        int p1 = 0;
        int p2 = height.length - 1;
        int max = 0;
        while (p1 != p2) {
            max = Math.max(max, (p2 - p1) * Math.min(height[p1], height[p2]));
            if (height[p1] < height[p2]) {
                p1++;
            } else {
                p2--;
            }
        }

        System.out.println("最大的是:" + max);

    }

}
