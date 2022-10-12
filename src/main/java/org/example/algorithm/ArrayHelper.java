package org.example.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ArrayHelper {

    /**
     * generate n numbers array from min to max
     *
     * @param n
     * @param min
     * @param max
     * @return
     */
    public static int[] generateArray(int n, int min, int max) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int ii = (int) (Math.random() * (max - min + 1) + min);
            arr[i] = ii;
        }

        System.out.println("随机数组: " + Arrays.toString(arr));
        return arr;
    }

    public static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static int[][] rotate90InAnticlockwise(int[][] matrix) {
        // 00 01 10 11 20 21 -> 00, 01, 02, 10, 11, 12
        // 00 -> 10, 01 -> 00, 10 -> 11, -> 11 -> 01, 20 -> 12, 21 -> 02;
        // 这个规律我自己还真找不出 看了题解结合自己的实验发现在逆时针旋转90°的情况下 [i, j] -> [columnLength - 1 - j, i];
        // 顺时针就不一样了
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int rowLength = matrix.length; // 3
        int columnLength = matrix[0].length; // 2
        int[][] res = new int[columnLength][rowLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                res[columnLength - 1 - j][i] = matrix[i][j];
            }
        }
        return res;

    }

    public static void main(String[] args) {
        int[][] input = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        System.out.println(Arrays.deepToString(rotate90InAnticlockwise(input)));
        ;
        int[] input2 = {1, 2, 3};
        boolean[][] visited = new boolean[2][2];
        System.out.println(Arrays.toString(input2));
        System.out.println(Arrays.deepToString(visited));
        // Arrays.toString, Arrays.deepToString
    }


}
