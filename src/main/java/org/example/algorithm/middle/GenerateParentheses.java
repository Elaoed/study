package org.example.algorithm.middle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * 哎嘿这道题我感觉我可以，先冷静一下分析题目
 * n=3，为三组括号 ((()))
 * 除了最外面的一组括号，暴力解法当然是最稳定的 但是不容易的分, 警惕那些只能通过暴力解的
 * 后来还是排除了最外面一组括号的情况
 * 首先这里需要搞清楚 res和stringBuilder有这两个参数，其次open和close也非常的重要
 * 一开始我就是想很久都想不出来这一个个的是怎么塞进去的，要怎么返回
 * 解完之后倒是能想明白一点，会需要一个builder一直在尝试各种合理的字符串，这种递归最重要的就是解决终止条件
 * 在这里的终止条件就是左右括号的计数都变成0了，然后需要有个容器放这个终止的字符串，紧接着退回到上一步，开始尝试别的试错
 * 又因为左括号和右括号是可以分开添加的，所以她俩的技术也是分开记的
 * 再最后就是退回到上一步的时候记得要把尾部给消除掉 不然肯定会有问题
 * 在两边都可以走的情况下，直接顺序的先走左边再走右边，做好善后工作就行
 * 好了开始看看别的解决方案
 */
public class GenerateParentheses {

    static HashSet<String> set = new HashSet<>();
    // 如果是这种必须配对的东西，都可以拿这种方案做，()括号一定是成对存在的，第二个括号有两种方案，插入中间或者插在右边。那左边呢
    // (()), ()(), 第三队括号有三种选项 ((())), (()()), (())(), ()(()), ()()()
    // 这还必须用递归，因为也有前进一步和后退一步的情况
    // 绝绝子 重点还是审题啊
    public static void version3(int n, String str) {

        if (n == 0) {
            set.add(str);
            return;
        }
        for (int i = 1; i <= str.length(); i++) {
            version3(n - 1, str.substring(0, i) + "()" + str.substring(i));
        }
    }

    // 看了第一个解决方案 把res提出变成类变量，无需传递，但是为什么无需删除尾部呢？ 因为他用的是string不会改变自身对象 dfs
    public static void version2(List<String> res, StringBuilder stringBuilder, int open, int close) {

    }
    public static void version1(List<String> res, StringBuilder stringBuilder, int open, int close) {
        if (open == 0) {
            if (close == 0) {
                // 没有任何步骤所以不用消去尾部
                res.add(stringBuilder.toString());
                return;
            }
            version1(res, stringBuilder.append(")"), open, close - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1); // 用string可以避免这句话
            return;
        }
        if (open == close) {
            version1(res, stringBuilder.append("("), open -1, close);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return;
        }
        // 两边都可以走的情况下，怎么让他先走左边，回来之后再走右边
        version1(res, stringBuilder.append("("), open -1, close);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        version1(res, stringBuilder.append(")"), open, close - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

    }

    public static void main(String[] args) {
        List<String> res = new ArrayList<>();
        int n = 3;

//        String s = "1234";
//        System.out.println(s.substring(s.length() - 1, s.length()));
        // 也是有基础的 只不过基础的和我不一样
        version3(n - 1, "()");
        System.out.println(set);
//        System.out.println(res);
        // version3 这个好像更有意思，每次都拿

    }
}
