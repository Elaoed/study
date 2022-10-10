package org.example.algorithm.LinkedList;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;

/**
 * 92. Reverse Linked List II
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 * 这道题的核心在于四个点要记下来，遍历完中间那一段之后 会留下curr和prev, prev是revered之后的头, curr是右边那段的头
 * 还要考虑如果是全列表的情况下
 */
public class ReverseLinkedListII {

    public static ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        ListNode prev = null;

        ListNode newHead = null;
        ListNode newEndOfReveredList = null;
        int i = 1;
        while (i <= right) {
            ListNode next = curr.next;
            if (i == left) {
                newEndOfReveredList = curr;
            }
            if (i >= left) {
                curr.next = prev;
            } else {
                newHead = curr;
            }
            prev = curr;
            curr = next;
            i++;

        }
        if (newHead != null) { // 从最左侧开始翻转
            newHead.next = prev;
        }
        newEndOfReveredList.next = curr;
        return left > 1 ? head : prev;

    }

    public static void main(String[] args) {

        ListHelper.printList(ListHelper.reverseLinkAdvance(ListHelper.makeList(1, 2, 3, 4, 5), 2, 4));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> =======================");
//        ListHelper.printList(ListHelper.reverseLinkAdvance(ListHelper.makeList(5, 6), 1, 2));


    }

}
