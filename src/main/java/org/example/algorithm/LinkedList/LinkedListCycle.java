package org.example.algorithm.LinkedList;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;

/**
 * 141. Linked List Cycle
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * 这道题的意思我没搞懂 特别是最后那个pos
 * 但是解说说这里通常有两种解决方案
 * 1. 标志法: 就是节点走过了就标记一下，需要一个额外的标记位置，根据题目看是否能给到额外的标记位，如果不能的话就用HashSet来辅助
 *      时间复杂度O(n), 空间复杂度O(n)，最坏的结果每个都需要遍历，每个都需要放到hashSet里面去
 * 2. 快慢指针法: 快慢指针步伐不一致，但是最终会走到同一个节点，这个看起来比较慢
 *      空间复杂度可以O(1) 但是时间复杂度把 真的不好说 我感觉最坏的情况要比O(n)要大，可能O1的时候快的绕圈子跑的更快 还是我错了
 * https://leetcode-cn.com/problems/linked-list-cycle/solution/huan-xing-lian-biao-by-leetcode-solution/
 * 没想到我被pos迷惑住了，这玩意儿没有任何的作用 👍🏻
 *
 * 想起来了 快慢指针 最终会走到一起, 走不到一起的话是用==null or next == null判空 发现尾部
 * 还可以用set的方法进行插入 空间复杂度为O(n)
 * 还可以用把走过的节点都set null的方式？ set一个特殊值的方式
 *
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;

        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = ListHelper.makeList(new int[]{});



    }
}
