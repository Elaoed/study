package org.example.algorithm.BinarySearch;

import java.util.function.Predicate;

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
 * 上一题是 / / / 这一题是/\/\/\ Mountain 是只会有一个山峰把
 * 一样的getPeek
 * 在最后选择左右区间进行扫描花了很久 后面可以稍微优化下 根据条件就是左边能找到就用左边的，找不到再找右边的
 * 花花写的真他娘的优雅 偷了偷了
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

    // 1 2 3 2 1
    // 唯一的问题就是right能不能取到
    public static int binarySearch(int left, int right, Predicate<Integer> cond) {

        // left: 0, right: 4, mid: 2 cond: mid > mid + 1
        while (left < right) {
            int mid = (left + right) / 2;
            // 这里的right = mid right是要取到的
            if (cond.test(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 为什么返回left呢
        return right;
    }

    public static int findInMountainArray(int target, MountainArray mountainArr) {
        if (mountainArr.length() < 3) {
            return -1;
        }

        // 二分法找到中间的顶端，然后二分法分别找两条坡
        int peek = binarySearch(0, mountainArr.length() - 1, (mid) -> mountainArr.get(mid) > mountainArr.get(mid + 1));
        System.out.println("peek: " + peek);
        if (peek == -1) {
            return -1;
        }

        // 从左边找
        int res = binarySearch(0, peek, (mid) -> mountainArr.get(mid) >= target);;
        System.out.println("left list result: " + res);
        if (target == mountainArr.get(res)) {
            return res;
        }
        // 左边不行了再从右边找
        res = binarySearch(peek, mountainArr.length() - 1, (mid) -> mountainArr.get(mid) <= target);
        if (target == mountainArr.get(res)) {
            return res;
        }
        return -1;

    }

    public static void main(String[] args) {

        MountainArray mountainArray = new MountainArray(new int[]{1, 2, 3, 4, 5, 3, 1});
//        MountainArray mountainArray = new MountainArray(new int[]{1, 5, 2});
        System.out.println(findInMountainArray(6, mountainArray));

    }
}
