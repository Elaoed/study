package org.example.algorithm.baseDatastructure.Stack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * 150. Evaluate Reverse Polish Notation
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 * Note that division between two integers should truncate toward zero.
 * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.
 * 尝试一下用栈做？
 * 用栈真的很好做，针对于这种什么类型的题目呢？ 一个列表里面 每次需要对两个数据进行运算 运算的结果再和另外一个数进行运算的时候用栈真的非常合适 特别还是按照顺序的
 *
 */
public class EvaluateReversePolishNotation {



    public static int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        List<String> operators = Arrays.asList("+", "-", "*", "/");
        for (int i = 0; i < tokens.length; i++) {

            if (operators.contains(tokens[i])) {
                Integer v1 = stack.pop();
                Integer v2 = stack.pop();
                Integer v;
                if (Objects.equals(tokens[i], "+")) {
                    v = v1 + v2;
                } else if (Objects.equals(tokens[i], "-")) {
                    v = v1 - v2;
                } else if (Objects.equals(tokens[i], "*")) {
                    v = v1 * v2;
                } else {
                    v = v1 / v2;
                }
                stack.push(v);
            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }

        }

        return stack.pop();

    }

    public static void main(String[] args) {

        System.out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));

    }
}
