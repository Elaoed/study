package org.example.algorithm.DynamicProgramming.Knapsack;

import java.util.Arrays;

/**
 * 动态规划里面的背包问题
 * 最基本的背包问题就是
 * <p>
 * 01背包问题：
 * 一共有 N 件物品，第 i（i 从 1 开始）件物品的重量为 w[i]，价值为 v[i]。
 * 在总重量不超过背包承载上限 W 的情况下，能够装入背包的最大价值是多少？
 * 完全背包问题
 * 与 01 背包不同就是每种物品可以有无限多个：一共有 N 种物品，每种物品有无限多个，第 i（i 从 1 开始）种物品的重量为 w[i]，价值为 v[i]。
 * 在总重量不超过背包承载上限 W 的情况下，能够装入背包的最大价值是多少？
 * 可见 01 背包问题与完全背包问题主要区别就是物品是否可以重复选取。
 * <p>
 * 416 / 494 / 139 / 279 / 322 / 377 / 518 / 1049
 * <p>
 * 是否可以根据一个 target（直接给出或间接求出），target 可以是数字也可以是字符串，
 * 再给定一个数组 arrs，问：能否使用 arrs 中的元素做各种排列组合得到 target。
 * 本质上也是组合问题，可以使用search, 使用二叉树长度为0，x0-xn 每个节点存01
 * <p>
 * 类型: 0-1,完全,分组
 * 最值,存在,组合(求....的排列组合)
 * 首先是背包分类的模板：
 * // 不考虑顺序的话内外循环是一样的
 * 1、0/1背包：外循环nums,内循环target,target倒序且target>=nums[i];
 * 2、完全背包：外循环nums,内循环target,target正序且target>=nums[i];
 * 3、组合背包(考虑顺序)：外循环target,内循环nums,target正序且target>=nums[i];
 * 4、分组背包：这个比较特殊，需要三重循环：外循环背包bags,内部两层循环根据题目的要求转化为1,2,3三种背包类型的模板
 * 然后是问题分类的模板：
 * 1、最值问题: dp[i] = max/min(dp[i], dp[i-nums]+1)或dp[i] = max/min(dp[i], dp[i-num]+nums);
 * 2、存在问题(bool)：dp[i]=dp[i]||dp[i-num];
 * 3、组合问题：dp[i]+=dp[i-num];
 * <p>
 * 背包一定都是>= <=不要忘记等于号
 * <p>
 * dp表格如何画 第一行作为表头代表target的递增，第一列作为表头代表nums数量的增加 // 回头再吸收一下花花的方法
 * ==============
 * ==============
 * ==============
 * 或许二维的方案更容易理解和拓展？
 * 把搜索的状态压缩
 * w要控制在一定范围之内 也就是weight? W(totalWeight) < 10^6
 * n >> 20
 * dp[i][j] = max(dp[i - 1][j - w[i] + v[i]), w[j] <= j <= W
 * 状态压缩
 * 可用可不用
 * Time complexity:O(NW)
 * Space complexity: O(NW) -> O(W)
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
        dpTwoDimensionVersion();
//        dpOneDimensionVersion();
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
                    // 01和完全在这里的区别是要不要i - 1
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
    // 一种是可以用每次数组替代，另外一种就是滚动数组 但是必须要reverse(只要一个int[])
    public static void dpOneDimensionVersion() {
        // 放的是什么？ 放的肯定不是物品的数量的，既然是bagWeight肯定是包包的重量
        int[] dp = new int[bagWeight + 1];
        Arrays.fill(dp, 0);

        // 两层循环还是不能少, 这个i即代表了能放几个东西，也代表了准备拿第几个东西 (双重含义所以比较难理解)
        for (int i = 1; i <= itemNum; i++) {
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

    /**
     * can be reduce to 0-1 kanpsack problem. By expand the item list, each item appears W/w[i] times;
     * for i = 1 to N
     *     for j = 1 to W/w[i]
     *          knapsack(w[i], v[i])
     *
     */
    public static void dpUnBoundedKnapsack() {

        Arrays.fill(dp[0], 0);

        for (int i = 1; i <= itemNum; i++) {
            int itemWeight = weight[i - 1];
            int itemValue = value[i - 1];
            for (int j = 0; j <= bagWeight; j++) {
                if (j < itemWeight) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 如果是0，1背包，在拿了新东西之后只要判断拿不拿就行了 max(dp[i - 1][j], dp[i - 1][j - itemWeight] + itemValue);
                    // 而完全背包 要考虑我拿一次还是要拿两次 max(dp[i - 1][j], dp[i - 1][j - itemWeight] + itemValue, dp[i - 1][j - 2 * itemWeight] + 2 * itemValue);
                    // 等等无限多的考虑 so -? 后来发现其实之前的问题在之前都考虑过了 这一行
                    // 差异仅仅是从i - 1变成i, 考虑这两者的区别
                    // 0, 1背包外层循环到这里已经是没有新物品时候的最优解，只要判断要不要放入就行
                    // 完全背包用i行的数据 上一层把没有新物品的完全背包问题完成了，这一行加了新物品之后要看放几个合适
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - itemWeight] + itemValue);
                }
            }
        }

        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        answer = dp[itemNum][bagWeight];

    }

    /**
     * for i = 1 to N:
     * // for j = w to W:
     *      dp[j] = max(dp[j], dp[j - w] + v)
     * 初始化dp[0][*], dp[1][*] ?
     *
     * unbounded是每件物品被使用无限次
     * bounded是每件物品被使用n次 也就是多重背包
     * both of them can be reduce to 0 - 1 problem but low efficient
     */
    public static void dpUnboundedKnapsackOneDimension() {

        int[] dp = new int[bagWeight + 1];
        Arrays.fill(dp, 0);

        for (int i = 1; i < itemNum; i++) {
            int w = weight[i - 1], v = value[i - 1];
            // 正向和逆向的区别在于要不要历史的小数据来支撑 TODO:
            for (int j = w; j <= bagWeight; j++) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }
        System.out.println(Arrays.toString(dp));
        answer = dp[bagWeight];

    }

    /**
     * 对于排序问题来说，只能外循环是 nums， 内循环是 sum，这样子才能保证顺序 比如说 nums是 1和 5，sum 是 6，
     * 如果外循环是 nums，内循环是 sum， 保证了当 sum = 6 的时候 只能是 6=1+5 存在顺序是，先1 后 5
     * 如果外循环是 sum，内循环是 nums，当sum = 6 的时候 有 6 = 1+5 和 6 = 5+1 两种 具体差距在哪里还是nums
     * 考虑顺序的话内外循环是一样的
     */
    public static void dpFullKnapsackOneDimensionOrdered() {
        // CoinChangeII518
    }

    //
    public static void dpMultiKnapsack() {
        // CombinationSumIV377
    }


}
