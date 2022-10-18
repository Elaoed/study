package org.example.algorithm.Sort;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;
import org.example.algorithm.TwoPointer.Pair;

import java.util.HashMap;

/**
 * LeetCode 148
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 * <p>
 * 1. split the list into two parts using fast/slow pointers. l1, l2 and set l1.next = null.
 * 2. merge(sort(l1), sort(l2))
 * 3. in merge function: initial newHead, tail
 * 4. while loop determined connect l1 or l2 into the tail and tail = tail.next;(这个很重要 之前就老忘)
 * <p>
 * 论仿真头的作用 是真好用
 * 无递归 空间复杂度为O1, 时间复杂度为两个list的长度和, 递归的空间复杂度也是O1啊
 * 虽然这一个方法里面空间复杂度是O1, 但是因为是递归 会有logN的方法也就会有OlogN的总空间复杂度, 怪不得是说栈的深度
 * 递归是属于top-down方法, 把递归改成迭代 bottom-up
 */
public class SortList {

//    public static int getIndex(ListNode head) {
//
//        int tmp = head.val;
//        ListNode tail = head;
//        while (tail.next != null) {
//            tail = tail.next;
//        }
//
//        while (head != tail) {
//            while (head != tail && tail.val > tmp) {
//
//            }
//        }
//
//
//    }

    public static ListNode quickSortVersion(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

//        int index = getIndex(head);
        // 待定吧宝 挡不住
//        ListNode rest = ListHelper.split(head, index - 1);

//        quickSortVersion(head);
//        quickSortVersion(rest.next);
        return head;

    }

    public static ListNode recursiveVersion(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode middleNode = ListHelper.getMiddleNode(head);
        // 断链条
        ListHelper.split(head, ListHelper.getListLength(head) / 2);

        ListNode left = recursiveVersion(head);
        ListNode right = recursiveVersion(middleNode);
        return ListHelper.merge(left, right).fst;

    }

    // 迭代归并的核心是step步长，步长每次翻倍，第一次是1、第二次是2代表的是需要被merge左右链表的长度
    public static ListNode iterationVersion(ListNode head) {

        // 常规校验
        if (head == null || head.next == null) {
            return head;
        }

        int len = ListHelper.getListLength(head);
        // 因为涉及到null.next所以搞个仿真头
        ListNode l, r, tail, dummy = new ListNode(0, head);
        // tail代表的是上一代的尾巴，后面应该接新一代的开头cur, 整一个复杂逻辑就是在处理上一代的尾巴和新一代的头的问题 = =
        // TODO: 牛啊 每次这个n要翻倍而不是加一
        for (int n = 1; n <= len; n <<= 1) {
            // cur 是当前要处理的列表的头, 每次都从头开始处理
            // 但是不对啊 每次这个head都会变化
            // 牛啊 论仿真头的作用, 仿真头就如同head节点，head.next永远都是第一个
            ListNode cur = dummy.next;
            tail = dummy;
            while (cur != null) {
                l = cur;
                r = ListHelper.split(l, n);
                cur = ListHelper.split(r, n); // cur是剩下的链表的head，最后一个大循环 cur = null
                tail.next = ListHelper.mergeSimple(l, r);
                // 把返回的head接到原先的tail后面，返回的tail成为新的tail，针对第一次第三次的小组排序的时候，要把新的head接到老的tail上面去
                // fst = l1 哦不一定，可能是l2 要看他们谁大谁小
                // 可以不用fst和snd, 返回都是
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
        }

        // 考虑三个要素 第一次 最后一次 和中间任意一次
        // 原先的head都没什么用, 一定是用最后一次排序过的head返回
        return dummy.next;
    }

    public static void main(String[] args) {
        // 默认是用一个O1来记录中间的位置 和中间值相比 从头或者从中间找 想法直接就错了
        // 首先用divide and conquer
        final ListNode head = ListHelper.makeList(-1, 5, 3, 4, 0);
        ListNode sortedHead = quickSortVersion(head);
        ListHelper.printList(sortedHead);
    }


}
