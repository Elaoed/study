package org.example.algorithm.Sort;

import org.example.algorithm.RandomArrayHelper;

/**
 * 归并也是divide and conquer
 * 他和快排的区别在于，归并是把左右列表都排序然后merge起来
 * 快排是找到一个中心点，让左边的都小于他，右边的都大于他
 */
public class MergeSort {

    /**
     * array1, array2 分别是左右数组 已经排序好了的
     */
    public static void merge(int[] array1, int[] array2) {

    }

    public static void mergeSort(int[] arr, int left, int right) {

    }


    public static void main(String[] args) {
        int n = 10;
        int[] ints = RandomArrayHelper.generateArray(n, 0, 10);
        mergeSort(ints, 0, ints.length - 1);

    }
}
