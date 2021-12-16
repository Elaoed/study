package org.example.algorithm.middle;

/**
 * 题目里面给出的第三个构造函数太迷惑人了，第一个和第三个构造函数都用不到
 * 其实一共就三点
 * 1. 一个循环把两个node节点里面的值相加考虑进位后数值-10，
 * 2. 下次相加要把进位也加上去，
 * 3. 循环完成之后还要考虑进位的问题，
 * 返回的是head，后续列表的增加需要set tail的next，所以需要两个变量
 */
public class AddTwoNumbers {

    public static void main(String[] args) {;
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        int val = 0;
        int carry = 0;
        ListNode head = new ListNode(-1);
        ListNode tail = new ListNode(-1);

        while (l1 != null || l2 != null) {
            val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = val >= 10 ? 1 : 0;
            val = carry == 1 ? val - 10 : val;
            if (head.val == -1) {
                head = new ListNode(val);
                tail = head;
            } else {
                tail.next = new ListNode(val);
                tail = tail.next;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (carry == 1) {
            tail.next = new ListNode(1);
        }

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
//        return o;
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
