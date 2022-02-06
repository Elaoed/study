package org.example.algorithm.baseDatastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 350. Intersection of Two Arrays II
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 * 这种没有方法论的，可能一紧张就做不出来了, 或者想错了, 先刷一遍把，然后看册子和对着leetcode-plus刷
 * 1. 我自己的这种方法 通过HashMap存计数，拿出来减计数的方式
 * 2. 同理，可以用list, 遍历list2的时候一旦list1 contains 就删掉那个
 * int和Integer可以通过boxed来转
 * 3. 还可以通过排序来处理, 排序完成两个指针进行对比和前进
 */
public class IntersectionOfTwoArraysII {

    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);
        }

        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                result.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
    }

}
