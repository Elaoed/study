package org.example.algorithm.baseDatastructure.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 767. Reorganize String
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * Return any possible rearrangement of s or return "" if not possible.
 */
public class ReorganizeString {

    public static String reorganizeString(String s) {

        // 26个字母的话可以用数组来代替 hashMap
        int[] counter = new int[26];
        Arrays.fill(counter, 0);
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a'] += 1;
        }

        // HashMap还是缺了点东西
        // 用Priority吧
        PriorityQueue<Character> heap = new PriorityQueue<>((o1, o2) -> counter[o2 - 'a'] - counter[o1 - 'a']);
        for (int i = 0; i < s.length(); i++) {
            if (!heap.contains(s.charAt(i))) {
                heap.add(s.charAt(i));
            }
        }
        // HashMap是所有O1, 而PriorityQueue是最大或者最小O1
        // 但是HashMap没排序，不能知道到底哪个最大最小，哎 heap就可以了
        Character mostChar = heap.poll();
        int pos = mostChar - 'a';
        int count = counter[pos];
        int totalOtherCount = Arrays.stream(counter).sum() - count;

        if (count - totalOtherCount > 1) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        int total = s.length();
        int round = count; // 每一轮的数量
        int initIndex = 0; // 每一轮初始化都会 + 1
        int step = 1; // 每一轮step都会 + 1
        int curIndex = initIndex;
        Character curCharacter = mostChar;
        while (total-- > 0) {
            if (round == 0) {
                round = count;
                initIndex += 1;
                step += 1;
                curIndex = initIndex;
            }
            if (counter[curCharacter - 'a'] == 0) {
                if (heap.size() == 0) {
                    break;
                }
                curCharacter = heap.poll();
            }
            builder.insert(curIndex, curCharacter);

            counter[curCharacter - 'a'] -= 1;
            round--;
            curIndex += step;
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));
//        System.out.println('a' + 3);
//        System.out.println((char) 'a' + 3);
//        System.out.println((char) 100);
//        final PriorityQueue<Integer> heap = new PriorityQueue<>();
//        heap.add(1);
//        heap.add(2);
//        System.out.println(heap.poll());
//        System.out.println(heap);

    }

}
