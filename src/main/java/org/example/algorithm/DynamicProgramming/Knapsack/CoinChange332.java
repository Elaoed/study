package org.example.algorithm.DynamicProgramming.Knapsack;

import java.util.Arrays;
import java.util.HashMap;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the <<"fewest">> number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an #infinite# number of each kind of coin. 完全背包问题
 * 果然不愧是一道middled的体型 还是我naive了，以为只要双重循环从大数开始-下来就行
 * 实际上优先需要满足的是能够凑上，凑上里面再挑数量最少的那个答案
 * 大方向宗旨是不变的，只是不满足的这个条件需要等到所有情况都遍历完了才知道
 * 不要一开始就陷在具体的语句里面了 // 找准大方向开始解剖
 * // 递归 + 从大到小 + amount <= 0 则回退一步改用其他的方式 两个大的可能用三个小的能抵上 后面可以少用几个更小的 用中等的补上，所以还是要递归遍历全部才行
 * fps dps
 * question1: 什么条件下返回 amount <= 0;
 * 没有列表的情况下 需要全局maxN计数比较
 * 当amount = 0的时候需要比较maxN并进行替换
 * 简化模型: Integer.MAX_VALUE 比小的时候可以用maxValue简化模型
 * 考虑不超过的时候需要有限考虑最大次数i的数量
 * 有限的for循环中为了拿到返回值可以多次初始化int res;
 * // dp 一步一步往上走的方案看起来很粗暴啊 -- 哈哈是啊 真的很粗暴又很简单 主要是有overlapping question
 * 这道题可以用bfs/dfs等等来做: powcai的评论
 *
 */
public class CoinChange332 {

    // 设置初始值 如果没有硬币满足则返回初始值
    public static int minN = -1;
    // 增加缓存, key: amountLeft, value: n
    // 什么时候放？
    public static HashMap<Integer, Integer> cache = new HashMap<>();

    public static void version1(int[] coins, int i, int n, int amountLeft) {
        if (amountLeft == 0) {
            minN = minN == -1 ? n : Math.min(minN, n);
        } else if (amountLeft > 0) {
            for (int j = 0; j <= i; j++) {
                version1(coins, i - j, n + 1, amountLeft - coins[i]);
            }
        }
    }

    /**
     * @param coins
     * @param i
     * @param amountLeft
     * @return minN under amountLeft
     * 需要传递的只有两种参数
     * 1. 累加/累减
     * 2. array for index
     * 遇到这种返回值相加的那个返回值是不用传的
     */
    public static int version2(int[] coins, int i, int amountLeft) {
        Integer cachedN = cache.get(amountLeft);
        if (cachedN != null) {
            return cachedN;
        }

        if (amountLeft < 0) {
            return -1;
        } else if (amountLeft == 0) {
            return 0;
        } else {
            int n = -1;
            int newN;
            for (int j = 0; j <= i; j++) {
                if (amountLeft - coins[i] > 0) {
                    newN = version2(coins, i - j, amountLeft - coins[i]);
                    n = n == -1 ? newN : Math.min(n, newN);
                }
            }
            cache.put(amountLeft, n);
            return n;
        }
    }

    /**
     * 斐波那契方式 和前面差一步 有限的枚举 每个硬币的面值
     * 不必排序搞大小 因为都要算到最后
     * <p>
     * 笑死 官方解答完全没有可读性
     * 笑不出来了 完全不知道问题在在哪里
     *
     * @param coins
     * @param amountLeft
     * @return
     */
    public static int version3(int[] coins, int amountLeft) {
        if (amountLeft < 0) {
            return -1;
        }

        if (amountLeft == 0) {
            return 0;
        }

        if (cache.get(amountLeft) != null) {
            return cache.get(amountLeft);
        }
        int min = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i >= 0; i--) {
            int coin = coins[i];
            int res = coinChange(coins, amountLeft - coin);
            if (res > 0 && res < min) {
                min = res + 1;
            }
        }
        cache.put(amountLeft, min == Integer.MAX_VALUE ? -1 : min);
        return cache.get(amountLeft);
    }


    /**
     * 状态：金额
     * 转移：一个硬币，这个硬币的金额在coins[]数组中遍历
     * dp函数：输入金额，返回最少硬币数
     * version DP
     * 这道题动态规划的时候是把金额和下标联系了起来，这个金额当下标对应的硬币数量是在这个金额减硬币下标对应的数量基础之上+1
     *
     * @param coins
     * @param amount
     * @return
     */
    public int version4(int[] coins, int amount) {
        //最多的硬币情况是全部是1元，共有amount个硬币，共有amount+1个状态，amount+1个金额
        // 空间以最小值需要的最多数量来算
        int[] dp = new int[amount + 1];
        //必须将所有的dp赋最大值，因为要找最小值, 根据需要寻找的最大最小值填充相反的值
        Arrays.fill(dp, amount + 1);
        //自底向上，金额为0，最小硬币数为0 初始化赋值
        dp[0] = 0;
        //自底向上
        for (int i = 1; i <= amount; i++) {
            //遍历coins的金额
            for (int coin : coins) {
                //amount - coin 必须大于0，否则数组溢出
                if (i >= coin) {
                    //金额为11的最小硬币数 和 金额为(11-一个面值)的最小硬币数+1 比较最小值
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    // 只所以只放一个是因为外面会对金额i做迭代的
                }
            }
        }
        //返回金额为amount的最小硬币数 根据测试用例判断dp[amout]>amount
        // 只是为了满足测试用例里面的-1
        // 老实说这种方式我是想不到的
        // 刷了这些题目 去归纳总结得到一个自己的思维方式吧
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static int coinChange(int[] coins, int amount) {
        // 如果入参就是0 则返回0
        if (amount == 0) {
            return 0;
        }
        // 没有满足的和不需要满足的
        Arrays.sort(coins);
//        version1(coins, coins.length - 1, 0, amount);
//        version2(coins, coins.length - 1, amount);
//        return minN;
        return version3(coins, amount);
        // 现在做不动
    }

    // 模板有了剩下的都是细节, 比如这里要的是数量不是价值，如果是数量就 + 1, 如果是价值就 + v
    public static int dpVersionAfterStudied(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }

        if (coins == null || coins.length == 0) {
            return -1;
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); // 第一个位置放0，因为amount = 0的时候 肯定coins 为0 那也说不定hhh
        dp[0] = 0;

        // coins = [2], amount = 3 的时候
        for (int i = 1; i <= coins.length; i++) {
            int coin = coins[i - 1];
            // 核心是这个 dp解决子问题开始到解决大问题，从小到大就是amount从零开始的步骤
            // 外面的coins.length只是用来换各种不同情况的
            // j = 0 毫无意义，最大应该是当amount = amount的时候
            for (int j = 0; j <= amount; j++) {
                // 我tm这种取最小的很难处理的，中间的数根本没法处理，需要额外加判断前一次！=Integer.MAX_VALUE
                if (coin <= j && dp[j - coin] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{2};
        int amount = 3;
        System.out.println(dpVersionAfterStudied(coins, amount));
    }
}
