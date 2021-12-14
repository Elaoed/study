package org.example.algorithm.middle;

import java.util.ArrayList;
import java.util.List;

public class Test {

    // recursive 里面的内容不能影响上层的内容 不然就无解了
    public static void version1(List<String> result, StringBuilder builder, int open, int close) {
        // 应该是像一棵树一样开枝散叶 等左右括号都变成空了就返回 这也是一种 而不是像之前的
        // 搞清楚返回的是什么
        if (open == 0) {
            if (close == 0) {
                result.add(builder.toString());
//                builder.delete(0, builder.length());
            } else {
                version1(result, builder.append(")"), open, close - 1);
            }
            builder.deleteCharAt(builder.length() - 1);
            return;
        }

        // 如果左右括号一样了就只能加左括号
        if (open == close) {
            version1(result, builder.append("("), open - 1, close);
            builder.deleteCharAt(builder.length() - 1);
            return;
        }


        // 所以这块儿怎么处理？ 其实每个函数的open和close都不会被消耗，传到下一层的是形参
        version1(result, builder.append("("), open - 1, close);
        version1(result, builder.append(")"), open, close - 1);

    }

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        int n = 3;
        version1(result, new StringBuilder(), n, n);
        System.out.println(result);
    }
}
