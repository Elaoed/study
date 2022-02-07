package org.example.algorithm.baseDatastructure;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 378. Kth Smallest Element in a Sorted MatriGiven an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * You must find a solution with a memory complexity better than O(n2).
 * // 转换成一维数组然后排序 然后取N就行
 * // 归并排序
 * 我还是用到了 行列都为有序数组的条件 棒棒哒
 */
public class KthSmallestElementInASortedMatrix {

    // 1. 先把k个数塞进去，记下坐标 从下一列找比最后一个数小的数，再下一列，直到列首的数都比他大
    // 这个解法有缺陷, md 如果前k + 1个数能排进前k个数，他永远都没法获得机会
    // 如果是O2的话比较简单 全部塞进去 小顶堆 取第八个，或者在插入的时候就判断要不要拿出一个
    // 然后一直是我搞搞错了 优先队列，如果是大顶堆的话 poll是删最大的，小顶堆是删最小的
    // kth smallest 就一定是小顶堆, 一般都是小顶堆，当然也可以是大顶堆，无非要控制一下数量
    public static int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        // 第一步就把我难倒了
        int n = k;
        int[] pos = new int[]{0, 0};
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, Comparator.reverseOrder()); // 默认大顶堆
        for (int row = 0; row < matrix.length; row++) {
            if (n > 0) {
//                int x = Math.min(n, matrix[0].length);
                for (int column = 0; column < matrix[0].length; column++) {
                    if (heap.size() < k) {
                        heap.add(matrix[row][column]);
                        pos = new int[]{row, column};
                    } else if (heap.peek() > matrix[row][column]) {
                        heap.poll();
                        heap.add(matrix[row][column]);
                        pos = new int[]{row, column};
                    }
                }
                n -= matrix[0].length;
            }
        }


        for (int row = pos[0] + 1; row < matrix.length; row++) {
            if (matrix[row][0] >= heap.peek()) {
                break;
            }
            for (int column = 0; column < matrix[0].length; column++) {
                if (matrix[row][column] >= heap.peek()) {
                    break;
                }
                // 需要第八个元素，优先队列是要大顶堆，如果插入之前已经满了，要和最大的比较，如果比最大要小，则入堆
                // 简单点，先全部入堆再说
                heap.poll();
                heap.add(matrix[row][column]);
            }
        }

        return heap.poll();

    }

    public static void main(String[] args) {
//        final int[][] ints = new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
//        final int[][] ints = new int[][]{{1, 2}, {1, 3}};
        final int[][] ints = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};

        int k = 5;
//        int k = 4;
        System.out.println(kthSmallest(ints, k));

//        final PriorityQueue<Integer> integers = new PriorityQueue<>();
//        integers.add(1);
//        integers.add(2);
//        integers.poll();
//        // 默认是小顶堆，poll会拿出最小的
//        System.out.println(integers);

    }

}
