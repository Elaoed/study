package org.example.algorithm.middle;

import java.util.HashSet;

/**
 * 仔细审题
 * 1. 两两相碰
 * 2. 重量要相减
 * 3. 结果最小
 * 最小的莫非于0，想办法凑零
 * 1. 先找出所有重量相等的
 * 2. 寻找三颗石头1v2重量相等的
 */
public class LastStoneWeightII {

    public static int lastStoneWeightII(int[] stones) {

        int res = 0;
        if (stones.length == 0) {
            return 0;
        } else if (stones.length == 1) {
            return 1;
        } else if (stones.length == 2) {
            return Math.abs(stones[stones[0] - stones[1]]);
        } else {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < stones.length; i++) {
                if (set.contains(stones[i])) {
                    set.remove(stones[i]);
                } else {
                    set.add(stones[i]);
                }
            }
            while (set.size() <= 1) {
                for (int i = 0; i < stones.length; i++) {
                    if (!set.contains(stones[i])) {
                        continue;
                    }
                    for (int k = 0; k < stones.length; k++) {
                        if (!set.contains(stones[i])) {
                            continue;
                        }
                        int abs = Math.abs(stones[i] - stones[k]);
                        if (set.contains(abs)) {
                            set.remove(abs);
                            set.remove(stones[i]);
                            set.remove(stones[k]);
                        } else {
                            for (int i1 = 0; i1 < set.size(); i1++) {
                                if (i1 ++ < set.size()) {
                                    res = Math.abs(res - i1);
                                    set.remove(stones[i1]);
                                }
                            }
                        }
                    }

                }
            }

        }

        return res;

    }

    public static void main(String[] args) {
        System.out.println("In Stone Main....");
//        int[] n = {2, 7, 4, 1, 8, 1};
//        int res = lastStoneWeightII(n);
//        for (int i = 0; i < res; i++) {
//            System.out.println(res);
//        }
    }
}
