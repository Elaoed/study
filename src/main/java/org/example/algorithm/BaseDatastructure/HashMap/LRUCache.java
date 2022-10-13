package org.example.algorithm.BaseDatastructure.HashMap;

import lombok.Data;

import java.util.HashMap;

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
 * LRU的核心数据结构就是LinkedHashMap，双向列表和哈希表的结合体
 * // 插入顺序和访问顺序的区别就是get的时候要不要makeRecentlyUsed
 * HashMap<key, Node>;
 * Node: key, value, prev, next;
 * // get: if exist, make key most recently
 * // put: if exist: update node value and make most recently, else if capacity excceed ? remove the last in list, and put it : put in map and list
 * 默写
 *
 * 打不过打不过，LinkedHashMap最近的也是在尾端，还是改一下吧，方便记忆 还算清晰
 *
 */
@Data
public class LRUCache {
    // capacity, get is fine, put the lru need a extra timestamp to identify, where to store?
    // the problem is in put with O(1) time complexity, and get to update to now.
    // There is no doubt that we could use HashMap to store the key val.

    public static class Node {
        public int key, val;
        public Node next, prev;
        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }
//
    private final HashMap<Integer, Node> cache;

    private Node head = null;

    // tail代表最近使用的 地位至高无上
    private Node tail = null;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
    }

    public void makeRecentlyUsed(Node node) {
        if (node != tail) {
            // 把node从上下文中解放出来
            removeInList(node);
            addLast(node);
        }
    }

    public void addLast(Node node) {

        // dummy -> node -> node.next 处理三者之间两对链接prev next的关系
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            Node last = tail;
            tail = node;
            last.next = node;
            node.prev = last;
        }
        // last -> node(tail);

    }

    public void removeInList(Node node) {

        Node prev = node.prev;
        Node next = node.next;
        // 清除引用关系 help gc
        node.prev = node.next = null;

        // node = head
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        // node = tail
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }

    }

    public void removeFirst() {
        removeInList(head);
    }

    public int get(int key) {

        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        makeRecentlyUsed(node);
        return node.val;

    }

    public void put(int key, int value) {

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = value;
            makeRecentlyUsed(node);
        } else {
            if (cache.size() >= capacity) {
                cache.remove(head.key);
                removeFirst();
            }
            Node node = new Node(key, value);
            cache.put(key, node);
            addLast(node);
        }

    }

    public static void main(String[] args) {
        final LRUCache cache2 = new LRUCache(2);
        cache2.put(1, 1);
        cache2.put(2, 2);
        System.out.println(cache2.get(1));

        cache2.put(3, 3);
        System.out.println(cache2.get(2));
        cache2.put(4, 4); // 2, 3, 4
        System.out.println(cache2.get(1));
        System.out.println(cache2.get(3));
        System.out.println(cache2.get(4));
        System.out.println(cache2);
        System.out.println(cache2.get(10));

    }

}
