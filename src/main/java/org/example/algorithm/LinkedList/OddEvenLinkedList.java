package org.example.algorithm.LinkedList;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;

/**
 * 328. Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 * The first node is considered odd, and the second node is even, and so on.
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 * <p>
 */
public class OddEvenLinkedList {

    // 别忘记 除了next要往下指之外，自己也要往下指呀
    public static ListNode version2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyOdd = new ListNode(0, head);
        ListNode dummyEven = new ListNode(0, head.next);
        ListNode oddCur = dummyOdd.next;
        ListNode evenCur = dummyEven.next;
        // 这里的判断条件还有点把握不好
        while (oddCur.next != null && evenCur.next != null) {
            oddCur.next = evenCur.next;
            oddCur = oddCur.next;
            evenCur.next = oddCur.next;
            evenCur = evenCur.next;
        }
        oddCur.next = dummyEven.next;
        return dummyOdd.next;
    }

    public static void main(String[] args) {
        ListHelper.printList(version2(ListHelper.makeList(2, 1, 3, 5, 6, 4, 7)));
        System.out.println(">>>>>>>>>>>>>>>");
        ListHelper.printList(version2(ListHelper.makeList(1, 2, 3, 4, 5)));
    }

}
