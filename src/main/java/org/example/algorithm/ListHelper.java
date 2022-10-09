package org.example.algorithm;

import org.example.algorithm.TwoPointer.Pair;

/**
 * 链表常用的一些函数 直接封装 里面有些也是基础的内容 比如
 * 1. 链表的翻转
 * 2. 通过快慢指针获取链表的中间节点
 * 3. 如果有两个链表的话要考虑是不是可能会有链表的拼接 拼接也分两种，一种是物理拼接，new一个对象,拼进去 另外一种是逻辑拼接 一个链表到结尾了，把另外一个链表的头赋值过来继续
 * 4. 生成链表
 * 5. 打印链表
 */
public class ListHelper {

    public static ListNode makeList(int... nums) {

        if (nums.length == 0) {
            return null;
        }

        if (nums.length == 1) {
            return new ListNode(nums[0]);
        }

        ListNode tail = new ListNode(nums[nums.length - 1]);
        for (int i = nums.length - 2; i >= 0; i--) {
            tail = new ListNode(nums[i], tail);
        }

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

        // 举个例子 如果是1/2 -> 5/10 当10是null的时候只有九个元素，就选slow第五个，前四后五，如果10有值的话，就选第六个元素，总归后面的链条是要比前面的大的
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

    public static ListNode mergeSimple(ListNode l1, ListNode l2) {
        // 设置一个仿真头 然后把tail先指向这个仿真头 tail.next = first
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                tail.next = l1;
                tail = tail.next;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = tail.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }

        return dummy.next;
    }

    // 可以不用返回Pair了，直接返回head，反正是断了链子的，可以通过cur = cur.next的方式获取tail
    public static Pair<ListNode, ListNode> merge(ListNode l1, ListNode l2) {
        // 设置一个仿真头 然后把tail先指向这个仿真头 tail.next = first
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                tail.next = l1;
                tail = tail.next;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = tail.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }

        while (tail.next != null) {
            tail = tail.next;
        }
        return new Pair<>(dummy.next, tail);
    }

    /**
     * @return the head of rest
     * 满满的都是细节 记得要夹断
     * n就是要切下来几个
     */
    public static ListNode split(ListNode head, int n) {
        while (--n > 0 && head != null) {
            head = head.next;
        }

        ListNode rest = null;
        // 这里的判断容易忘记掉
        if (head != null) {
            rest = head.next;
            head.next = null;
        }

        return rest;
    }

    public static int getListLength(ListNode head) {

        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        return len;

    }

}
