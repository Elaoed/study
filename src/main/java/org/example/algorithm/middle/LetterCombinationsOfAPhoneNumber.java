package org.example.algorithm.middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

/**
 * 看似很简单 好像也真的不难，不是一个定的数量多少个循环，这种就需要走回溯和递归
 * // 第二种 n * n * n 方法第一次见 很适合做列出所有组合的题目
 */
public class LetterCombinationsOfAPhoneNumber {

    static Map<Character, String> content = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    static List<String> list = new ArrayList<>();
    static Queue<String> queue = new LinkedList<>();

    public static void version1(String digits, String combination) {
        // 空了
        if (Objects.equals(digits, "")) {
            list.add(combination);
            return;
        }
        String s = content.get(digits.charAt(0));
        for (int i = 0; i < s.length(); i++) {
            version1(digits.substring(1),  combination + s.charAt(i));
        }

    }

    public static List<String> letterCombinations(String digits) {
        if (Objects.equals(digits, ""))  {
            return new ArrayList<>();
        }
//        version1(digits, "");
        for (int i = 0; i < digits.length(); i++) {
            version2(content.get(digits.charAt(i)));
        }

        if (!queue.isEmpty()) {
            list.addAll(queue);
        }

        return list;
    }

    private static void version2(String letters) {
        //初始定义的queue一定是空的，所以这时候把第一个号码的字母放入队列
        if (queue.size() == 0) {
            for (int i = 0; i < letters.length(); i++) {
                queue.add(letters.charAt(i) + "");
            }
        } else {
            //对于后面到来字母，把queue出队列然后拼接以后进入队列
            int queueLength = queue.size(); //记录本次需要进行出列组合的元素数量
            for (int i = 0; i < queueLength; i++) {
                String s = queue.poll();    //队列头元素出队列
                for (int j = 0; j < letters.length(); j++) {
                    queue.add(s + letters.charAt(j));
                    // 呈枝叶一样的发散
                }
            }
        }
    }

    public static void main(String[] args) {

        String nums = "23";
        final List<String> strings = letterCombinations(nums);
        System.out.println(strings);

    }
}
