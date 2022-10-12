package org.example.algorithm.easy;

import java.util.Arrays;

/**
 * 455
 * 这个好像很简单啊 从大到小排序 从大的开始喂
 */
public class AssignCookies {

    public static int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int k = s.length - 1;
        // gi胃口 sk饼干 想明白一个问题，如果饼干比胃口要大，ok，两个牵手成功，找下一家，如果饼干比胃口要小，这个人不得行，换下一个
        for (int i = g.length - 1; i>=0; i --){
            if (k >= 0 && s[k] >= g[i]) {
                res++;
                k--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] g = new int[]{1, 2};
        int[] s = new int[]{1, 2, 3};
        int contentChildren = findContentChildren(g, s);
        System.out.println(contentChildren);
    }
}
