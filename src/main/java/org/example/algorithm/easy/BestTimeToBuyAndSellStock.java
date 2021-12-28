package org.example.algorithm.easy;

/**
 * 没这么麻烦宝，我又看错题目还给自己脑补
 * 选一天就行了 不用每天
 * 不过不知道中级和高级的是不是要
 * <p>
 * 那啥简单的也有两种解法，双指针从两侧来，双指针从一侧去
 */
public class BestTimeToBuyAndSellStock {

    public static void version1(int[] prices, int[] dayMaxProfile) {

    }

    /**
     * fibonacci version
     *
     * @param prices
     * @return
     */
    public static int fibonacciHarderVersion(int[] prices, int[] dayMaxProfile, int length) {

        if (dayMaxProfile[length] > -1) {
            return dayMaxProfile[length];
        }

        // 用什么结构来存储？
        // 第七天的收益 = 第六天的收益 + 第六天买进 第七天卖出的收益 ||
        // 一共每天可以有n种步骤 从第一天买到底n天卖
        int maxProfile = 0;
        for (int i = length; i > 0; i--) {
            maxProfile = Math.max(fibonacciHarderVersion(prices, dayMaxProfile, i - 1) + Math.max(0, prices[i] - prices[i - 1]), maxProfile);
        }
        dayMaxProfile[length] = maxProfile;
        return dayMaxProfile[length];
    }

    public static int fibonacciVersion(int[] prices) {
        // for 循环 双指针
        // 要写对于2的特殊逻辑，那怎么才可以统一掉
        if (prices.length == 0) {
            return 0;
        }
        int i = 0;
        int j = i + 1;
        int max = 0;
        while (i < prices.length - 1 && j < prices.length) {
            max = Math.max(max, prices[j] - prices[i]);
            // 只有一个if else 要么j++ 要么i ++ 最重要的是搞清楚比较的对象
            // 不是光往下走就行 还要比较往下走和往上走的收益 还是跟之前一样 比较的是收益
            if (j + 1 == prices.length) {
                i++;
                continue;
            }
            if (j == i) {
                j++;
                continue;
            }
            boolean hasProfit = prices[j + 1] - prices[j] >= prices[i] - prices[i + 1];
            if (hasProfit) {
                j++;
            } else {
                i++;
            }
        }
        // 要么用指针下标控制 要么用内容控制不能小于0，用指针控制比较多
//        for (int i = 0; i < prices.length - 1; i++) {
//            while (j + 1 < prices.length) {
//                max = Math.max(max, prices[j] - prices[i]);
//                if (Math.max(prices[j + 1] - prices[i], 0) >= prices[j] - prices[i + 1]) {
//                    j++;
//                } else {
//                    break;
//                }
//            }
//        }

        return max;
    }

    /**
     * 不需要管长度的就是这么自信
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int prices[]) {
//        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
//            if (prices[i] < minprice) {
//                minprice = prices[i];
//            } else if (prices[i] - minprice > maxprofit) {
//                maxprofit = prices[i] - minprice;
//            }
//        }
        return maxprofit;
    }

    public static void main(String[] args) {
//        int[] prices = new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        int[] prices = new int[]{6, 7, 2, 4, 1, 2};
//        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
//        int[] prices = new int[]{2, 1, 4, 5, 2, 9, 7};
//        int[] dayMaxProfile = new int[prices.length];
//        Arrays.fill(dayMaxProfile, -1);
//        dayMaxProfile[0] = 0;
//        System.out.println(fibonacciHarderVersion(prices, dayMaxProfile, prices.length - 1));
//        System.out.println(fibonacciVersion(prices));
        System.out.println(maxProfit(prices));
    }
}