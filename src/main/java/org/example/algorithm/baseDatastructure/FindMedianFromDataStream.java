package org.example.algorithm.baseDatastructure;

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

    private PriorityQueue<Integer> heap;
    public FindMedianFromDataStream() {
        heap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        heap.add(num);
    }

    public double findMedian() {
        int size = heap.size();
        // 3, 取第2个, 3 / 2 + 1
        // 4, 去第2个和第3个的mean
        int middle1 = 0;

        int n = size % 2 == 0 ? size / 2 : size / 2 + 1;
        while (n-- > 0) {
            middle1 = heap.poll();
        }
        int middle2 = middle1;
        if (size % 2 == 0) {
            middle2 = heap.poll();
        }
        return (double) (middle1 + middle2) / 2;

    }

    public static void main(String[] args) {

        FindMedianFromDataStream stream = new FindMedianFromDataStream();
        stream.addNum(1);
        stream.addNum(2);
        stream.addNum(3);
        stream.addNum(4);
        System.out.println(stream.findMedian());

    }
}
