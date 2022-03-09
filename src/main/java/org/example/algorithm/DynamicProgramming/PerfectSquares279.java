package org.example.algorithm.DynamicProgramming;

/**
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * A perfect square is an integer that is the square of an integer;
 * in other words, it is the product of some integer with itself.
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 * 比斐波那契数列/爬台阶的问题稍微进步那么一点点
 * 爬台阶的步长是给定的，所以用两行就搞定了，这里的步长是能预测的但是不知道会有多少，所以用了一个for循环
 * */
public class PerfectSquares279 {

    public static int numSquares(int n) {
        if (n == 0) {
            return 0;
        }
        // 最好下标即当前位置
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏情况 所有都是1
            for (int j = 1; i - j * j >= 0; j ++) {
                // 这里不会遇到一个问题吗 比如还剩3 但是perfect integer里面没有3了? ? 比如7
                // 这是从上而下的不会遇到这个问题 7 - 1 = [6] + 1, 7 - 4 = [3] + 1
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }

}
