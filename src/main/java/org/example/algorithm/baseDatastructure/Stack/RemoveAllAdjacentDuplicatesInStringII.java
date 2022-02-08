package org.example.algorithm.baseDatastructure.Stack;

/**
 * 1209. Remove All Adjacent Duplicates in String II
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 * 中等的题目 虽然结果是对的，但是显然这个考的不是这种暴力的解法
 * 尝试用stack, 但是有一个缺点 aabbba 主要要解决这个问题
 * 这里呢 又有一个点 bbb完了之后是又会有一个a来触发的，这时候a可以看一下前面的是否满足条件
 */
public class RemoveAllAdjacentDuplicatesInStringII {

    public static String removeDuplicates2(String s, int k) {
        StringBuilder stringBuilder = new StringBuilder(s);
        int[] space = new int[s.length()];
        // 就算一开始处理了，像这种有删除的也难保后面会回到这个地方，要注意
        space[0] = 1;
        for (int i = 1; i < stringBuilder.length(); i++) {
            char c = stringBuilder.charAt(i);
            if (i > 0 && c == stringBuilder.charAt(i - 1)) {
                space[i] = space[i - 1] + 1;
                if (space[i] == k) {
                    stringBuilder.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            } else {
                space[i] = 1;
            }
        }
        return stringBuilder.toString();

    }
    public static String removeDuplicates(String s, int k) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        boolean c = true;
        while (!s.isEmpty() && c) {
            c = false;
            int nums = 1;
            char ch = s.charAt(0);
            for (int i = 1; i < s.length(); i++) {
                // 每次有消除 就要改成true 要重新循环
                if (s.charAt(i) == ch) {
                    nums += 1;
                    if (nums == k) {
                        // abbccd i = 2, 0 - 0,
                        s = s.substring(0, i - k + 1) + s.substring(i + 1); // 左开右闭
                        c = true;
                    }
                } else {
                    ch = s.charAt(i);
                    nums = 1;
                }

            }

        }
        // 先停下来分析 这道题要用什么数据结构，我现在全被stack给蒙住了 停止的条件1 没有可以消除的内容了，每次循环都要重置那个计数
        return s;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates2("deeedbbcccbdaa", 3));

    }
}
