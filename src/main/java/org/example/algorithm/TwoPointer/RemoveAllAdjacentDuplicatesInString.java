package org.example.algorithm.TwoPointer;

import java.util.Stack;

/**
 * 1047. Remove All Adjacent Duplicates In String
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 * We repeatedly make duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 * 真同向双指针！
 * 题目特性，前后两个adjacent same letters, remove them all, and compare two letters adjacent to them
 * 还可以用栈的方式来实现，连连看、对对碰的方式
 * // 细节 stack.pop/peek之前一定要判空, 应该是说集合
 */
public class RemoveAllAdjacentDuplicatesInString {

    public static String removeDuplicates2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {

            if (!stack.isEmpty() && s.charAt(i) == stack.peek()) {
                stack.pop();
                continue;
            }
            stack.push(s.charAt(i));
        }

        StringBuilder builder = new StringBuilder();
        // 细节: 顺序变了
        while (!stack.isEmpty()) {
            builder.insert(0, stack.pop());
        }
        return builder.toString();

    }
    public static String removeDuplicates(String s) {
        int p1 = 0, p2 = 1;
        StringBuilder builder = new StringBuilder(s);
        while (p1 >= 0 && p2 < builder.length()) {
            // 00 11 22  0 11 0 22
            if (builder.charAt(p1) == builder.charAt(p2)) {
                builder.delete(p1, p2 + 1);
                if (p1 > 0) {
                    p1--;
                    p2--;
                }
            } else {
                p1++;
                p2++;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates2("abbaca"));
    }

}
