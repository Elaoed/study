package org.example.algorithm;

/**
 * 链表常用的一些函数 直接封装 里面有些也是基础的内容 比如
 * 1. 链表的翻转
 * 2. 通过快慢指针获取链表的中间节点
 * 3. 如果有两个链表的话要考虑是不是可能会有链表的拼接 拼接也分两种，一种是物理拼接，new一个对象,拼进去 另外一种是逻辑拼接 一个链表到结尾了，把另外一个链表的头赋值过来继续
 */
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

//        printList(tail);

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

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

//        return curr; // return null
        return prev; // lastNode

    }
}
