package org.example.algorithm.TwoPointer;

import org.example.algorithm.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://zhuanlan.zhihu.com/p/390570255
 */
public class SlideWindowTemplate {

    //
    public static ListNode template(ListNode head) {
        // 1. 定义变量，防止无法进入循环
        ListNode fast = head.next;
        ListNode slow = head;
        // 用来记录字母出现的频率，counter = new int[26];
        Map<String, Integer> counter = new HashMap<String, Integer>();
        // 记录符合题目要求最长的substring
        int longest = 0;

        // 看具体问题，有的条件是两者不能相遇
        while (fast != null && fast.next != null) {
            // 字母计数
            fast = fast.next.next;
            slow = slow.next;
        }
        // 1 2 3 4 5 6
        // fast为空说明是单数个, 这时候slow就是最中间的数;
        // fast不为空说明是偶数个，这是后slow是左半边列表的最后一个;
        return fast == null ? slow : slow.next;
    }
    public static void main(String[] args) {

    }
}
