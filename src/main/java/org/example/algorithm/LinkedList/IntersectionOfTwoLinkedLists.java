package org.example.algorithm.LinkedList;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;

/**
 * 160. Intersection of Two Linked Lists
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
 * Could you write a solution that runs in O(m + n) time and use only O(1) memory? 拼接链表的方法同时也满足了这个
 */
public class IntersectionOfTwoLinkedLists {

    /**
     * 这也是一种可行的方案 但是比较复杂
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode tailA = ListHelper.reverseList(headA);
        ListHelper.printList(tailA);
        ListNode tailB = ListHelper.reverseList(headB);
        ListHelper.printList(tailB);
        System.out.println("==============================");

        ListNode intersection = null;
        while (tailA != null && tailB != null && tailA.val == tailB.val) {
            intersection = tailA;
            tailA = tailA.next;
            tailB = tailB.next;
        }

        return intersection;
    }

    /**
     * 比较优的方案链表的拼接 不管两个链表原来的长短，拼接了之后一定等长
     * 这种题目要看那两个节点是不是真的是同一个节点 hash是一样的
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        ListNode A = headA;
        ListNode B = headB;
        while (A == null || B == null || (A.val != B.val)) {
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }
        return A;
    }

    public static void main(String[] args) {

        int[] nums1 = new int[]{4, 1, 8, 4, 5};
        int[] nums2 = new int[]{5, 6, 1, 8, 4, 5};
        ListNode head1 = ListHelper.makeList(nums1);
        ListNode head2 = ListHelper.makeList(nums2);

        ListNode intersectionNode = getIntersectionNode2(head1, head2);
        System.out.println(intersectionNode);

    }
}
