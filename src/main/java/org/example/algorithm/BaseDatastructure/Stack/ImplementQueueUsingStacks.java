package org.example.algorithm.BaseDatastructure.Stack;

import java.util.Stack;

/**
 * 232. Implement Queue using Stacks
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 * Implement the MyQueue class:
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.
 * 注意细节就，不难，自己进行推演测试
 *
 */
public class ImplementQueueUsingStacks {

    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void push(int x) {
        s1.push(x);
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }

    public int pop() {
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        int x = s1.pop();
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        return x;    }

    public int peek() {
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        int x = s1.peek();
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        return x;    }

    public boolean empty() {
        return s2.isEmpty();
    }

}
