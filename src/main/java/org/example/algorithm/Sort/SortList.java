package org.example.algorithm.Sort;

/**
 * LeetCode 148
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        return null;
    }

    public static void main(String[] args) {
        int[] head = new int[]{-1, 5, 3, 4, 0};


    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }

}
