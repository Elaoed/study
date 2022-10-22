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

    public static int[][] ddp = new int[itemNum + 1][bagWeight + 1];

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


    /**
     * 整一个模型可以用树来表示 dp(4, 4) 用4件物品来完成重量是4的组合
     * 树的模型要看是否有overlapping, 如果有的话需要用动态规划，没有的话可以用搜索
     * 二维dp是因为我们的目标是书包内物品的总价值，而变量是物品和书包的限重
     * dp的核心是解决存在overlapping子逻辑的方案，避免重复计算带来的额外空间复杂度
     *
     * 背包问题是一种组合优化的 NP 完全问题:有 N 个物品和容量为 W 的背包，每个物品都有 自己的体积 w 和价值 v，
     * 求拿哪些物品可以使得背包所装下物品的总价值最大。如果限定每种物 品只能选择 0 个或 1 个，
     * 则问题称为 0-1 背包问题;如果不限定每种物品的数量，则问题称为无 界背包问题或完全背包问题。
     *
     */
    public static int dpTwoDimensionVersion(int[] weight, int[] value, int packCapacity) {
        // dp核心的内容就是确定dp中值的意思;
        // 比如这题不存在在leetcode上背包的原题
        // TODO: 所以dp[i][j]是<<前i件物品>>装进<<限重为j>>的背包可以获得的<<最大价值: value>>
        int[][] dp = new int[weight.length + 1][packCapacity + 1];
        int itemNum = weight.length;

        // 初始化是第0件物品装进限重为0-bagWeight的背包能获得的最大价值，因为没有物品装进去所以价值都为0
        // 第一行变成0, 放0个东西那不管背包有多大，价值也是0 默认就是0
        // 之所以第一列不能设置全部是0, 第一列的意思是不管可以放几个东西，但是背包的重量是0，一般都为0的
        // 但是不排除数字世界存在重量为0，但是价值不为0
        // int[][] 初始化默认 都是0

        // 包里能放几个东西和取第几个东西的关系？取前面东西的路子都被走过了 可以直接照搬，比较关心多一个空格对大家的影响
        // 遍历这个袋子里可以装几个东西, 第一次遍历的时候袋子里只能装一个东西，哎嘿
        // 对于i, j只有dp用到时候直接用，在入参取值的时候都要 - 1
        for (int i = 1; i <= itemNum; i++) {
            // 遍历袋子里东西的数量限定的时候，怎么发挥
            int itemWeight = weight[i - 1];
            int itemValue = value[i - 1];
            for (int j = weight[i - 1]; j <= packCapacity; j++) {
                // j是剩余的容量/当前包的容量 不足以拿第weight[i] 所以i 就拿i - 1的冲
                // dp[i - 1][j] 也就是所谓的不装入第i件商品, j < itemWeight 背包无法容纳itemWeight
                // 背包虽然无法容纳这么大个 但是按照上次的来容纳一个15还是没问题的 woc 我又悟了
                // 放不下了
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
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - itemWeight] + itemValue);
                    // 这里才是拿和不拿，上面的if是不能拿 左边是不拿 右边是拿,
                    // j - itemWeight 当然原先包里的东西是越重越好，最重是j - itemWeight, 剩下的多的空间才可以塞itemWeight, 并且要在原先的价值的基础之上加上itemValue妙啊
                    // 妈的我悟了
                }
            }
        }

        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }

        return dp[itemNum][packCapacity];

    }

    /**
     * 优化的核心理念是: 只需要上一排的数据
     * 一种是可以用每次数组替代，另外一种就是滚动数组 但是必须要reverse(只要一个int[])
     */
    public static int dpOneDimensionVersion(int[] weight, int[] value, int packCapacity) {
        // 放的是什么？ 放的肯定不是物品的数量的，既然是bagWeight肯定是包包的重量 当包的容量为i的时候包里最大价值是value
        int[] dp = new int[packCapacity + 1];
        int itemNum = weight.length;

        // 两层循环还是不能少, 这个i即代表了能放几个东西，也代表了准备拿第几个东西 (双重含义所以比较难理解)
        for (int i = 1; i <= itemNum; i++) {
            // 旧的物品都用过了不管, 也不一定是用过，后面可能要拿出来的那拿出来再说
            // 拿出来也算用过派和拿出来算没用过派
            int w = weight[i - 1], v = value[i - 1];
            // 现在结果是[0, 15, 15, 20, 35]
            // 如果改成完全背包的话结果会变成[0, 15, 30, 45, 60]
            for (int j = packCapacity; j >= w; j--) {
                // 之所以要倒序是因为，同一个列表反复用，每次新开始的时候，列表是上一个列表
                // 而我们最关心上一个列表的的前面部分，如果是正序来的，前面部分就被覆盖了...
                // 那是这样的 1. 也不是把整个列表都颠覆了，还是留下了前面w个存活
                // 2. 不会有问题吗 不会的后面本来就是要依据前面的内容来的，之前可以正向是因为上面还有一层快照着
                // 这里的dp[j] 其实就是dp[i - 1][j]
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[packCapacity];

    }

    /**
     * can be reduce to 0-1 kanpsack problem. By expand the item list, each item appears W/w[i] times;
     * for i = 1 to N
     *     for j = 1 to W/w[i]
     *          knapsack(w[i], v[i])
     *
     */
    public static int dpUnBoundedTwoDimension(int[] weight, int[] value, int packCapacity) {

        int itemNum = weight.length;
        int[][] dp = new int[itemNum + 1][packCapacity + 1];

        for (int i = 1; i <= itemNum; i++) {
            int itemWeight = weight[i - 1];
            int itemValue = value[i - 1];
            for (int j = 0; j <= packCapacity; j++) {
                if (j < itemWeight) {

                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 如果是0，1背包，在拿了新东西之后只要判断拿不拿就行了 max(dp[i - 1][j], dp[i - 1][j - itemWeight] + itemValue);
                    // 而完全背包 要考虑我拿一次还是要拿两次 max(dp[i - 1][j], dp[i - 1][j - itemWeight] + itemValue, dp[i - 1][j - 2 * itemWeight] + 2 * itemValue);
                    // 等等无限多的考虑 so -? 后来发现其实之前的问题在之前都考虑过了 这一行
                    // 差异仅仅是从i - 1变成i, 考虑这两者的区别
                    // 0, 1背包外层循环到这里已经是没有新物品时候的最优解，只要判断要不要放入就行
                    // 完全背包用i行的数据 上一层把没有新物品的完全背包问题完成了，这一行加了新物品之后要看放几个合适
                    // i - 1是放i - 1件物品的时候包里最大价值，但是这里没有物品放置数量的限制，一个物品可以放无限次，所以就按照大的来
                    // i - 1和i的区别 应该是
                    // 核心还是要看j，j就是包有多少容量
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - itemWeight] + itemValue);
                    // dp[i - 1][j - itemWeight] + itemValue: 后面一段还算懂的，前面的i - 1
                    // 突然就悟了 01背包和无限背包的区别在于当前的物品能不能无限放，i - 1的意思是选一个不能放当前物品的结果 然后选择要不要加入当前物品
                    // i的意思是选一个可以放过当前物品的结果 然后可以继续放
                }
            }
        }

        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[itemNum][packCapacity];

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
    public static int dpUnboundedOneDimension(int[] weight, int[] value, int packCapacity) {

        int itemNum = weight.length;
        int[] dp = new int[packCapacity + 1];

        for (int i = 1; i <= itemNum; i++) {
            int w = weight[i - 1], v = value[i - 1];
            // 正向和逆向的区别在于要不要历史的小数据来支撑
            for (int j = w; j <= packCapacity; j++) {
                // 需要历史数据，但是只需要w位以前的历史数据, 同样都是j - w一个是从后面往前面防止前面被覆盖，一个是直接从w开始
                // 同样的正序和逆序的区别在于，逆序用到的数据都是上一行的数据，没有当前物品参与的最大值，而正序用的是可以有当前商品参与的结果
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[packCapacity];

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

    /**
     * 爬楼梯 每次只能爬一阶或者两阶
     * @return
     */
    public static int climbStairs(int n) {
        // dp中的每一项代表着爬n阶有几种解法
        int[] steps = new int[]{2, 3, 5};
        int min = Integer.MAX_VALUE;
        for (int step: steps) {
            min = Math.min(min, step);
        }

        int[] dp = new int[n + 1];
        dp[0] = 1; // 重要测试dp[0] 这个无边界值是1
        // 外侧是target 内侧是nums是排列 数量会多, 反之是组合(针对硬币这种无序的题目), 核心在于2、3，3,2两种情况会不会重复
        // 内外循环调换的区别在于 step会执行多次
        for (int step : steps) {
            for (int i = 1; i <= n; i++) {
            // 转移方程变成了 dp[n] = dp[n - steps[0]] + dp[n - steps[1]] + dp[n - steps[2]];
            // steps里面最小的值 之前的步子都是0?
                if (i >= step) {
                    dp[i] += dp[i - step];
                }
            }
        }
        // 之所以这里当n = 5的时候有错误是这么三种情况 5、2 + 3、3 + 2 重复算了2/3 当外层是items的时候 实际上是固定了顺序 所以只会有一种2、3
        // 没问题，先跨2和先跨3的确是两种不同的走法
        System.out.println(Arrays.toString(dp));
        return dp[n];

    }



    public static void main(String[] args) {
        int answer = dpTwoDimensionVersion(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4);
//        int answer = dpOneDimensionVersion(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4);
//        int answer = dpUnBoundedTwoDimension(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4);
//        int answer = dpUnboundedOneDimension(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4);
//        System.out.println(answer);
        System.out.println(climbStairs(5));
    }

}
