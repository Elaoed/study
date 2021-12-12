package org.example.algorithm.easy;

/**
 * 说是这类题目有两种解法，自上而下的斐波那契数列解法，和自下而上的动态规划解法
 * dynamic planning / dynamic programming
 * 两者优化上最重要的点就是，不要做重复的子运算，把结果在数组里面存起来，而且n是给定的，很适合用数组
 * 数组如果没有默认的话初始化都是0
 * 粗心X + 1 循环的变量用错了!!!!!
 * 爬楼梯是最最最简单的动态规划题
 * and 斐波那契和动态规划搞这么麻烦，这个概念还是我在罗马尼亚克鲁日博雅一babishi大学上大一的课的时候学到的概念,以及举一反三
 * 教育啊
 */
public class ClimbingStairs {

    public static void main(String[] args) {

        int n = 45; // 1836311903
        int[] his = new int[n + 1];
        his[0] = 1;
        his[1] = 1;
        // step is 1 or 2
//        int ways = version1(n, his);
        for (int i = 0; i < n + 1; i++) {
            if (his[i] > 0) {
                continue;
            }
            his[i] = his[i - 1] + his[i - 2];
        }
        System.out.println("ways: " + his[n]);

    }

    /**
     * 斐波那契
     * 自上而下，每一个指定的n台阶都有 n-2的台阶+2和n-1的台阶+1两种方式
     *
     * @param n
     */
    public static int version1(int n, int[] his) {
        if (his[n] > 0) {
            return his[n];
        }
        int value = version1(n - 1, his) + version1(n - 2, his);
        his[n] = value;
        return value;
    }

}
