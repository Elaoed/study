package org.example.algorithm.BaseDatastructure.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 先做一遍最蠢的解法
 * and 善用map, 存储 并且比较--差额--
 * 给出解决方案的时候还是要多想想，用HashSet能找到又怎么样，只能返回true/false
 */
public class TwoSum {

    public static void main(String[] args) {
        int target = 18;
        int[] list = new int[]{2, 7, 11, 15};
//        version1(list, target);
        version2(list, target);
    }

//    public static void version3(int[] nums, int target) {
//        int left = 0, right = nums.length - 1;
//        Arrays.sort(nums);
//        while (left < right) {
//            if (left + right)
//        }
//
//    }

    public static void version2(int[] list, int target) {
        Map<Integer, Integer> map = new HashMap<>(list.length);
        for (int i = 0; i < list.length; i++) {
            Integer oldP = map.get(target - list[i]);
            if (oldP != null) {
                System.out.println("" + oldP + " " + i);
                return;
            }
            map.put(list[i], i);
        }
        System.out.println("no appropriate numbers");

    }

    public static void version1(int[] list, int target) {
        int p1 = -1;
        int p2 = -1;
        boolean flag = false;
        while (++p1 < list.length - 1) {
            p2 = p1;
            while (++p2 < list.length) {
                if (list[p1] + list[p2] == target) {
                    flag = true;
                    break;
                }
            }
            if (flag == true) {
                break;
            }
        }
        if (flag == true) {
            System.out.println("" + p1 + " " + p2);
        } else {
            System.out.println("no appropriate numbers");
        }
    }
}
