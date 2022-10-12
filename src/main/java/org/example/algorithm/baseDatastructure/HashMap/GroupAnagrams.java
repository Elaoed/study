package org.example.algorithm.baseDatastructure.HashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 王幸阳: 一拍脑袋想出的解基本不是最优解
 * 49. Group Anagrams
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * 下次再进步一点点，等有想法了再来
 * 大体的步骤肯定是有个hashMap<identification, List<pos>>
 * Q: how to identify anagram
 * Sort And Compare, 这里sort用的这么随便的吗, 然后用hashMap这个真的很方便, 但是复杂度比较高 nklogk n是字符串的数量, k是字符串的长度
 * Count. 这个空间复杂度就很大 也有排序的，把字母出现的次数加字母a3b5作为hashMap的key
 */
public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            // 对单个字符串排序只能先转换成char[]
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!hashMap.containsKey(key)) {
                hashMap.put(key, new ArrayList<>());
            }
            hashMap.get(key).add(str);
        }
        return new ArrayList<>(hashMap.values());
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

    }

}
