package org.example.algorithm;

public class ListHelper {

    public static ListNode makeList(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        ListNode tail = new ListNode(nums[nums.length - 1]);
        if (nums.length == 1) {
            return tail;
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            tail = new ListNode(nums[i], tail);
        }

        printList(tail);

        return tail;

    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
    }

    // 如果列表是单数会返回中间那个节点，如果是双数会返回后面那个节点，用的时候双数的要注意
    public static ListNode getMiddleNode(ListNode head) {
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

}
