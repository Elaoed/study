package org.example.algorithm.BaseDatastructure.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 * // 双heap是因为前面poll出来的数据都得有地方放才行
 * // 还是我肤浅了，双heap是为了一个大根堆一个小根堆，大根堆里面放左半部分数据，poll就可以拿到最大值(中间值)，小根堆里面放右半部分数据，poll就可以拿到最小值(中间值) 在根据是奇数还是偶数 取不同的值
 *
 */
public class FindMedianFromDataStream {
    // 最起码我没想错，smallHeap里面放的是大数，bigHeap里面放的是小数，以smallHeap为优先，每次加入的时候动态的调整两个heap里面的元素
    private final PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
    private final PriorityQueue<Integer> bigHeap = new PriorityQueue<>(Comparator.comparingInt(a -> -a));
    public FindMedianFromDataStream() {
    }

    public void addNum(int num) {
        if (smallHeap.isEmpty() || num >= smallHeap.peek()) {
            smallHeap.offer(num);
        } else {
            bigHeap.offer(num);
        }
        // 大数堆太多了，就匀一个过去
        if (smallHeap.size() > bigHeap.size() + 1) {
            bigHeap.offer(smallHeap.poll());
        }
        if (bigHeap.size() > smallHeap.size()) {
            smallHeap.offer(bigHeap.poll());
        }
    }

    public double findMedian() {
        return (smallHeap.size() + bigHeap.size()) % 2 == 1 ? smallHeap.peek() : (double)(smallHeap.peek() + bigHeap.peek()) / 2;
    }

    public static void main(String[] args) {

//        FindMedianFromDataStream stream = new FindMedianFromDataStream();
//        stream.addNum(1);
//        stream.addNum(2);
//        stream.addNum(3);
//        stream.addNum(4);
//        System.out.println(stream.findMedian());
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append('1'));;

    }
}
