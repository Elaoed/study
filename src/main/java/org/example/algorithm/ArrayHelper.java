package org.example.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ArrayHelper {

    /**
     * generate n numbers array from min to max
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

}
