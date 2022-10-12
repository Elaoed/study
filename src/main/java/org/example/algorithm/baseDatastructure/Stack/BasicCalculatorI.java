package org.example.algorithm.baseDatastructure.Stack;

import java.util.Stack;

/**
 * 224. Basic Calculator
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 */
public class BasicCalculatorI {

    private static boolean isUnaryNow = false;

    public static boolean isOperand(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static boolean isUnaryOperand(char c) {
        return c == '+' || c == '-';
    }

    public static int priority(char c, Boolean isUnary) {
        if (isUnary) {
            // 一元运算符优先级最高
            return 3;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            return -1;
        }
    }

    public static void operate(Stack<Integer> stack, char operand, Boolean isUnary) {
        // 遇到一元运算符啦
        // 同时解决了---的问题反正只要取反就可以了
        if (isUnary) {
            switch (operand) {
                case '+':
                    stack.push(stack.pop());
                    break;
                case '-':
                    stack.push(-stack.pop());
                    break;
                default:
                    // throw error
                    break;
            }
        } else {
            int v2 = stack.pop();
            int v1 = stack.pop();
            switch (operand) {
                case '+':
                    stack.push(v1 + v2);
                    break;
                case '-':
                    stack.push(v1 - v2);
                    break;
                case '*':
                    stack.push(v1 * v2);
                    break;
                case '/':
                    stack.push(v1 / v2);
                    break;
                default:
                    break;
                // throw error
            }
        }
    }

    public static int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Object[]> symbolStack = new Stack<>(); // 之所以叫symbol因为还有()

        s = s.replace(" ", "");
        boolean mayBeUnary = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }

            if (c == '(') {
                symbolStack.push(new Object[]{c, false});
                // 想法是左边不管右边，右边管左边哎嘿，右边管左边的同时，numStack里面从栈头开始都是这对括号里面的操作数，妙啊！
                mayBeUnary = true; // 左括号的右边可能出现一元运算符
            } else if (c == ')') {
                while ((Character) symbolStack.peek()[0] != '(') {
                    Object[] operandArray = symbolStack.pop();
                    Character operand = (Character) operandArray[0];
                    Boolean isUnary = (Boolean) operandArray[1];
                    operate(numStack, operand, isUnary);
                }
                symbolStack.pop();
                mayBeUnary = false; // 右括号的右边就不可能了
            } else if (isOperand(c)) {
                if (mayBeUnary && isUnaryOperand(c)) {
                    isUnaryNow = true;
                }
                // 如果发现当前操作符是优先级低的，就输出所有优先级比他高的！并处理 优先级相等也行吗 果然从尾部往前是毫无阻力的 只是我开始没把数字的问题处理掉
                // 这个有什么影响吗 如果当前是一元操作符
                while (!symbolStack.isEmpty() &&
                        (!isUnaryNow && priority((Character) symbolStack.peek()[0], (Boolean) symbolStack.peek()[1]) >= priority(c, isUnaryNow) ||
                                // 这个条件不可能打成 为什么还要放在这里？ 想不通
                                isUnaryNow && priority((Character) symbolStack.peek()[0], (Boolean) symbolStack.peek()[1]) > priority(c, isUnaryNow))
                ) {
                    Object[] operandArray = symbolStack.pop();
                    operate(numStack, (Character) operandArray[0], (Boolean) operandArray[1]);
                }

                // 仅有当前无法评判应该做什么操作，所以留到下次 woc优雅！
                symbolStack.push(new Object[]{c, isUnaryNow});
                // 问题出在op
                mayBeUnary = true;
            } else {
                // 这里都是数字了
                int number = 0;
                while (i < s.length() && s.charAt(i) >= '0') {
                    number = number * 10 + s.charAt(i++) - '0';
                }
                // 因为上面执行完还多加了一次
                i--;
                numStack.push(number);
                mayBeUnary = false; // 数字右边不可能
            }
            isUnaryNow = false;
        }

        while (!symbolStack.isEmpty()) {
            Object[] operand = symbolStack.pop();
            operate(numStack, (Character) operand[0], (Boolean) operand[1]);
        }
        return numStack.pop();
    }

    public static void main(String[] args) {

//        System.out.println(calculate("1 + 1"));
//        System.out.println(calculate(" 2-1 + 2 "));
        System.out.println(calculate("- (3 + (4 + 5))"));

    }

}
