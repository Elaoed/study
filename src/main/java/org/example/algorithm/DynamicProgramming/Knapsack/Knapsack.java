package org.example.algorithm.DynamicProgramming.Knapsack;

import java.util.Arrays;

/**
 * 动态规划里面的背包问题
 * 最基本的背包问题就是
 * 01背包问题：
 * 一共有 N 件物品，第 i（i 从 1 开始）件物品的重量为 w[i]，价值为 v[i]。
 * 在总重量不超过背包承载上限 W 的情况下，能够装入背包的最大价值是多少？
 * 完全背包问题
 * 与 01 背包不同就是每种物品可以有无限多个：一共有 N 种物品，每种物品有无限多个，第 i（i 从 1 开始）种物品的重量为 w[i]，价值为 v[i]。
 * 在总重量不超过背包承载上限 W 的情况下，能够装入背包的最大价值是多少？
 * 可见 01 背包问题与完全背包问题主要区别就是物品是否可以重复选取。
 * <p>
 * 416 / 494 / 139 / 279 / 322 / 377 / 518 / 1049
 *
 * 本质上也是组合问题，可以使用search, 使用二叉树长度为0，x0-xn 每个节点存01
 */
public class Knapsack {
    public static int answer = 0;
    public static int bagWeight = 4;
    public static int[] weight = new int[]{1, 3, 4};
    public static int[] value = new int[]{15, 20, 30};
    public static int itemNum = weight.length;

    public static int[][] dp = new int[bagWeight + 1][bagWeight + 1];

    // result[i] := {w -> maxV} for first i items
    public static void dfs(int s, int curWeight, int curValue) {
        answer = Math.max(answer, curValue);
        if (s >= itemNum) {
            return;
        }
        // s是第几件物品吗, 这里不会考虑不到后面的物品放前面的遍历吗
        // 怎么判断已经拿过了的东西
        for (int i = s; i < itemNum; i++) {
            // 为什么等于的时候还要继续, 因为可能存在weight是0，但是value不是的情况
            // 这里就是两个for循环，每次把背包里的物品换一个 来求最优解, 这不就解决了 后面物品放前面的问题...
            if (curWeight + weight[i] <= bagWeight) {
                dfs(i + 1, curWeight + weight[i], curValue + value[i]);
            }
        }
    }

    public static void main(String[] args) {
        // 第一个物品?
//        dfs(0, 0, 0);
        dpVersion();
        System.out.println(answer);
    }


    public static void dpVersion() {

        int[][] dp = new int[bagWeight + 1][bagWeight + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, 0);
        }

        // 核心的一个点在于dp[i] 里面承担的是当背包里面重量为i的时候 最大的价值
        // 第一行是只选0号物品时候的价值, 所以选择是从上到下吗, 第一个物品不能换吗
        for (int i = bagWeight; i >= weight[0]; i--) {
            dp[0][i] = dp[0][i - weight[0]] + value[0];
        }

        // 遍历每一层
        for (int i = 1; i < weight.length; i++) {
            // 每一层遍历每一个物品
            for (int j = 0; j <= bagWeight; j++) {
                // j是剩余的容量 不足以拿第weight[i] 所以i 就拿i - 1的冲
                // dp[i - 1][j]
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 不然就加上j的值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }

    }


}
