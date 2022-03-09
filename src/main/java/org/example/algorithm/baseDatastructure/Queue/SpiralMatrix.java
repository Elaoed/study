package org.example.algorithm.baseDatastructure.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * // 另外还有的方案就是削头，只是用java不会写，先去掉第一层，把剩下的长方形旋转90度，继续去
 * // 第三种方案是削层，
 */
public class SpiralMatrix {

    public static List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int columnNum = matrix[0].length, rowNum = matrix.length;
        int left = 0, right = columnNum - 1, top = 0, bottom = rowNum - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                res.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                res.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column >= left; column--) {
                    res.add(matrix[bottom][column]);
                }
                for (int row = bottom - 1; row > top; row--) {
                    res.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int columnNum = matrix[0].length, rowNum = matrix.length;
        // 不是把原来的值变成0，而是另外开一个matrix写0， 1
        boolean[][] visited = new boolean[rowNum][columnNum];
        int total = rowNum * columnNum;
        // 用这个代替原先几个丑陋的for循环
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;

        // 为什么是0， 0？ 因为看似是左上角 但是实际上左上角是0,0
        int row = 0, column = 0;
        while (total-- > 0) {

            // 这么写就是不会碰到 那种闯到已经去过的地方
            res.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            // 这个if 条件尤其重要
            // 保证不越界
            if (nextRow < 0 || nextRow >= rowNum || nextColumn < 0 || nextColumn >= columnNum || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return res;

    }

    public static void main(String[] args) {
        final int[][] ints = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> integers = spiralOrder2(ints);
        System.out.println(integers);


    }
}
