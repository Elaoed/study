package org.example.algorithm.baseDatastructure.Heap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 264. Ugly Number II
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * Given an integer n, return the nth ugly number.
 * <p>
 * 前N个都可以用优先队列的方式, 根据丑数的特性可以得到，n * m 如何解析丑数的特性
 * 三个指针k 是怎么做的？
 * 稍微刷出一点感觉了 基本上都会实现一个基本款和优化款 同时获取思路，看思维不同的核心点在哪里
 * 第二轮的时候可以把这些整理一下，然后很多以前卡主的点都可以理顺一下
 * 动态规划 -> 后面在弄
 */
public class UglyNumberII {
    // 三指针也不难啊, 没有用priorityQueue，把这部分工作自己写代码对掉了。
    public static int nthUglyNumberThreePointer(int n) {
        final int[] ints = new int[n];
        Arrays.fill(ints, 1);
        int pointer2 = 0;
        int pointer3 = 0;
        int pointer5 = 0;
        // 核心思想是一样的，但是中间实现最小值的这块儿是不一样的，这个我是想不到的
        for (int i = 1; i < n; i++) {
            int num2 = ints[pointer2] * 2;
            int num3 = ints[pointer3] * 3;
            int num5 = ints[pointer5] * 5;
            ints[i] = Math.min(num5, Math.min(num2, num3));
            if (ints[i] == num2) {
                pointer2++;
            }
            if (ints[i] == num3) {
                pointer3++;
            }
            if (ints[i] == num5) {
                pointer5++;
            }
        }
        return ints[n - 1];

    }

    public static int nthUglyNumber(int n) {
        int[] factors = new int[]{2, 3, 5}; // 像这种需要乘起来的数，肯定逃不过用一个数组来表示，好遍历
        PriorityQueue<Long> heap = new PriorityQueue<>(); // 默认最大堆 通过reverse实现最小堆 然后从里面拿第n个数 // 默认是最小堆吗？
        heap.add(1L);
        Set<Long> set = new HashSet<>();
        set.add(1L);
        long ugly = 1L;
        // TODO: 基数的选择尤其重要，而且需要选择返回的数据类型很重要
        // 因为都是2， 3， 5的质数，所以不用纠结是2，3，5哪个为基数去乘，只要拿前面塞到heap里面的数据去乘就行了
        // n次 每次都乘2，3，5这样一定不会漏掉
        // 最大打不过某一个值的5^n //错了 这个循环是为了往res里面放数据，放n个就够了
        // 也不用管顺序 每次把最小的poll出来就行了
        for (int i = 0; i < n; i++) {
            // 怎么找到heap里面的值呢
            long curr = heap.poll();
            ugly = curr;
            for (int factor : factors) {
                if (!set.contains(ugly * factor)) {
                    heap.add(ugly * factor);
                    set.add(ugly * factor);
                }
            }

        }
        return (int) ugly;
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumberThreePointer(5));
//        final PriorityQueue<Integer> integers = new PriorityQueue<>();
//        integers.add(1);
//        integers.add(2);
//        System.out.println(integers.poll()); // 默认是最小堆
    }
}
