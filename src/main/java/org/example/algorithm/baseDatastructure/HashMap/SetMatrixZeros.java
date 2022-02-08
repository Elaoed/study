package org.example.algorithm.baseDatastructure.HashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 73. Set Matrix Zeroes
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.
 * You must do it in place.
 * 这种都有坐标x, y的 然后有四个方向 {0 1, 0 -1, 1 0, -1 0} 这玩意儿的空间复杂度不是常量 但是也是可以解的
 * <p>
 * 他们的思维怎么跟我都不一样，直接一步到位，行0和列0来记录中间有0的位置的影响范围
 * 但是首行首列的信息会丢失 所以需要额外的标识，我想到用一个 为什么他们都是用两个呢
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

        // 坐标轴的x, y
        // 二维数组的 row, column
        // column才是x, row才是y, row对应的长度是matrix[row].length, column对应的长度是matrix.length;
        // len(row) = matrix.length = y轴的取值范围
        // len(column) = matrix[0].length = x轴的取值范围
        // x, y 对应 column, row
        boolean x0 = false, y0 = false;
        // 终于发现了第二个flag的作用，终于终于发现了第二个flag的用处 ::)
        // 原来两个flag是专门用来记录是row的0还是column的0
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                // matrix[row][column] = matrix[column][row]
                if (matrix[row][column] == 0) {
                    if (row == 0) {
                        x0 = true;
                    } else {
                        matrix[row][0] = 0;
                    }
                    if (column == 0) {
                        y0 = true;
                    } else {
                        matrix[0][column] = 0;
                    }
                    // 所以这个会把坐标轴的左边和底部标记0，但是不会去更新00那个位置
                }
            }
        }

        // 所以00和原来是一样的
        for (int row = 1; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {  // 原来是我自己的问题，寻址的方式错了，用row,column和用x,y的寻址方式是不一样的
                for (int column = 0; column < matrix[0].length; column++) {
                    matrix[row][column] = 0;
                }
            }
        }

        for (int column = 1; column < matrix[0].length; column++) {
            if (matrix[0][column] == 0) {  // 原来是我自己的问题，寻址的方式错了，用row,column和用x,y的寻址方式是不一样的
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][column] = 0;
                }
            }
        }

        if (x0) {
            Arrays.fill(matrix[0], 0);
        }

        if (y0) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][0] = 0;
            }
        }

    }

    public static void setZeroes(int[][] matrix) {
        // 先把0的坐标提出来, 然后for处理他的上下左右, 另外一种是类似深度优先广度优先的
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        List<int[]> coordinateList = new ArrayList<>();
        // 这个row column是从00 开始的，可以想象成为这个列表是往上列的，这样就和我们的坐标轴会概念比较像
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column] == 0) {
                    // 存下来
                    coordinateList.add(new int[]{row, column});
                }
            }
        }
        // for list for 上下左右 while 上到顶把所有的都变成0
        for (int[] coo : coordinateList) {
            int column = 0;
            int row = 0;
            while (column < matrix[0].length) {
                matrix[coo[0]][column] = 0;
                column++;
            }
            while (row < matrix.length) {
                matrix[row][coo[1]] = 0;
                row++;
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

}
