package org.example.algorithm.LinkedList;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;

/**
 * 206. Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseLinkedList {

    public static void main(String[] args) {

        ListNode head = ListHelper.makeList(1, 2, 3, 4, 5);
        ListNode newHead = ListHelper.reverseList(head);
        while (newHead != null) {
            System.out.println(newHead);
            newHead = newHead.next;
        }
    }

}

