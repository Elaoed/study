package org.example.algorithm.BinarySearch;

/**
 * 69. Sqrt(x) Square root
 * Given a non-negative integer x,compute and return the square root of x.
 * Since the return typeis an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 * 思路很简单，如果是O(sqrt(x))的话直接for就可以了，更优解是二分, 题型已经给出答案了
 * 永远记得边界值，开始值的判空，结尾好像一般都没有，中间再套各种基本算法的组合
 * 但凡有平方的，要加个条件 target < middleValue || middleValue < 0
 * 用 target / middle == middle 的方式来 替代 middle * middle == target 防止溢出
 * 这种一定要注意 for i = 0, i <= target / 2;
 * // 保留X位的做法？
 * 二分的square 不在乎right = target + 1
 * 因为不会比到那里去的，反而会导致溢出 ::)
 *
 *
 * // 这道题独有的，i < 2 的时候返回i
 * 尽管如此 for i = 1 还是要从1开始算
 * 最后不管是迭代还是二分都要return i(left) + 1;
 *
 * 保留X位小数的秘诀是 一开始放大，后面再缩小，代码可以完成复用
 * 注意: 最后也是要对left - 1进行反位移
 *
 */
public class SqrtX {

    public static double move(int value, int decimal) {
        double res = value;
        while (decimal-- > 0) {
            res /= 10;
        }
        return res;
    }

    // 如果需要保留小数点后面两位呢
    public static double mySqrt3(int x, int decimal) {

        if (x < 2) {
            return x;
        }

        int curr = decimal;
        while (curr-- > 0) {
            x *= 100;
        }

        int left = 0, right = x;
        while (left < right) {
            int middle = left + (right - left) / 2;
            double res = (double) x / middle;
            if (middle == res) {
                // 也要进行位移
                return move(middle, decimal);
            }
            // 说明middle大了
            if (middle > res) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        // 在二分的条件上 判断一下当left == right的时候 值满不满足条件

        return move(left - 1, decimal);

    }

    public static int mySqrt2(int x) {
        if (x < 2) {
            return x;
        }

        int left = 0, right = x;
        while (left < right) {
            int middle = left + (right - left) / 2;
            int res = x / middle;
            if (middle == res) {
                return middle;
            }
            // 说明middle大了
            if (middle > res) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        // 在二分的条件上 判断一下当left == right的时候 值满不满足条件
        return left - 1;
        // 本质的区别是 之前的题只关心有没有，这个需要关心具体的位置，一般都是左边的位置所以要-1

    }
    // 就算是非二分法 返回也要指针 - 1
    public static int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int res = -1;
        for (int i = 2; i <= x / 2; i++) {
            if (x / i == i) {
                return i;
            }
            if (x / i < i) {
                return i - 1;
            }
            res = i;
        }

        return res;

    }

    public static void main(String[] args) {
//        System.out.println(mySqrt2(6));
//        System.out.println(mySqrt2(4));
//        System.out.println(mySqrt2(2));
//        System.out.println(mySqrt2(2147395599));
        System.out.println(mySqrt3(2, 2));
    }

}
