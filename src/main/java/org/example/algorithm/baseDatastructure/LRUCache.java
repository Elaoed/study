package org.example.algorithm.baseDatastructure;

import lombok.Data;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * 146. LRU Cache
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict(驱逐) the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * 妈的，题解竟然用系统的外挂，那我sort还用系统自带的函数呢
 * dummyHead and dummyTail, 所以很多初始化的时候capacity要+2
 * 很多stack里面塞进去一个1就是为了不检查边界
 *
 */
@Data
public class LRUCache {
    // capacity, get is fine, put the lru need a extra timestamp to identify, where to store?
    // the problem is in put with O(1) time complexity, and get to update to now.
    // There is no doubt that we could use HashMap to store the key val.
    private final HashMap<Integer, Integer> cache;
    private final Deque<Integer> deque;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        deque = new LinkedList<>();
    }

    public int get(int key) {

//        cache.k
//
//         return node == null ? -1 : node.get();

        Integer value = cache.get(key);
        if (value == null) {
            return -1;
        }
        // update 把key对应的entry放到头部来或者放到尾部，我要好好看一下，丢掉那种无谓的把那个元素取出来放到头部或尾部把
        // LinkedList的put功能很好的帮你做掉了
        cache.remove(key);
        cache.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (cache.size() == capacity) {
            if (!cache.containsKey(key)) {
                Map.Entry<Integer, Integer> first = cache.entrySet().iterator().next();
                cache.remove(first.getKey());
            } else {
                cache.remove(key);
            }
            cache.put(key, value);
        } else {
            cache.remove(key);
            cache.put(key, value);
        }

    }

    public static void main(String[] args) {
//        LinkedHashMap<String, String> cache = new LinkedHashMap<>();
//        cache.put("1", "1");
//        cache.put("2", "2");
//        cache.put("3", "3");
//        System.out.println(cache);
//        cache.put("2", "2");
//        System.out.println(cache);
//        cache.remove("2");
//        cache.put("2", "2");
//        System.out.println(cache);

        final LRUCache cache2 = new LRUCache(3);
        cache2.put(1, 1);
        cache2.put(2, 2);
        cache2.put(3, 3);
        System.out.println(cache2);
        cache2.put(4, 4); // 2, 3, 4
        System.out.println(cache2);
        cache2.put(2, 2); // update 2, 2
        cache2.put(5, 5); // 4, 2, 5
        System.out.println(cache2);
        System.out.println(cache2.get(10));

    }

}
