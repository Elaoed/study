package org.example.algorithm.baseDatastructure.Stack;

import java.util.Stack;

/**
 * 1472. Design Browser History
 * You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.
 *
 * Implement the BrowserHistory class:
 *
 * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 * void visit(string url)Visits url from the current page. It clears up all the forward history.
 * string back(int steps)Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
 * string forward(int steps)Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
 *
 * 思路正确，这个应该属于简单把 还有用很长的列表实现的... 比较投机取巧
 * 数组也可以做，但是需要维护指针一个是当前的指针，一个是最大的指针，最大后面的数据不可访问
 *
 *
 */
public class DesignBrowserHistory {

    private Stack<String> forwardStack;
    private Stack<String> backStack;
    private String current;
    public DesignBrowserHistory(String homepage) {
        current = homepage;
        forwardStack = new Stack<>();
        backStack = new Stack<>();
        StringBuffer stringBuffer = new StringBuffer();


    }


    public void visit(String url) {
        backStack.push(current);
        current = url;
    }

    public String back(int steps) {
        while (steps-- > 0 && !backStack.isEmpty()) {
            forwardStack.push(current);
            current = backStack.pop();
        }
        return current;
    }

    public String forward(int steps) {
        while (steps-- > 0 && !forwardStack.isEmpty())  {
            backStack.push(current);
            current = forwardStack.pop();
        }
        return current;

    }

    public static void main(String[] args) {

    }

}
