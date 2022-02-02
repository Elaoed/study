package org.example.algorithm.Sort;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 179. Largest Number
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * Since the result may be very large, so you need to return a string instead of an integer.
 * 他喵的 原先怕的是两个值加起来超纲 不过好像没这个限制 只是所有的数加起来容易超纲 所以返回值是string
 */
public class LargestNumber {

    public static String largestNumber(int[] nums) {

        return Arrays.stream(nums)
                .mapToObj(Integer::toString)
                // TODO: 为什么k1 + k2的v1 比v2 大却返回-1 问文文
                .sorted((k1, k2) -> {
                    String v1 = k1 + k2;
                    String v2 = k2 + k1;
                    for (int i = 0; i < v1.length(); i++) {

                        if (v1.charAt(i) > v2.charAt(i)) {
                            return -1;
                        } else if (v1.charAt(i) < v2.charAt(i)) {
                            return 1;
                        }
                    }
                    return 0;
                    // largest的定义 they form a largest number
                    // 搞清楚最关键

                }).collect(Collectors.joining("")).replaceAll("^0+", "0");

    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 30, 34, 5, 9};
//        int[] nums = new int[]{111311, 1113};
        final String s = largestNumber(nums);
        System.out.println(s);

    }

}
