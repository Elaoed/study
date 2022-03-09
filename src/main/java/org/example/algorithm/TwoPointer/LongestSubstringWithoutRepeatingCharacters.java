package org.example.algorithm.TwoPointer;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 * // 76 是他的进阶版
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
public class LongestSubstringWithoutRepeatingCharacters {

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

        int left = -1;
        int longestLength = 0;
        // 光跳没有用，跳梁小丑，还是需要remove，不然就跟我刚刚想的一样，数据会脏, 来了来了我来了，可以用比下标大小的方式驱逐劣币
        for (int right = 0; right < s.length(); right++) {
            char newChar = s.charAt(right);
            final boolean exist = map.containsKey(newChar);
            // 如果新的已经存在了，触发了左指针的运动条件，从while到这一段的简化是这个题目独有的
            // 本来还考虑说shrink的时候要把该删的都删掉 但是用了这个Math.max的时候就不用了独有的优化，基础班的我再写一遍
            if (exist) {
                left = Math.max(map.get(newChar), left);
            }
            map.put(newChar, right);
            longestLength = Math.max(right - left, longestLength);
        }

        return longestLength;

    }

    public static int version3(String s) {

        // 大概考虑下这道题的思考流程 用滑动窗口已经是板上钉钉的事情了，这道题最重要的限制条件就是substring里面不能有重复的字符串
        // substring就是滑动窗口，重复的字符串意味着右指针向右，遇到有重复的停下来可以用hashmap或者hashset
        // 左边开始shrink 停止的条件是到去掉这个重复的数位置，可能同时也会影响到别的数
        // a b c a / b c a a / b a c a -> 几种情况
        // 总觉得 shrink的过程中 中间该删的还是得删呀
        if (s == null || "".equals(s)) {
            return 0;
        }
//        HashMap<Character, Integer> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();

        int left = 0;
        int longestLength = 0;
        // 光跳没有用，跳梁小丑，还是需要remove，不然就跟我刚刚想的一样，数据会脏, 来了来了我来了，可以用比下标大小的方式驱逐劣币
        for (int right = 0; right < s.length(); right++) {
            Character newChar = s.charAt(right);
            if (set.contains(newChar)) {
                // 两个条件 -> 合并起来就是一个条件 TODO: 这一步是最重要的，自己平时还会去想 什么hashMap里面的元素要符合规则为止，当然最初级是要符合规则，这里可以优化成变成一样的
                // left 最后的位置一定是 相同的元素 + 1
                // 好好画图 兄弟 shrink 一下
                while (s.charAt(left) != newChar) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.remove(s.charAt(left));
                left += 1;

            }
            set.add(newChar);
            longestLength = Math.max(longestLength, right - left + 1);
        }

        return longestLength;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
//        s = "pwwkew";
        System.out.println(version3(s));
//        System.out.println(version2(s));
    }
}
