package org.example.algorithm.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 妙啊 这个手册，优先队列和堆排序(最大堆、最小堆)一看就懂了，
 * 堆是一个完全二叉树也就是其每个节点的值总是大于等于子节点的值，
 * 完全二叉树和满二叉树又有区别
 * 如果编号为i（1≤i≤n）的结点与满二叉树中编号为i的结点在二叉树中的位置相同, 则为完全二叉树
 */
public class PriorityQueueRen {

    /**
     * 因为用了完全二叉树的特性，所以在找寻对应的位置的时候时间复杂度为O(logn), sink and swim
     * ArrayList的特性的话也是尾部的增删可以做到O(1), 所以之前不考虑其中顺序的就可以找到要删除的值和尾部的互换，然后删除
     * 这里也是，只不过删除了之后需要上浮交换排序还需要O(logn)的时间
     */
    private List<Integer> heap = new ArrayList<>();

    /**
     * 插入任意值
     *
     * @param k
     */
    void push(int k) {

    }

    /**
     * 删除最大值, 并获取？
     *
     * @param k
     */
    int pop(int k) {
        return 0;

    }

    /**
     * 获取最大值
     *
     * @param k
     * @return
     */
    int top(int k) {
        return 0;

    }

    // swim要找的父节点就一个，还算简单的
    void swim(int pos) {
        // 因为pos[0]是最大的，所以pos / 2应该是要比pos大，这也是跳出循环的条件
        while (pos > 1 && heap.get(pos / 2) < heap.get(pos)) {
            swap(pos / 2, pos);
            pos /= 2;
        }
    }

    // 重点是下沉，有两个子节点都比他小或者比他大的时候，他怎么知道要去哪一边
    // pos, 2 * pos, 2 * pos + 1 三者为一组一父二子, 下沉的时候是在父子之间交换
    void sink(int fatherPos) {
        // <= or < 这个size 应该是size - 1;
        int size = heap.size() - 1;
        // 那为什么 = size的也要走
        while (2 * fatherPos <= size) {
            // 先变成左儿子
            int childPos = 2 * fatherPos;
            // 如果满足这个条件就跟右儿子换，不满足就跟左儿子换,
            // 条件是有右儿子存在的情况下，并且左儿子比右儿子小才交换，这不是废话吗？？
            // 是我傻逼了废话了，这里只能保证爸爸比儿子大，不能保证左儿子比右儿子大
            // 然后在父子交换的过程中，可能交换完之后，左右儿子的大小完全变了
            // 为什么要和大的互换？
            if (childPos < size && heap.get(childPos) < heap.get(childPos + 1)) {
                childPos += 1; // 可以加一是因为右儿子才是最大的那个，没有超出限制 所以最大的右儿子 = size - 1

            }
            swap(fatherPos, childPos);
            fatherPos = childPos;
        }

    }

    void swap(int halfPos, int pos) {
        int tmp = heap.get(halfPos);
        heap.set(halfPos, heap.get(pos));
        heap.set(pos, tmp);
    }


}
