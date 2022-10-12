package org.example.algorithm.baseDatastructure.Stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time(LinkedList).
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * 所以这道题首先就可以用栈，其次难点在需要实现getMin方法并且是constantTime
 * 不仅仅是如此 如果按照原先我想的 只是用一个数来保存的话，把那个pop出去之后这个值会变得更小 从-2变成-1 我就没法玩儿了
 * Min() 这种constant就提示了很多东西，就跟我们一般的想法想的一样，正常要不断获取最小的，是不是得排序，排序的时间就肯定不是常量，所以不是排序。
 * 再看看条件。我们甚至不关心其他所有的数，也关心的，一个stack不够我们就看看再加一个能不能完成
 */
public class MinStack {

    Stack<Integer> xStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public MinStack() {
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
