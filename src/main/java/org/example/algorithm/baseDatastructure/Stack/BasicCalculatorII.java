package org.example.algorithm.baseDatastructure.Stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 227
 * 原先我设计这套嗯 蛮好的 看起来挺用功的，但是没有鸟用，核心是用sign控制数字的正负极，压入stack，
 * 如果有乘除的要先算，最后在stack里面的只有数字，连符号都没有，全部相加就完事儿
 * 前面那题是150
 * <p>
 * 重新审题啊宝
 */
public class BasicCalculatorII {

    // 这道题相比Evaluate Reverse Polish Notation来说，复杂了一些，在于给出的是一个human readable的表达式
    // 需要自己去转化成为上一道题那样系统可辨别的形式，并且还存在优先级，比如 * / 优先级高于 + -;
    // just try it!
    private Stack<String> stack = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public int calculate(String s) {
        // 还是使用stack压入法，现在的想法是先把* /做了
        // 不过有个问题待解决，就是是否给出来的变量都是个位数，如果是个位数就好弄了。先做个个位数的解法吧
        // 果然遇到没有operand和不是个位数的就不行了，算了看题解吧
        int i = 0;
        int v1;
        int v2;
        List<String> operands = Arrays.asList("+", "-", "*", "/");

        s = s.replace(" ", "");
        while (i < s.length()) {
            String x = String.valueOf(s.charAt(i));
            if (operands.contains(x)) {
                if (x.equals("*")) {
                    v1 = Integer.parseInt(stack.pop());
                    v2 = (int) s.charAt(++i) - 48;
                    stack.push(String.valueOf(v1 * v2));
                } else if (x.equals("/")) {
                    v1 = Integer.parseInt(stack.pop());
                    v2 = (int) s.charAt(++i) - 48;
                    stack.push(String.valueOf(v1 / v2));
                } else {
                    String nextOperand = String.valueOf(s.charAt(i + 2));
                    if ((i + 2 > s.length()) || nextOperand.equals("+") || nextOperand.equals("-")) {
                        // 直接干
                        v1 = Integer.valueOf(stack.pop());
                        v2 = (int) s.charAt(++i) - 48;
                        stack.push(String.valueOf(nextOperand.equals("+") ? v1 + v2 : v1 - v2));

                    } else {
                        // 需要压进去
                        stack.push(x);
                    }
                }
            } else {
                stack.push(x);
            }
            i++;
        }

        while (!stack.isEmpty()) {
            String x = stack.pop();
            System.out.println(x);
            if (operands.contains(x)) {
                v1 = stack2.pop();
                v2 = Integer.valueOf(stack.pop());
                if (x.equals("+")) {
                    stack2.push(v1 + v2);
                } else {
                    stack2.push(v1 - v2);
                }
            } else {
                stack2.push(Integer.valueOf(x));
            }
        }

        return stack2.pop();
    }

    public static void operate(Stack<Integer> stack, String operand) {
        int v2 = stack.pop();
        int v1 = stack.pop();
        if (operand.equals("+")) {
            stack.push(v1 + v2);
        } else if (operand.equals("-")) {
            stack.push(v1 - v2);
        } else if (operand.equals("*")) {
            stack.push(v1 * v2);
        } else {
            stack.push(v1 / v2);
        }
    }

    public static int calculateAdvance(String s) {

        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operandStack = new Stack<>();
        s = s.replace(" ", "");
        // 从尾巴开始就无脑干, 不需要考虑+/-后面那个操作符是不是* / 尾巴不行尾巴可以！
        int d = 0;
        // 读取的时候应该写个while读到不是数字为止
        // 这种要涉及到前后数字的很麻烦，主要是还不止一位是数，就更麻烦了 宣告自己的计划破产
        // 可以可以 直接可以读 md
        String formerSign = "+";
        String curSign;
        // 判断的时机尤其重要 只有数字的时候不push 要配合两个符号一起push 没了老兄 已经找到抓数字的办法了。
        //
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) < '0' || i == s.length() - 1) {
                curSign = String.valueOf(s.charAt(i));
                operate(numStack, formerSign);
                formerSign = curSign;
            } else {
                // 在条件里的i++是没有意义的，只有++i才行，i++只能加在循环最后一行要用到i的地方
                while (i >= 0 && s.charAt(i) >= '0') {
                    d = d + (s.charAt(i--) - '0') * 10;
                }
                i--;
                numStack.push(d);
            }
            d = 0;
        }

        int res = 0;
        while (!numStack.isEmpty()) {
            res += numStack.pop();
        }
        return res;

    }

    public static void main(String[] args) {
        System.out.println(calculateAdvance("3+2*2"));
    }

}
