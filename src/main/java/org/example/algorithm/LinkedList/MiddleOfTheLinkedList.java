package org.example.algorithm.LinkedList;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;

/**
 * 876. Middle of the Linked List
 * Given the head of a singly linked list, return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 * 只要是链表的中间 一定要想到快慢指针
 * 写完这一题 去问王老师
 *
 */
public class MiddleOfTheLinkedList {

    public static ListNode middleNode(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return fast == null ? slow : slow.next;
    }

    public static void main(String[] args) {

//        int[] nums = new int[]{1, 2, 3, 4, 5};
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        ListNode resultHead = middleNode(ListHelper.makeList(nums));
        ListHelper.printList(resultHead);

    }
}
