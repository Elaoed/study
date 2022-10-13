package org.example.algorithm.BaseDatastructure.Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 我做过 我做过
 * 是一个栈模式 先进后出
 * 细节stack判空 不要只走正常模式 Ok? !!!!!
 * 说实话也没那么省，谁管你是if还是hashmap, 工作中只用if的别人会认为你是傻帽
 * Deque<Character> stack = new LinkedList<Character>();
 * LinkedList和Deque都是Stack And LinkedList extends from Deque
 * 提前判断，这个我一直做的还不错，这次没有，像这种配对的题目就应该先判断单双
 */
public class ValidParentheses {

    public static boolean isValid(String s) {
        // optimize
        if (s.length() % 2 == 1) {
            return false;
        }

        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char newChar = s.charAt(i);
            if (newChar == '{' || newChar == '[' || newChar == '(') {
                stack.push(newChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                // 本来应该再加一些判断，懒了，要看题目有没有说明
//                if (map.get(stack.pop()) == null) {
//                    return false;
//                }

                if (!map.get(stack.pop()).equals(newChar)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        String s = "{[]}";
        System.out.println(isValid(s));
    }
}
