package org.example.algorithm.LinkedList;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;

/**
 * 876. Middle of the Linked List
 * Given the head of a singly linked list, return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 * 只要是链表的中间 一定要想到快慢指针
 */
public class MiddleOfTheLinkedList {

    public static void main(String[] args) {

        ListNode head = ListHelper.makeList(1, 2, 3, 4, 5, 6);
        ListNode resultHead = ListHelper.getMiddleNode(head);
        ListHelper.printList(resultHead);

    }
}
