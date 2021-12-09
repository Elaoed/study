package org.example.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 *  最长无重复字符串的长度 一定是一串字符串中连续的那一手
 *  所以可以通过滑动窗口的方式
 *  只要是滑动窗口 一定要有左右游标，和长度和最大长度四个元素
 *  还需要一个set
 *  注意什么时候加左游标，什么时候加右游标，还要注意空的输入
 *  最后模拟测试数据的时候一定要模拟一个基本的和特殊的，不要只模拟一个！
 */
public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {

        if (s == null || "".equals(s)) {
            return 0;
        }

        int cursorRight;
        int cursorLeft = 0;
        int longestLength = 0;
        int length = 0;
        Set<String> hashSet = new HashSet<>();
        for (cursorRight = 0; cursorRight < s.length(); cursorRight ++) {
            String newChar = s.substring(cursorRight, cursorRight + 1);
            if (" ".equals(newChar)) {
                continue;
            }
            boolean inserted = hashSet.add(newChar);
            if (inserted) {
                length += 1;
            } else {
                // 如果有了，要把从最左边到对应这个新字符的地方都删掉
                if (length > longestLength) {
                    longestLength = length;
                }

                String leftChar = s.substring(cursorLeft, cursorLeft + 1);
                while (!leftChar.equals(newChar)) {
                    hashSet.remove(leftChar);
                    length -= 1;
                    cursorLeft += 1;
                    leftChar = s.substring(cursorLeft, cursorLeft + 1);
                }
            }
        }
        return longestLength;

    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        s = "  ";
        System.out.println(LengthOfLongestSubstring.lengthOfLongestSubstring(s));
//        if (s == null || s == "") {
//            System.out.println(0);
//            return;
//        }
//        int cursorRight;
//        int cursorLeft = 0;
//        int longestLength = 0;
//        int length = 0;
//        Set<String> hashSet = new HashSet<>();
//        for (cursorRight = 0; cursorRight < s.length(); cursorRight ++) {
//            String newChar = s.substring(cursorRight, cursorRight + 1);
//            if (" ".equals(newChar)) {
//                continue;
//            }
//
//            boolean inserted = hashSet.add(newChar);
//            if (inserted) {
//                length += 1;
//            } else {
//                // 如果有了，要把从最左边到对应这个新字符的地方都删掉
//                if (length > longestLength) {
//                    longestLength = length;
//                }
//
//                String leftChar = s.substring(cursorLeft, cursorLeft + 1);
//                while (!leftChar.equals(newChar)) {
//                    hashSet.remove(leftChar);
//                    length -= 1;
//                    cursorLeft += 1;
//                    leftChar = s.substring(cursorLeft, cursorLeft + 1);
//                }
//            }
//        }
//
//        System.out.println("longest: " + longestLength);
    }
}
