package org.example.algorithm.BinarySearch;

/**
 * 528. Random Pick with Weight
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 * 如果真的这道题没做过就过来了，我怕是题都看不懂
 * 总之就是要按照权重pickIndex, 权重由value / sum来决定
 * 那么普通的random是行不通的，最简单的方案就是在一个数组里面，按照random的个数往里面塞数据
 * 那随机取一个取到的概率就是value / sum, 只不过是要变得有序的，就可以用二分法, 怎么保证有序?
 *
 * 所以为什么要前缀和 + 二分
 * 首先，要建立一个新的bucket, 这个bucket负责存储1、3、3、3然后从中随机的抽一个，那抽到的是w里面具体的数，要拿到那个index, 必须在w里面遍历
 * 这样也是可以做到的，空间和时间复杂度都不低，而且需要用list, 不能用bucket, 因为bucket需要初始化数组长度而我们没法完全预料bucket有多长
 * 且w也没排序，不可用二分
 *
 * // Ok采用第二种方案 前缀和 + 二分法
 * 前缀和也是这种权重概率类问题的解法之一
 * 果然睡醒了比较理解
 * 前缀和就是 1， 2， 3， 4 分到的概率分别是 10% 20% 30% 40%
 * 不用扩张数组的方式
 * 把前缀和加起来就变成了 1， 3， 6， 10
 * 当random一个[1-10]之间的数看他在哪个区间就行了 结果和上一种方法是等价的 空间复杂度还低
 * 通过二分法的方式去查找，而且还省了排序，因为前缀和的方式一定是有序的!
 *
 * 权重随机的前缀和要考虑的点
 * 1. 考虑左边界
 * 2. 最后random * weight[weight.length - 1] // 这个才是最大值，能均匀分配到每一个区间
 * 3.
 */
public class RandomPickWithWeight {

    private int[] bucket;
    // length用来干嘛

    public RandomPickWithWeight(int[] w) {
        if (w == null) {
            return;
        }

        this.bucket = new int[w.length];
        bucket[0] = w[0];
        if (bucket.length == 1) {
            return;
        }

        // 前缀和就是bucket[i] = w[i] + bucket[i - 1]
        for (int i = 1; i < w.length; i++) {
            bucket[i] = w[i] + bucket[i - 1];
        }

    }

    public int pickIndex() {

        // 从bucket里面random拿出一个数, math.random = 0-1吧
//        int random = (int) (Math.random() * bucket.size());
//        return binarySearch(bucket.get(random));

        // 所以random摇号的时候 咦 这里有猫腻 需要乘的不是length 而是最大的那个数
        int random = (int) (Math.random() * bucket[bucket.length - 1]);
        return binarySearch(random);

    }

    private int binarySearch(int target) {
        int left = 0, right = bucket.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (bucket[middle] == target) {
                return middle;
            }
            if (target < bucket[middle]) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
        // 这里要取右区间 所以直接返回left
    }

    public static void main(String[] args) {

//        RandomPickWithWeight randomPickWithWeight = new RandomPickWithWeight(new int[]{3, 14, 1, 7});
        RandomPickWithWeight randomPickWithWeight = new RandomPickWithWeight(new int[]{1});
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());


    }
}
