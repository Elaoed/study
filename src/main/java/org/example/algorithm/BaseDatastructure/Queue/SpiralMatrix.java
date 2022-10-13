package org.example.algorithm.BaseDatastructure.Queue;

import org.example.algorithm.ArrayHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * // 第三种方案是削层，
 * // 肤浅了啊
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

    public static List<Integer> otherVersion(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();

        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        // 接下来换大多思路的写法
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // 主要是如何判断到顶了，第一层当然好判断，因为有边界在，所以要削减边界吗？
        // 通过一个判断是否被访问过的二维数组就可以
        boolean[][] visited = new boolean[rowLength][columnLength];

        // 标记初始位置
        int row = 0;
        int column = 0;
        // 想来应该有一个for 一个while 但是哪个在外面呢
        // 停止while的条件需要商量 四个方向都走变了，newRowPos和newColumnPos = rowPos和columnPos?
        // 或者简单点，设定总步数 = rowLength * columnLength - 1;
        // 就要加这么多次
        int total = rowLength * columnLength;
        int dIndex = 0;
        int[] direction = directions[dIndex];
        while (total-- > 0) {
            res.add(matrix[row][column]);
            visited[row][column] = true;

            // 如果需要走下一步的条件判断 那还是用next的方式更优雅一些 试探一下，试探不成功就换方向
            int nextRow = row + direction[0];
            int nextColumn = column + direction[1];
            // 边界条件的设置 考虑大小边界
            if (nextRow < 0 || nextColumn < 0 || nextRow == rowLength || nextColumn == columnLength || visited[nextRow][nextColumn]) {
                direction = directions[++dIndex % 4];
            }
            row = row + direction[0];
            column = column + direction[1];

//            for (int i = 0; i < directions.length; i++) {
//                int[] direction = directions[i];
//                row = row + direction[0];
//                column = column + direction[1];
                // 怎么处理一条过去要加入一排的数据的问题
                // 这一整条的逻辑有点难梳理
                // 因为最外层有一个while在转嘞，就可以不需要里面的while，相比之下满足条件再改变direction就行，
                // 所以问题在于外面的for循环，有了这个for循环才被迫要里面的while
//                while (true) {
//                    res.add(matrix[row][column]);
//                    visited[row][column] = true;
//                    total--;
//                    int nextRow = row + direction[0];
//                    int nextColumn = column + direction[1];
//                    if (nextRow < rowLength && nextColumn < columnLength && visited[nextRow][nextColumn]) {
//                        row = nextRow;
//                        column = nextColumn;
//                    } else {
//                        break;
//                    }
//                }
                // 退回到上一步合法的节点 但是这里又有问题 合法节点应该往正确的方向进一步再add
                // 这里就有问题 默认是上面完成了加过了才来减，但是有没有回遇到没加过直接跳出循环开始减的呢。我总感觉减不是一个好方法
                // 换种思路，能+direct[0]就+direct[0]，不能加就，也不行啊 从下面回上来的时候应该还是走的上，不能走右
                // 所以为了避免下面这两行回退的代码，还是需要有nextRow, nextColumn
//                row = row - direction[0];
//                column = column - direction[1];
//            }
        }
        return res;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int columnLength = matrix[0].length, rowLength = matrix.length;
        // 不是把原来的值变成0，而是另外开一个matrix写0， 1
        boolean[][] visited = new boolean[rowLength][columnLength];
        int total = rowLength * columnLength;
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
            if (nextRow < 0 || nextRow >= rowLength || nextColumn < 0 || nextColumn >= columnLength || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return res;

    }

    /**
     * 另外还有的方案就是削头，只是用java不会写，先去掉第一层，把剩下的长方形旋转90度，继续去
     * @param matrix
     * @return
     */
    public static List<Integer> chipOffHeadLayerVersion(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        // 如何把数组旋转90°
        while (matrix != null && matrix.length > 0) {
            // 削掉第一层
            for (int i = 0; i < matrix[0].length; i++) {
                res.add(matrix[0][i]);
            }
            // 这里并没有削掉第一层
            matrix = chipOffFirstLayer(matrix);
            matrix = ArrayHelper.rotate90InAnticlockwise(matrix);
        }
        return res;
    }

    private static int[][] chipOffFirstLayer(int[][] matrix) {
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        int[][] res = new int[rowLength - 1][columnLength];
        for (int i = 1; i < rowLength; i++) {
            System.arraycopy(matrix[i], 0, res[i - 1], 0, columnLength);
        }
        return res;
    }


    public static void main(String[] args) {
        final int[][] ints = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> integers = otherVersion(ints);
        System.out.println(integers);


    }
}
