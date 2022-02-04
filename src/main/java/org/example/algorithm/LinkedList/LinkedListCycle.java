package org.example.algorithm.LinkedList;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;

/**
 * 141. Linked List Cycle
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * 想起来了 快慢指针 最终会走到一起, 走不到一起的话是用==null or next == null判空 发现尾部
 * 还可以用set的方法进行插入 空间复杂度为O(n)
 * 还可以用把走过的节点都set null的方式？ set一个特殊值的方式
 *
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
