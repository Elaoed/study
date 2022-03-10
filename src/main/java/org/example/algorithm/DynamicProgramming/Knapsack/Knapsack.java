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

    public static int[][] dp = new int[itemNum + 1][bagWeight + 1];

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
        dpOneDimensionVersion();
        System.out.println(answer);
    }


    /**
     * 整一个模型可以用树来表示 dp(4, 4) 用4件物品来完成重量是4的组合
     * 树的模型要看是否有overlapping, 如果有的话需要用动态规划，没有的话可以用搜索
     * 二维dp是因为我们的目标是书包内物品的总价值，而变量是物品和书包的限重
     * dp的核心是解决存在overlapping子逻辑的方案，避免重复计算带来的额外空间复杂度
     */
    public static void dpTwoDimensionVersion() {

        // TODO: 所以dp[i][j]是前i件物品装进限重为j的背包可以获得的最大价值
        // 初始化是第0件物品装进限重为0-bagWeight的背包能获得的最大价值，因为没有物品装进去所以价值都为0
        Arrays.fill(dp[0], 0); // 第一行变成0, 放0个东西那不管背包有多大，价值也是0
        // 之所以第一列不能设置全部是0, 第一列的意思是不管可以放几个东西，但是背包的重量是0，一般都为0的
        // 但是不排除数字世界存在重量为0，但是价值不为0

        // 包里能放几个东西和取第几个东西的关系？取前面东西的路子都被走过了 可以直接照搬，比较关心多一个空格对大家的影响
        // 遍历这个袋子里可以装几个东西, 第一次遍历的时候袋子里只能装一个东西，哎嘿
        for (int i = 1; i <= itemNum; i++) {
            // 遍历袋子里东西的数量限定的时候，怎么发挥
            int itemWeight = weight[i - 1];
            for (int j = 0; j <= bagWeight; j++) {
                // j是剩余的容量/当前包的容量 不足以拿第weight[i] 所以i 就拿i - 1的冲
                // dp[i - 1][j] 也就是所谓的不装入第i件商品, j < itemWeight 背包无法容纳itemWeight
                // 背包虽然无法容纳这么大个 但是按照上次的来容纳一个15还是没问题的 woc 我又悟了
                if (j < itemWeight) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 不然就加上j的值, 也就是所谓的装入第i件商品
                    // 记住左边是商品件数，右边是背包重量, 也就是当前的位置是由之前的位置 + 1二来的
                    // max的左边是不拿这个东西目前背包里的东西个数，重量，和对应的价值，右边是拿了这个东西但是背包里面要拿出对应重量的东西的价值
                    // 当可以拿两个东西东西的时候 包重量为1-2的时候都只能放一个15的，包重量为3的时候可以放一个20的, 20要比15大 所以拿20的
                    // 当包重量为4的时候可以拿两个，这时候要把那个1再拿上，看下在代码里面怎么体现的
                    // 左边是 15， 右边那个是0 + 20, 再往下走是 左边的是15，右边的是15 + 20
                    // 急速定位上上一层的左侧，
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - itemWeight] + value[i - 1]);
                    // 这里才是拿和不拿，上面的if是不能拿
                    // 妈的我悟了
                }
            }
        }

        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        answer = dp[itemNum][bagWeight];
        // 等下整理下吧

    }

    // 优化 代码告诉我们 我们只需要上一排的数据
    public static void dpOneDimensionVersion() {
        // 放的是什么？ 放的肯定不是物品的数量的，既然是bagWeight肯定是包包的重量
        int[] dp = new int[bagWeight + 1];
        Arrays.fill(dp, 0);

        // 两层循环还是不能少
        for (int i = 1; i < itemNum; i++) {
            // 旧的物品都用过了不管, 也不一定是用过，后面可能要拿出来的那拿出来再说
            // 拿出来也算用过派和拿出来算没用过派
            int w = weight[i - 1], v = value[i - 1];
            for (int j = bagWeight; j >= w; j--) {
                // 之所以要倒序是因为，同一个列表反复用，每次新开始的时候，列表是上一个列表
                // 而我们最关心上一个列表的的前面部分，如果是正序来的，前面部分就被覆盖了...
                // 那是这样的 1. 也不是把整个列表都颠覆了，还是留下了前面w个存活
                // 2. 不会有问题吗 不会的后面本来就是要依据前面的内容来的，之前可以正向是因为上面还有一层快照着
                // 这里的dp[j] 其实就是dp[i - 1][j]
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }
        System.out.println(Arrays.toString(dp));
        answer = dp[bagWeight];

    }




}
