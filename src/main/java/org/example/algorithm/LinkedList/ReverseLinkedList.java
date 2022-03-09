package org.example.algorithm.LinkedList;

import org.example.algorithm.ListNode;

/**
 * 206. Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseLinkedList {

    public static ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
        // 这种赋值来赋值去的用指针会稍微好看一点
        // 简单点 一个循环只有一步赋值，把curr.next = prev;
        // 然后移动两个指针 一个是prev = (prev.next 只是这个意思 这么写是行不通的) = curr 这一步必须要优先, 一个是curr = curr.next,

    }

    public static void main(String[] args) {

        int[] nums = new int[]{1, 2, 3, 4, 5};
        ListNode tail = new ListNode(nums[nums.length - 1]);

        for (int i = nums.length - 2; i >= 0; i--) {
            tail = new ListNode(nums[i], tail);
        }

        ListNode newHead = reverseList(tail);
        while (newHead != null) {
            System.out.println(newHead);
            newHead = newHead.next;
        }
    }

}

