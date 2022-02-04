package org.example.algorithm.LinkedList;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;

/**
 * 328. Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 * The first node is considered odd, and the second node is even, and so on.
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 * <p>
 * 这么一个题里面得搞清楚几个变量
 * 1. 传进来的head
 * 2. 遍历下去的curr
 * 3. oddHead就是head
 * 4. oddTail因为后面要接上evenHead 为了往后接
 * 5. evenHead最好用dummy.next的方式表示
 * 6. evenTail 为了往后接
 * 7. 从链表里面把even摘除到even链表的时候odd记得要把next的指针指向下一个odd
 * <p>
 * version2:
 */
public class OddEvenLinkedList {

    // 别忘记 除了next要往下指之外，自己也要往下指呀
    public static ListNode version2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddHead = head;
        ListNode evenHead = head.next;
        ListNode evenDummyHead = new ListNode(0, evenHead);
        while (oddHead.next != null && evenHead.next != null) {
            oddHead.next = oddHead.next.next;
            oddHead = oddHead.next;
            evenHead.next = evenHead.next.next;
            evenHead = evenHead.next;
        }
        oddHead.next = evenDummyHead.next;
        return head;
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddTail = null;
        ListNode evenDummyHead = new ListNode(0);
        ListNode evenTail = evenDummyHead;
        ListNode curr = head;
        // curr must be odd 因为一次处理两步
        while (curr != null) {
            // curr = oddNode
            evenTail.next = curr.next;
            evenTail = evenTail.next;

            oddTail = curr;
            if (curr.next != null) {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
        oddTail.next = evenDummyHead.next;
        return head;
    }

    public static void main(String[] args) {
        ListHelper.printList(version2(ListHelper.makeList(2, 1, 3, 5, 6, 4, 7)));
        System.out.println(">>>>>>>>>>>>>>>");
        ListHelper.printList(version2(ListHelper.makeList(1, 2, 3, 4, 5)));
    }

}
