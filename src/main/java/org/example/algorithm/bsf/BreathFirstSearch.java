package org.example.algorithm.bsf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 除了框架之外 还有双向BFS 通过三个Set来操作，快速判断两边是否有相等的数，每次扩散的结果存到第三个set中，然后s1 = s2;s2 = tmp;
public class BreathFirstSearch {

    private static Queue<Object> queue = new LinkedList<>();
    private static Set<Object> visited = new HashSet<>();
    public static void main(String[] args) {

        char[] a = new char[10];
        a[0] = '1';
        System.out.println(Arrays.toString(a));
        // queue.offer(root);
        int step = 0;
        while (!queue.isEmpty()) {
            int c = queue.size();
            while (--c >= 0) {
                Object node = queue.poll();
                // if node.val == target: return
                // if visited.contains(node): continue;
                // for 四面八方 if aspect != null: queue.offer(aspect);
            }
        }

    }

}
