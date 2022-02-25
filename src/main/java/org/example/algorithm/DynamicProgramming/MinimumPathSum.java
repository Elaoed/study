package org.example.algorithm.DynamicProgramming;

import java.util.Arrays;

/**
 * 64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 * 重叠子问题 是不是感觉就来了
 */
public class MinimumPathSum {

    public static void helper(int x, int y, int[][] grid, int[][] res, int lastSum) {

        if (x >= grid.length || y >= grid[0].length) {
            return;
        }

        res[x][y] = Math.min(res[x][y], lastSum + grid[x][y]);
        helper(x + 1, y, grid, res, res[x][y]);
        helper(x, y + 1, grid, res, res[x][y]);

    }

    /**
     * 这是一种方案 但是超出时间限制了
     * 如果面试官让我想的话 我肯定会想那就要用迭代 可是迭代怎么写 必须要嵌套for到达每个节点
     * 但是我明明有两步 for里面只能走一步
     */
    public static int iterationVersion(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // 在矩阵的每个节点都记录minimun path sum
        int[][] res = new int[grid.length][grid[0].length];
        for (int[] re : res) {
            Arrays.fill(re, Integer.MAX_VALUE);
        }
        res[0][0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            res[0][i] = res[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            res[i][0] = res[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int i1 = 1; i1 < grid[0].length; i1++) {
                res[i][i1] = Math.min(res[i - 1][i1], res[i][i1 - 1]) + grid[i][i1];
            }
        }

        return res[grid.length - 1][grid[0].length - 1];
    }

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // 在矩阵的每个节点都记录minimun path sum
        int[][] res = new int[grid.length][grid[0].length];
        for (int[] re : res) {
            Arrays.fill(re, Integer.MAX_VALUE);
        }

        res[0][0] = grid[0][0];
        helper(0, 0, grid, res, grid[0][0]);
        return res[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        System.out.println(iterationVersion(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }
}

