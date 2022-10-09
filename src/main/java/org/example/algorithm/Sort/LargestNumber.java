package org.example.algorithm.Sort;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 179. Largest Number
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * Since the result may be very large, so you need to return a string instead of an integer.
 * 他喵的 原先怕的是两个值加起来超纲 不过好像没这个限制 只是所有的数加起来容易超纲 所以返回值是string
 * 这种算是看过才知道题，得靠记的
 * 既然最后是要拼在一起，那在选的时候就先拼在一起试试咯！
 */
public class LargestNumber {

    public static String largestNumber(int[] nums) {

        return Arrays.stream(nums)
                .mapToObj(Integer::toString)
                // TODO: 为什么k1 + k2的v1 比v2 大却返回-1 问文文 -1就是小，小就是要在前面啊 这题是大的在前面 o
                // Collection是size, String是length(), Array是length
                .sorted((k1, k2) -> {
                    String v1 = k1 + k2;
                    String v2 = k2 + k1;
                    // 因为怕相加超过移除，所以用字符串比较的方法，而且通过相加的方式保持两边长度一致可以直接比较
                    for (int i = 0; i < v1.length(); i++) {
                        if (v1.charAt(i) > v2.charAt(i)) {
                            return -1;
                        } else if (v1.charAt(i) < v2.charAt(i)) {
                            return 1;
                        }
                    }
                    return 0;
                    // largest的定义 they form largest number
                    // 搞清楚最关键

                }).collect(Collectors.joining("")).replaceAll("^0+", "0");
        //  细节: 因为存在0的数字，单纯的[0, 0]就会让人吃不消, 只是不会存在01 02这种数字而已，刚刚没考虑到的

    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 30, 34, 5, 9};
//        int[] nums = new int[]{111311, 1113};
        final String s = largestNumber(nums);
        System.out.println(s);

    }

}
