package org.example.algorithm.baseDatastructure;

import org.example.algorithm.ListHelper;
import org.example.algorithm.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 * 这玩意儿还是一个hard
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * mergeList的套路还是那样，不过为了避免每次都要去扫描N个sort对比获取最小值
 * 用一个大小为N的PriorityQueue存所有的头结点并进行排序，应该是头结点 + list在数组array里面的序号
 * 然后pop 小顶堆之后找到对应的list, 把下一个值给塞进去，如果塞完了 要怎么取，k少一个就少一个，继续取heap里面的下一个值
 * // 慢慢的我开始考虑边界条件了 我很棒棒哦
 * // 算法的训练也是逼着自己考虑边界，同时这个考虑边界的习惯也会带到系统设计里面
 * 另外就是merge大法好，两两merge，这个只是普通合并
 * 还有一种是分治合并, 分治合并就是一次里面合两条，最终时间复杂度从O(n)变成了O(logn) 在顺序执行两两不相干的时候可以考虑这个做法 
 */
public class MergeKSortedLists {

    public static ListNode mergeKLists(ListNode[] lists) {

        // int之间的compare可以直接用相减来替代
        // 还是我naive了，有list的时候不需要index就可以知道是哪个队列，list的nextNode就行
        PriorityQueue<List<Object>> heap = new PriorityQueue<>(lists.length, Comparator.comparing(o -> ((ListNode) o.get(0)).val));
        for (int i = 0; i < lists.length; i++) {
            heap.add(Arrays.asList(lists[i], i));
            lists[i] = lists[i].next;
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode curr = dummyHead;
        while (!heap.isEmpty()) {
            List<Object> item = heap.poll();
            curr.next = (ListNode) item.get(0);
            curr = curr.next;
            int index = (int) item.get(1);
            ListNode listNode = lists[index];
            if (listNode != null) {
                heap.add(Arrays.asList(listNode, index));
                lists[index] = listNode.next;
            }
        }

        return dummyHead.next;

    }

    public static void main(String[] args) {
        ListHelper.printList(mergeKLists(new ListNode[]{ListHelper.makeList(1, 4, 5), ListHelper.makeList(1, 3, 4), ListHelper.makeList(2, 6)}));

    }

}
