package org.example.algorithm.baseDatastructure.Stack;

import java.util.Stack;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 * Stack相比 一个单独变量来记录的好处是，有历史，解决完了peek的，历史的就被推出来变成peek 还是针对于先进后出的比较游泳
 * 我这个时间复杂度就O(n) 空间复杂度大一点
 * 还有两次遍历 先把多余的右括号变成0，再反向把多余的左括号变成0，然后把所有的0都删掉
 */
public class MinimumRemoveToMakeValidParentheses{

    public static String minRemoveToMakeValid(String s) {
        // 首先不要冲动 很明显是用stack, valid parentheses来做 不满足的不要入栈就行
        StringBuilder res = new StringBuilder(s);
        Stack<Integer> s1 = new Stack<>();
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) == '(') {
                s1.push(i);
            } else if (res.charAt(i) == ')') {
                if (s1.isEmpty()) {
                    // 放弃这个家伙
                    res.delete(i, i + 1);
                    i--;
                } else {
                    s1.pop();
                }
            }
        }

        while (!s1.isEmpty()) {
            // 少了一个计数 不知道要删哪个了
            Integer i = s1.pop();
            res.delete(i, i + 1);
        }

        return res.toString();

    }

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("a)b(c)d"));

    }

}
