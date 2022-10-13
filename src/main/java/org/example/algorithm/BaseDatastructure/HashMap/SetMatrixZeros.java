package org.example.algorithm.BaseDatastructure.HashMap;

import java.util.Arrays;
import java.util.Stack;

/**
 * 73. Set Matrix Zeroes
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.
 * You must do it in place.
 * 这种都有坐标x, y的 然后有四个方向 {0 1, 0 -1, 1 0, -1 0} 这玩意儿的空间复杂度不是常量 但是也是可以解的
 */

public class SetMatrixZeros {

    /**
     * 他们的思维怎么跟我都不一样，直接一步到位，行0和列0来记录中间有0的位置的影响范围
     * 但是首行首列的信息会丢失 所以需要额外的标识，我想到用一个 为什么他们都是用两个呢
     *
     * @param matrix
     */
    public static void setZeroes2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }


        boolean x0 = false, y0 = false;
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;

        // 通过把矩阵中的坐标分解到第一行和第一列来事后还原整个矩阵，正常在第一行第一列的数据也没问题，因为无法分辨第一行第一列是否需要置0，通过两个变量x0, y0来记录第一行第一列上原来是否有0
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                y0 = true; // 代表第一列也要置0
                break;
            }
        }
        for (int j = 0; j < columnLength; j++) {
            if (matrix[0][j] == 0) {
                x0 = true; // 代表第一行也要置0
                break;
            }
        }
        for (int i = 1; i < rowLength; i++) {
            for (int j = 1; j < columnLength; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        System.out.println(Arrays.deepToString(matrix));


        for (int i = 1; i < rowLength; i++) {
            if (matrix[i][0] == 0) {
                Arrays.fill(matrix[i], 0);
            }
        }

        for (int j = 1; j < columnLength; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < rowLength; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(matrix));
        System.out.println("x0: " + x0 + ", y0: " + y0);

        if (x0) {
            Arrays.fill(matrix[0], 0);
        }

        if (y0) {
            for (int i = 0; i < rowLength; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < rowLength; i ++ ) {
            for (int j = 0; j < columnLength; j++) {
                if (matrix[i][j] == 0) {
                    System.out.println("x: " + i + ", y: " + j);
                    stack.push(new int[]{i, j});
                }
            }
        }

        while (!stack.isEmpty()) {
            int[] zeroPos = stack.pop();

            int x = zeroPos[0];
            int y = zeroPos[1];
            // System.out.println("x: " + x + ", y: " + y);
            Arrays.fill(matrix[x], 0);
            for (int k = 0; k < rowLength; k++) {
                matrix[k][y] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

}
