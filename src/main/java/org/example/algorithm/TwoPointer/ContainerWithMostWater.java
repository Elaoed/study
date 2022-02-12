package org.example.algorithm.TwoPointer;

/**
 * 11. Container With Most Water
 * 作为一个长方体，长乘以高是解题的唯一思路
 * 最直白的方案当然就是两层循环，取最大的那个 达咩
 * 现在脑子里就多了点东西不是，滑动窗口，双指针，跟前面的双指针不一样的是，前面有给定的数值，这里只要求最大
 * 还是我naive了，虽然没做过的时候一定会这么做
 * 不难的，但是怎么就这么粗心？？ 还是要解题 理解题目意思
 * ===============
 * 果然是做着做着对题目的理解越加清晰了，之前一直在考虑，如果只是从两端，前一个高度是2 后一个高度是1 再后一个高度是10 不是会错过吗
 * of course not, 实际上他求得是容积，以最小的那一边作为高
 * 2 1 10 1 8
 * 3 * 2 = 6
 * 2 * 1 = 2
 * 1 * 8 = 8
 * 这种情况呢？
 * 这种情况下就是右边指针左移而不是左边指针右移了
 *
 */

public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            // 最重点是我之前没理解这个if语句, 这里不比较的是面积，纯粹比较的是两边的高度
            // 那当然要移动矮的那一边？ 这样才有希望
            // 通过这个机制保证了两个指针一定会有一个在移动
            // 另外一个就呆在他认为最长(跟第一个相比)的地方不动了 ::) TODO: 这才是重点
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {

        int[] height = new int[]{2, 1, 10, 1, 9};
        System.out.println("最大的是:" + maxArea(height));

    }

}
