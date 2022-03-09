package org.example.algorithm.BinarySearch;

/**
 * 278. First Bad Version
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * // 妈的 学到了 int middle = (left + right) / 2 可以换成 int middle = left + (right - left) / 2 用来防止溢出
 * 这题的条件就跟前面那几题middle的完全不同，还是要慎重思考，好好看题干
 */
public class FirstBadVersion {

    public static boolean isBadVersion(int index) {
        return false;
    }

    // We want to find the first bad version!!! 在写middle +/- 的逻辑的时候好好看看这个
    public static int findBadVersion(int n) {

        int left = 0, right = n;
        // 这里left == right 是没有意义的 因为当他两相遇的时候 就是最终解 不需要再判断额外的东西
        while (left < right) {
//            int middle = (left + right) / 2;
            int middle = left + (right - left) / 2;
            if (isBadVersion(middle)) {
                right = middle; // 当前middle可能是第一个badVersion
            } else {
                left = middle + 1; // 当前middle不可能是badVersion
            }
            // 这种时候应该是middle +/- 1的把
        }
        return left;
    }



    public static void main(String[] args) {

    }
}
