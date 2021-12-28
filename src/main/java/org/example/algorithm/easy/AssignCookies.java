package org.example.algorithm.easy;

import java.util.Arrays;

/**
 * 这个好像很简单啊 从大到小排序 从大的开始喂
 */
public class AssignCookies {


    public static int findContentChildren(int[] g, int[] s) {

        int n = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int k = g.length - 1;
        for (int i = s.length - 1; i >= 0; i--) {
            while (k >= 0) {
                if (s[i] >= g[k]) {
                    n++;
                    k--;
                    break;
                }
                k--;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        int[] g = new int[]{1, 2};
        int[] s = new int[]{1, 2, 3};
        int contentChildren = findContentChildren(g, s);
        System.out.println(contentChildren);
    }
}
