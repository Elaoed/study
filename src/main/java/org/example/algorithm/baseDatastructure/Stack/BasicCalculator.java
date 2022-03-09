package org.example.algorithm.baseDatastructure.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 224. Basic Calculator
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * 还是那一点，要注重边界条件
 * 我是部分拆括号，答案是一次性把括号全拆掉，根据括号前面的正负号来翻转
 * 字符串从左到右挨个处理，还是很好处理的 针对五种情况进行不同的处理，数字要特殊处理
 *
 */
public class BasicCalculator {

    public static int calculatePart(List<String> expr) {

        int v1 = Integer.parseInt(expr.get(expr.size() - 1));
        for (int i1 = expr.size() - 2; i1 >= 0; i1 -= 2) {

            String operator = expr.get(i1);
            Integer v2 = Integer.parseInt(expr.get(i1 - 1));
            if (Objects.equals(operator, "+")) {
                v1 += v2;
            } else if (Objects.equals(operator, "-")) {
                v1 -= v2;
            } else if (Objects.equals(operator, "*")) {
                v1 *= v2;
            } else {
                v1 /= v2;
            }
        }
        expr.clear();

        return v1;

    }

    public static int calculate(String s) {
        Stack<Integer> s1 = new Stack<>();
        List<String> expr = new ArrayList<>();

        // 行不通 还是要考虑两边没有括号的边界条件
        int res = 0;
        int sign = 1;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(') {
                s1.push(sign);
                i++;
            } else if (c == ')') {
                sign = s1.pop();
                i++;
            } else if (c == '+') {
                sign = 1;
                i++;
            } else if (c == '-') {
                sign = -1;
                i++;
            } else {
                // 开始计算数字
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + c - '0';
                    i++;
                }
                res += sign * num;
            }
        }

//        for (int i = 0; i < s.length(); i++) {
//            String c = s.substring(i, i + 1);
//            if (" ".equals(c)) {
//                continue;
//            }
//            // 什么时候往里面放, 不是最后一个的时候往里面放
//            if (!")".equals(c)) {
//                s1.push(c);
//                continue;
//            }
//            // ")" == c;
//
//            while (true) {
//                String pop = s1.pop();
//                if ("(".equals(pop)) {
//                    break;
//                }
//                expr.add(pop);
//            }
//            // 这里没考虑 * / 不带括号的情况
//            // 好了现在我们得到了3 + 4 + 5
//            int v1 = calculatePart(expr);
//            res += v1;
//            s1.push(String.valueOf(v1));
//        }
//
//        if (!s1.isEmpty()) {
//
//            while (!s1.isEmpty()) {
//                expr.add(s1.pop());
//            }
//
//            int v1 = calculatePart(expr);
//            res += v1;
//        }
//
        return res;
    }

    public static void main(String[] args) {

//        System.out.println(calculate("1 + 1"));
//        System.out.println(calculate(" 2-1 + 2 "));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));

    }

}
