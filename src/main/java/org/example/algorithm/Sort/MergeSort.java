package org.example.algorithm.Sort;

import org.example.algorithm.RandomArrayHelper;

import java.util.Arrays;

/**
 * 归并也是divide and conquer
 * 他和快排的区别在于，归并是把左右列表都排序然后merge起来
 * 快排是找到一个中心点，让左边的都小于他，右边的都大于他
 * 空间复杂度为O(n)
 * 时间复杂度为O(nlogn)
 * 需要有一个辅助的数组 根据下标取这里的数组才行
 */
public class MergeSort {

    /**
     * array1, array2 分别是左右数组 已经排序好了的
     */
    private static void merge(int[] arr, int left, int mid, int right) {

        // 搞一个空数组，两个指针分别开始移动 i, j 分别代表左数组和右数组
        // 左开右闭
        int[] aux = Arrays.copyOfRange(arr, left, right + 1);
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                // i超过了中线 说明左边的都比右边的最小还小
                arr[k] = aux[j - left];
                j++;
            } else if (j > right) {
                // j 怎么可能过右边的线呢？傻逼这不是和上面一个意思吗
                arr[k] = aux[i - left];
                i++;
            } else if (aux[i - left] <= aux[j - left]) {
                arr[k] = aux[i - left];
                i++;
            } else {
                arr[k] = aux[j - left];
                j++;
            }
        }

    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);

    }

    public static void main(String[] args) {
        int n = 10;
        int[] ints = RandomArrayHelper.generateArray(n, 0, 10);
        mergeSort(ints, 0, ints.length - 1);

        System.out.println("result: " + Arrays.toString(ints));
    }
}
