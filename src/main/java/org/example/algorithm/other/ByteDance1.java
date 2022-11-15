package org.example.algorithm.other;

public class ByteDance1 {

    public static int rotate(int pos, int n) {

        return pos < n ? pos: pos - n;
    }
    // 求一个最长的子序列 这个序列小于阈值threshould
    public static void test(int[] input, int threshold) {

        int n = input.length;

        int max = 0;
        int sum = 0;
        int left = 0;
        int[] point = new int[]{0, 0};
        for (int right = 0; right < 2 * n; right++) {
            sum += input[rotate(right, n)];
            while (sum > threshold) {
                sum -= input[rotate(left, n)];
                left++;
            }
            if (right - left + 1 > max) {
                max = right - left + 1;
                point[0] = left;
                point[1] = right;
            }
        }

        System.out.println("left: " + rotate(point[0], n) + ", right: " + rotate(point[1], n));

    }

    public static void main(String[] args) {
        test(new int[]{1, 1, 5, 6, 3, 1, 2}, 5);
    }

}
