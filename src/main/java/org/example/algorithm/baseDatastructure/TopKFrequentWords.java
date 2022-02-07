package org.example.algorithm.baseDatastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 692. Top K Frequent Words
 * Given an array of strings words and an integer k, return the k most frequent strings.
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 */
public class TopKFrequentWords {

    // 强行一句话
    public static List<String> topKFrequent2(String[] words, int k) {

        return Arrays.stream(words)
                // 平时的groupingBy是怎么样的？
                .parallel()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
//                .sorted((o1, o2) -> {
//                    int res = o2.getValue().compareTo(o1.getValue());
//                    return res == 0 ? o1.getKey().compareTo(o2.getKey()) : res;
//                })
                .sorted(
                        Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue)
                        .reversed()
                        .thenComparing(Map.Entry::getKey)
                )
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }
    public static List<String> topKFrequent(String[] words, int k) {
        // 遇到top的问题先 new PriorityQueue

        Map<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            if (!counter.containsKey(word)) {
                counter.put(word, 0);
            }
            Integer count = counter.get(word);
            count++;
            counter.put(word, count);
        }

        // 默认不应该就是小堆吗
        PriorityQueue<String> heap = new PriorityQueue<>(k ,(o1, o2) -> {
            Integer res = counter.get(o2).compareTo(counter.get(o1));
            return res == 0 ? o1.compareTo(o2) : res;
        });
        heap.addAll(counter.keySet());

        List<String> res = new ArrayList<>(k);
        int n = 0;
        while (!heap.isEmpty() && n < k) {
            res.add(heap.poll());
            n++;
        }
        return res;

    }

    public static void main(String[] args) {
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        System.out.println(topKFrequent2(words, k));

    }
}
