package org.example.algorithm.middle;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 最长无重复字符串的长度 一定是一串字符串中连续的那一手
 * 所以可以通过滑动窗口的方式
 * 只要是滑动窗口 一定要有左右游标，和长度和最大长度四个元素
 * 还需要一个set
 * 注意什么时候加左游标，什么时候加右游标，还要注意空的输入
 * 最后模拟测试数据的时候一定要模拟一个基本的和特殊的，不要只模拟一个！
 * String.charAt(i)替代subString
 * // 善用Map,数组中用map替代查找, Map<Value, Index> 不得用, 还真得用，大神们牛啊，这是脏数据处理的问题 ，算法题里面基本都可以靠比较下标大小来判断老旧
 * // 还可以用数组替换Set/Map 反正里面本来就是Character
 */
public class LengthOfLongestSubstring {

    public static int version2(String s) {

        if (s == null || "".equals(s)) {
            return 0;
        }

        int cursorLeft = 0;
        int longestLength = 0;
        HashSet<Character> hashSet = new HashSet<>();
        for (int cursorRight = 0; cursorRight < s.length(); cursorRight++) {
            char newChar = s.charAt(cursorRight);
            if (!hashSet.add(newChar)) {
                // 如果有了，要把从最左边到对应这个新字符的地方都删掉
                char leftChar = s.charAt(cursorLeft + 1);
                while (leftChar != newChar) {
                    hashSet.remove(leftChar);
                    cursorLeft += 1;
                    leftChar = s.charAt(cursorLeft + 1);
                }
                // 这时他两相等了
                cursorLeft += 1;
            }
            // 成功了 do nothing;
            longestLength = Math.max(longestLength, cursorRight - cursorLeft);
        }
        return longestLength;

    }

    public static int version1(String s) {

        if (s == null || "".equals(s)) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();

        int cursorLeft = -1;
        int longestLength = 0;
        // 光跳没有用，跳梁小丑，还是需要remove，不然就跟我刚刚想的一样，数据会脏, 来了来了我来了，可以用比下标大小的方式驱逐劣币
        for (int cursorRight = 0; cursorRight < s.length(); cursorRight++) {
            char newChar = s.charAt(cursorRight);
            final boolean exist = map.containsKey(newChar);
            if (exist) {
                cursorLeft = Math.max(map.get(newChar), cursorLeft);
            }
            map.put(newChar, cursorRight);
            longestLength = Math.max(cursorRight - cursorLeft, longestLength);
        }

        return longestLength;

    }

    public static void main(String[] args) {
        String s = "aabaab!bb";
//        s = "pwwkew";
        System.out.println(version1(s));
//        System.out.println(version2(s));
    }
}
