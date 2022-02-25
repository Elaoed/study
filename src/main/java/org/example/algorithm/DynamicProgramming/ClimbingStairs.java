package org.example.algorithm.DynamicProgramming;

/**
 * 说是这类题目有两种解法，自上而下的斐波那契数列解法，和自下而上的动态规划解法
 * dynamic programming
 * 两者优化上最重要的点就是，不要做重复的子运算，把结果在数组里面存起来，而且n是给定的，很适合用数组
 * 数组如果没有默认的话初始化都是0
 * 粗心X + 1 循环的变量用错了!!!!!
 * 爬楼梯是最最最简单的动态规划题
 * and 斐波那契和动态规划搞这么麻烦，这个概念还是我在罗马尼亚克鲁日博雅一babishi大学上大一的课的时候学到的概念,以及举一反三
 * 教育啊
 *
 * 以后就慢慢的开始讲dp
 * 1. 有很多的重叠子问题 // 所以可以用斐波那契的递归走
 * 2. 计算结果被保存，不会在同样的问题上花费时间
 * 3. 本质和其他遍历算法一样都是把问题拆成多个子问题求解，本质区别是动态规划要找到状态转移方程
 * 4. 还可以通过空间压缩节省空间
 * 5. dp是自下而上的，适合求总值，带状态记录的优先搜索是自上而下的，适合求所有路径
 */
public class ClimbingStairs {

    public static int dpVersion(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[i] = 1;
            } else if (i == 2) {
                dp[i] = 2;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {

        int n = 45; // 1836311903
        System.out.println(dpVersion(n));

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
