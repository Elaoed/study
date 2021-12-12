package org.example.algorithm.easy;

import java.util.HashSet;

/**
 * 这道题的意思我没搞懂 特别是最后那个pos
 * 但是解说说这里通常有两种解决方案
 * 1. 标志法: 就是节点走过了就标记一下，需要一个额外的标记位置，根据题目看是否能给到额外的标记位，如果不能的话就用HashSet来辅助
 *      时间复杂度O(n), 空间复杂度O(n)，最坏的结果每个都需要遍历，每个都需要放到hashSet里面去
 * 2. 快慢指针法: 快慢指针步伐不一致，但是最终会走到同一个节点，这个看起来比较慢
 *      空间复杂度可以O(1) 但是时间复杂度把 真的不好说 我感觉最坏的情况要比O(n)要大，可能O1的时候快的绕圈子跑的更快 还是我错了
 * 先看下去再说
 * 那这个pos的用处？
 * https://leetcode-cn.com/problems/linked-list-cycle/solution/huan-xing-lian-biao-by-leetcode-solution/
 * 没想到我被pos迷惑住了，这玩意儿没有任何的作用 👍🏻
 *
 */
public class LinkedListCycle {

    public static class ListNode {

        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
            next = null;
        }

    }


    public static boolean hasCycle2(ListNode head) {
        ListNode slowP = head;
        ListNode fastP = head.next;
        while (fastP != null && fastP.next != null) {
            if (slowP == fastP) {
                return true;
            }
            fastP = fastP.next.next;
            slowP = slowP.next;
        }
        return false;
    }

    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> hashSet = new HashSet<>();
        while (head != null) {
            boolean inserted = hashSet.add(head);
            if (!inserted) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = head.next;
        boolean has = hasCycle2(head);
        System.out.println("hasCycle: " + has);
    }
}
