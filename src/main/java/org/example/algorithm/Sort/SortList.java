package org.example.algorithm.Sort;

import com.sun.tools.javac.util.Pair;
import lombok.Data;

/**
 * LeetCode 148
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 * <p>
 * 1. split the list into two parts using fast/slow pointers. l1, l2 and set l1.next = null.
 * 2. merge(sort(l1), sort(l2))
 * 3. in merge function: initial newHead, tail
 * 4. while loop determined connect l1 or l2 into the tail and tail = tail.next;(这个很重要 之前就老忘)
 *
 * 论仿真头的作用
 */
public class SortList {

    // 无递归 空间复杂度为O1, 时间复杂度为两个list的长度和
    // 虽然这一个方法里面空间复杂度是O1, 但是因为是递归 会有logN的方法也就会有OlogN的总空间复杂度, 怪不得是说栈的深度
    // 递归是属于top-down方法, merge这个方法可以通用的
    public static Pair<ListNode, ListNode> merge(ListNode l1, ListNode l2) {
        // 头的初始化怎么做好一点?
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
     */
    public static ListNode split(ListNode head, int n) {
        while (--n > 0 && head != null) {
            head = head.next;
        }
        ListNode rest = null;
        if (head != null) {
            rest = head.next;
            head.next = null;
        }

        return rest;
    }

    // Ok version2: 把递归改成迭代 bottom to top
    public static ListNode version2(ListNode head) {

        // if there is only 0 or 1 element, we are done
        if (head == null || head.next == null) {
            return head;
        }

        // split 怎么去定N? 还是需要遍历一下这个列表
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        ListNode l, r, tail, dummy = new ListNode(0, head); // 因为涉及到null.next所以搞个仿真头
        // TODO: 牛啊 每次这个n要翻倍而不是加一
        for (int n = 1; n <= len; n <<= 1) {
            // cur 是当前要处理的列表的头, 每次都从头开始处理
            // 但是不对啊 每次这个head都会变化
            // 牛啊 论仿真头的作用
            cur = dummy.next;
            tail = dummy;
            while (cur != null) {
                l = cur;
                r = split(l, n);
                cur = split(r, n); // 最后一个大循环 cur = null
                Pair<ListNode, ListNode> merged = merge(l, r);
                tail.next = merged.fst;
                tail = merged.snd;
                // 把返回的head接到原先的tail后面，返回的tail成为新的tail
                // 先按照这种方案走 后面再用new的方案
            }
        }

        // 考虑三个要素 第一次 最后一次 和中间任意一次
        // 原先的head都没什么用, 一定是用最后一次排序过的head返回
        return dummy.next;
    }

    // Ok version1: 这个不满足题目要求
    public static ListNode version1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1. 快慢指针切割list
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode middle = slow.next;
        slow.next = null;
//        return merge(sortList(head), sortList(middle));
        return null;
    }

    public static void main(String[] args) {
        int[] list = new int[]{-1, 5, 3, 4, 0};
        // 默认是用一个O1来记录中间的位置 和中间值相比 从头或者从中间找 想法直接就错了
        // 首先用divide and conquer
        ListNode tail = new ListNode(list[list.length - 1]);
        for (int i = list.length - 2; i >= 0; i--) {
            tail = new ListNode(list[i], tail);
        }

        ListNode sortedHead = version2(tail);
        while (sortedHead != null) {
            System.out.println(sortedHead.getVal());
            sortedHead = sortedHead.next;
        }
    }

    @Data
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
