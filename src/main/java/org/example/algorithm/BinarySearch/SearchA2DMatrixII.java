package org.example.algorithm.BinarySearch;

/**
 * 240. Search a 2D Matrix II
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * 这题和I的区别在于，I可以转换成一个一维数组，II不行
 * 能想到两个方案，1. 还未能证明的二分法
 * 2. 二分归并 O(2nlogm + log(m*n)) m行n列
 * 这题只能在具体一行中二分，不能在选行的时候二分，先要锁定具体哪一行，需要通过一行的首尾比较 选出那几行 然后挨个进行二分
 */
public class SearchA2DMatrixII {

    // matrix = 4 * 5
    public static boolean searchMatrix(int[][] matrix, int target) {
        // 先二分左边，再二分右边
        int left = 0, right = matrix.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (matrix[middle][0] == target) {
                return true;
            }
            // 只要移动一边，不是的，二分本来就是有序的和现在这个一样，之所以要移动两边是因为要获得更精准的middle
            if (matrix[middle][0] > target) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        // 因为end总会比那个数大，而我们要找的可能在上一行, 那如果只有一行呢, left = 0, right = 1, middle = 0 没毛病
        int end = right; // 这里也不用 - 1, right可能是个不可达的位置
        left = 0;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (target == matrix[middle][matrix[0].length - 1]) {
                return true;
            }
            // 一样的，标准模式就可以了 因为上面已经判过==了，这里不是<就是>
            if (target < matrix[middle][matrix[0].length - 1]) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        // 这是血统最正规的答案了
        int start = left;
        for (int i = start; i < end; i++) {
            // 这里也可以改成二分
            if (target > matrix[i][matrix[0].length - 1])  {
                continue;
            }
            left = 0;
            right = matrix[0].length - 1;
            while (left < right) {
                int middle = left + (right - left) / 2;
                if (target == matrix[i][middle]) {
                    return true;
                }
                if (target < matrix[i][middle]) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            }
            if (target == matrix[i][left]) {
                return true;
            }
        }

        return false;

    }

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}}, 20));
    }

}
