package org.example.algorithm.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 妙啊 这个手册，优先队列和堆排序(最大堆、最小堆)一看就懂了，堆是一个完全二叉树
 * 完全二叉树
 * 只允许最后一层有空缺结点且空缺在右边，即叶子结点只能在层次最大的两层上出现
 * 对任一结点，如果其右子树的深度为j，则其左子树的深度必为j或j+1。 即度为1的点只有1个或0个
 * 每个节点的值总是大于等于子节点的值，位置i的节点的父节点位置一定是i/2，而他的两个子节点的位置又一定是2i和2i+1
 * 完全二叉树可以直接使用一个数组来表示
 * 根就是index[0];
 *
 * 满二叉树
 * 把完全二叉树填满
 *
 * 二叉查找树
 * 对排序有要求，左根右以从大到小的顺序排列
 *
 * 队列FIFO特性，优先两大功能1. Priority可以插队，2. deleteMin删除最小者
 * 实现最常用的是二叉堆, 是一颗除了叶子节点剩下都被填满的二叉树 完全二叉树？
 * 小顶堆Min-Heap和大顶堆Max-Heap的区别，最想找出最小或者最大的节点，这个节点就在根上
 * https://juejin.cn/post/6844903826856607757
 * 那我们需要的就是大顶堆了
 * 如果只是插入排序的话，用二叉树的方式，最大时间复杂度也才O(logn), 应该是log小2大n
 * 首先需要知道这个东西要怎么用的，有哪几种添加和删除的操作，区别在哪里，然后要看源码 才算是学习嘛
 * Java默认是minHeap，既然Java默认是minHeap，那我就实现一个maxHeap吧
 */
public class MaxPriorityQueue {

   /**
     * 因为用了完全二叉树的特性，所以在找寻对应的位置的时候时间复杂度为O(logn), sink and swim
     * ArrayList的特性的话也是尾部的增删可以做到O(1), 所以之前不考虑其中顺序的就可以找到要删除的值和尾部的互换，然后删除
     * 这里也是，只不过删除了之后需要上浮交换排序还需要O(logn)的时间
     */
    private List<Integer> heap = new ArrayList<>();

    /**
     * 插入任意值: 把新的数字添加到末尾，然后上浮
     * @param k
     */
    void push(int k) {
        heap.add(k);
        swim(heap.size() - 1);
    }

    /**
     * 删除最大值, 病返回
     * 大顶堆的话就是删除最大的，因为最大值在第一个
     * 小顶堆的话就是删除最小的，因为最小值在第一个
     * 先把尾部的赋值到首部，pop尾部的，再对首部进行下沉
     * Java里面默认是小顶堆， 1，2的时候poll会拿出来1
     * @param k
     */
    int pop(int k) {
        int max = heap.get(0);
        heap.set(0, k);
        sink(0);
        return max;
    }

    /**
     * 获取最大值
     * @param k
     * @return
     */
    int top(int k) {
        return heap.get(0);
    }

    /**
     * 上浮, 在大顶堆中，把指定位置的节点上浮到合适的位置
     * swim要找的父节点就一个，还算简单的，盲猜可以通过二分法查找
     * @param pos
     */
    void swim(int pos) {
        // 因为pos[0]是最大的，所以pos / 2应该是要比pos大，这也是跳出循环的条件
        while (pos > 1 && heap.get(pos / 2) < heap.get(pos)) {
            // 如果父王威力不再(比自己要小)，占取父王的宝座
            swap(pos / 2, pos);
            pos /= 2;
        }
    }

    // 用在pop的时候，pop掉最大值，heap[0] = heap[last] 然后对heap[0]进行下沉
    // 重点是下沉，有两个子节点都比他小或者比他大的时候，他怎么知道要去哪一边? 当然是两个儿子之间对比啦，毕竟要继承王位，肯定要更强的那个才行
    // pos, 2 * pos, 2 * pos + 1 三者为一组一父二子, 下沉的时候是在父子之间交换
    void sink(int fatherPos) {
        // <= or < 这个size 应该是size - 1;
        int size = heap.size() - 1;
        // 那为什么 = size的也要走
        while (2 * fatherPos <= size) { // 起码可以下沉，下沉的图感觉有点像 往下面溜
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
