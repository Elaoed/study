package org.example.algorithm.DynamicProgramming;

/**
 * Given an m x n binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 * the more clever way is to 遍历找到1作为正方形的左上角，只考虑横竖两边到0为止取最先的边界，然后check这个边界里面的是不是都是但凡遇到一个0就cut边界, 然后进行对比 比我想的好多了 仍然很暴力
 * 扩展的时候可以通过判断右边一列和下边一行的交叉点是否为0快速判断是否要continue
 * dp考虑边界和状态转移方程
 */
public class MaximalSquare221 {


    //
    public static int dpVersion(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        // 想清楚这个dp里面放的什么很重要
        // 以dp(i, j)表示以i, j为右下角，且只包含1的正方形的边长最大值, 返回值为dp中最大的值
        // 暴力求解以左上角为锚点开始计算，dp则以右下角
        // 最重要的是找寻状态转移方程，即这个点的内容是由哪些相邻的内容决定的
        // 在这道题中 dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
        int maxSide = 0;
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                // 如果这是个0要怎么做，直接把当前的置位0
                if (matrix[x][y] == '0') {
                    dp[x][y] = 0;
                } else {
                    if (x - 1 >= 0 && y - 1 >= 0) {
                        dp[x][y] = Math.min(Math.min(dp[x - 1][y - 1], dp[x - 1][y]), dp[x][y - 1]) + 1;
                    } else {
                        dp[x][y] = 1;
                    }
                    if (dp[x][y] > maxSide) {
                        maxSide = dp[x][y];
                    }
                }
            }
        }
        return maxSide * maxSide;

    }

    // 我这不是dp是暴力解法把, dp的思路是感觉从一个点开始延伸，满足条件就再往外扩展这样, 不我连暴力都不如
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int biggestBorder = Math.min(m, n);
        // 边, 其实从最大开始慢慢变小更好，没事先解着再说
        int ret = 0;
        for (int border = 1; border < biggestBorder; border++) {
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (x + border < m && y + border < n) {
                        // 算出这个正方形里面 所有的1sum 和ret进行Math.max();
                    }
                }
            }

        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(dpVersion(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
    }

}
