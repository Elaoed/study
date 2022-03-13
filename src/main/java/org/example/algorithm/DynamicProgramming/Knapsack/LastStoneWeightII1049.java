package org.example.algorithm.DynamicProgramming.Knapsack;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 1049. Last Stone Weight II
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 *
 * We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y. The result of this smash is:
 *
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 *
 * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
 *
 * 仔细审题
 * 1. 两两相碰
 * 2. 重量要相减
 * 3. 结果最小
 * 最小的莫非于0，想办法凑零
 * 1. 先找出所有重量相等的
 * 2. 寻找三颗石头1v2重量相等的
 * // oh 呵呵呵果然当时没做出来 ::) 不用慌 dp哥哥来了
 */
public class LastStoneWeightII1049 {

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
            for (int stone : stones) {
                if (set.contains(stone)) {
                    set.remove(stone);
                } else {
                    set.add(stone);
                }
            }
            while (set.size() <= 1) {
                for (int stoneOne : stones) {
                    if (!set.contains(stoneOne)) {
                        continue;
                    }
                    for (int stoneTwo : stones) {
                        if (!set.contains(stoneOne)) {
                            continue;
                        }
                        int abs = Math.abs(stoneOne - stoneTwo);
                        if (set.contains(abs)) {
                            set.remove(abs);
                            set.remove(stoneOne);
                            set.remove(stoneTwo);
                        } else {
                            for (int i1 = 0; i1 < set.size(); i1++) {
                                if (i1++ < set.size()) {
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

    // 为什么放在dp 因为也有一个隐含的target, 不对他不是要全部消除，而是找到最小的解, 最小的解还是要最接近target
    // Math.min([Math.abs(h1 - target) * 2])
    // 转化为 隐含的target
    public static int dpVersion(int[] n) {
        if (n == null || n.length == 0) {
            return 0;
        }
        if (n.length == 1) {
            return n[0];
        }

        int sum = Arrays.stream(n).sum();
        int target = sum / 2;
        int[] dp = new int[target + 1];
        Arrays.fill(dp, 0);
        // 每块石头只能用一次
        for (int stone : n) {
            for (int i = target; i >= stone; i--) {
                // 如果是一维方程 01背包一定是要比较大小的 二维的也要的 不过是逼 dp[i][i - stone] + stone, dp[i - 1][j]
                dp[i] = Math.max(dp[i - stone] + stone, dp[i]);
            }
        }

        return sum - dp[target] * 2;

    }

    public static void main(String[] args) {
        System.out.println("In Stone Main....");
        int[] n = {2, 7, 4, 1, 8, 1};
        int res = dpVersion(n);
        System.out.println(res);
    }
}
