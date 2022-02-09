package org.example.algorithm.BinarySearch;

/**
 * 1095. Find in Mountain Array
 * <p>
 * (This problem is an interactive problem.)
 * <p>
 * You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
 * <p>
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 * <p>
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * <p>
 * 这道题有点上一题的进阶版
 * 上一题是 / / / 这一题是/\/\/\
 * Mountain 是只会有一个山峰把
 * 一样的getPeak
 * 然后判断一些极端条件
 * 根据左右两边进行二分
 * // 在最后选择左右区间进行扫描花了很久 后面可以稍微优化下 根据条件就是左边能找到就用左边的，找不到再找右边的
 */
public class FindInMountainArray {

    public static class MountainArray {

        private int[] array;

        public MountainArray(int[] array) {
            this.array = array;
        }

        public int get(int index) {
            return array[index];
        }

        public int length() {
            return array.length;
        }
    }

    // 1, 5, 2  peak = 1, target = 1
    public static int getPeak(MountainArray mountainArray) {
        int left = 0;
        int mountainLength = mountainArray.length();
        int right = mountainLength - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (middle > 0 && middle < mountainLength && mountainArray.get(middle) > mountainArray.get(middle - 1) && mountainArray.get(middle) > mountainArray.get(middle + 1)) {
                return middle;
            }
            if (mountainArray.get(middle - 1) < mountainArray.get(middle)) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return -1;
    }

    public static int binarySearch(MountainArray mountainArray, int left, int right, int target, boolean ascend) {

        while (left <= right) {
            int middle = (left + right) / 2;
            if (mountainArray.get(middle) == target) {
                return middle;
            } else {
                // 山的左边，上升
                if (ascend) {
                   if (mountainArray.get(middle) > target) {
                       // 走左边
                       right = middle - 1;
                   } else {
                       left = middle + 1;
                   }
                   // 山的右边, 下降
                } else {
                    if (mountainArray.get(middle) > target) {
                        // 走右边
                        left = middle + 1;
                    } else {
                        right = middle - 1;
                    }
                }
            }
        }
        return -1;
    }

    public static int findInMountainArray(int target, MountainArray mountainArr) {
        if (mountainArr.length() < 3) {
            return -1;
        }

        // 二分法找到中间的顶端，然后二分法分别找两条坡
        int peak = getPeak(mountainArr);
        if (peak == -1) {
            return -1;
        }

        if ((target < mountainArr.get(0) && target < mountainArr.get(mountainArr.length() - 1)) || target > mountainArr.get(peak)) {
            return -1;
        }

        int res = -1;
        // 左右都是闭区间
        if (target >= mountainArr.get(0)) {
            // 从左边找
            res =  binarySearch(mountainArr, 0, peak, target, true);
        }
        if (res > -1) {
            return res;
        }
        // 左边不行了再从右边找
        return binarySearch(mountainArr, peak, mountainArr.length() - 1, target, false);

    }

    public static void main(String[] args) {

        MountainArray mountainArray = new MountainArray(new int[]{1, 2, 3, 4, 5, 3, 1});
//        MountainArray mountainArray = new MountainArray(new int[]{1, 5, 2});
        System.out.println(findInMountainArray(3, mountainArray));

    }
}
