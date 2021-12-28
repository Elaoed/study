package org.example.algorithm.middle;

import java.util.Arrays;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 *
 */
public class UniquePaths {


    public static void version1(int m, int n) {
        int[][] nm = new int[n][m];
        Arrays.fill(nm, -1);
        int[] robot = new int[]{0, m - 1};
        int[] target = new int[]{n - 1, 0};



    }

    public static void main(String[] args) {
    }
}
