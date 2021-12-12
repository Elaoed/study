package org.example.algorithm;

/**
 * 怎么这么简单一道题我还要做这么久的
 * 1. 选取最小的length
 * 2. 以第一个单词为标杆 遍历所有的单词，遇到有一个不等的或者跑完了就停
 * 就很简单 ohhehehehe
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {

        String[] strs = new String[]{"ab", "a"};
        int length = strs[0].length();
        for (String str : strs) {
            length = Math.min(length, str.length());
        }

        int i = 0;
        for (i = 0; i < length; i++) {
            for (int i1 = 1; i1 < strs.length; i1++) {
                if (strs[i1].charAt(i) != strs[0].charAt(i)) {
                    if (i == 0) {
                        System.out.println();
                    } else {
                        System.out.println(strs[0].substring(0, i));
                    }
                    return;
                }
            }
        }
        System.out.println(strs[0].substring(i, 1));

    }
}
