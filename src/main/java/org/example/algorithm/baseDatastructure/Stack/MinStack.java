package org.example.algorithm.baseDatastructure.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time(LinkedList).
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * 所以这道题首先就可以用栈，其次难点在需要实现getMin方法并且是constantTime
 * 不仅仅是如此 如果按照原先我想的 只是用一个数来保存的话，把那个pop出去之后这个值会变得更小 从-2变成-1 我就没法玩儿了
 *
 */
public class MinStack {

    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
        // 为啥还需要push一个最大值
    }

    public void push(int val) {
        xStack.push(val);
        minStack.push(Math.min(minStack.peek(), val));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
        // 其他都可以 这个是怎么做到的？
    }

}
