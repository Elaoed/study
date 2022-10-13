package org.example.algorithm.BaseDatastructure.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 895. Maximum Frequency Stack
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 * Implement the FreqStack class:
 * FreqStack() constructs an empty frequency stack.
 * void push(int val) pushes an integer val onto the top of the stack.
 * int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 * 按照这个表现，先取多的，一样多的里面取靠近栈的 应该有以下几个重要的特性
 * 1. 需要有数字的记录，HashMap counter
 * 2. 需要排序, PriorityQueue
 * 3. 需要靠近栈, stack
 *
 */
public class MaximumFrequencyStack {

    int[] counter;
    private PriorityQueue<Integer> heap;
    private Stack<Integer> stackStore;
    private Stack<Integer> stackTmp;

    public MaximumFrequencyStack() {
//        hashMap = new HashMap<>();
        counter = new int[10];
        Arrays.fill(counter, 0);
        // 最大堆
        heap = new PriorityQueue<>((o1, o2) -> counter[o2] - counter[o1]);
        stackStore = new Stack<>();
        stackTmp = new Stack<>();
    }

    public void push(int val) {
        // 在计数的时候要防止heap重复放
        // 顺序是致命的
        counter[val] += 1;

        // 还有就是每次计数更新了之后要更新heap中的顺序
        heap.remove(val);
        heap.add(val);

        stackStore.push(val);
    }

    public int pop() {
        List<Integer> tmpList = new ArrayList<>();
        int max = counter[heap.peek()];
        // 把N个当前最大的数都丢到tmp里面去
        while (heap.size() > 0 && counter[heap.peek()] == max) {
            tmpList.add(heap.poll());
        }
        // if tempList.size = 1; 还是要收拾一堆乱七八糟的东西，还不如一起了

        int stackVal = stackStore.pop();
        while (true) {
            if (tmpList.contains(stackVal)) {
                while (!stackTmp.isEmpty()) {
                    stackStore.push(stackTmp.pop());
                }
                counter[stackVal] -= 1;
                // if (counter[integer] > 0) {
                // } // 放回去也是0 没事的
                heap.addAll(tmpList);
                // 把其他的都放回去
                break;
            }
            stackTmp.push(stackVal);
            stackVal = stackStore.pop();
        }

        return stackVal;


    }

    public static void main(String[] args) {

        MaximumFrequencyStack freqStack = new MaximumFrequencyStack();
        freqStack.push(4);
        freqStack.push(0);
        freqStack.push(9);
        freqStack.push(3);
        freqStack.push(4);
        freqStack.push(2);
        System.out.println(freqStack.pop());
        freqStack.push(6);
        System.out.println(freqStack.pop());
        freqStack.push(1);
        System.out.println(freqStack.pop());
        freqStack.push(1);
        System.out.println(freqStack.pop());
        freqStack.push(4);
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());

    }


}
