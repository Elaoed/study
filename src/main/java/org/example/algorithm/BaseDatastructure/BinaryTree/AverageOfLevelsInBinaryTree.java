package org.example.algorithm.BaseDatastructure.BinaryTree;

import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
 * Each Level Means given node and get siblings, but how
 * 放进队列，广度优先，记录上次循环的最终点
 */
public class AverageOfLevelsInBinaryTree {

    // 有两个数组，一个记录每一层的节点数，一个记录每一层的总数, 结果只要挨个除一下就行了, 这个dfs,bfs first search都一样，前中后序都一样
    public static List<Double> averageOfLevelsVersion2(TreeNode root) {
        return new ArrayList<>();

    }

    public static List<Double> averageOfLevels(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        List<Double> res = new ArrayList<>();
        list.add(root);
        int currLength = 1; // 可以在while循环里面嵌套一个for循环就行了
        long total = 0;
        int levelLength = 1;
        // 是不是需要两个计数器来记录 一个容易搞混, 不是容易搞混是根本记不了
        while (!list.isEmpty() && currLength-- > 0) {
            TreeNode node = list.pollFirst();
            total += node.val;
            if (node.left != null) {
                list.add(node.left);
            }
            if (node.right != null) {
                list.add(node.right);
            }
            // 这里也是需要注意
            if (currLength == 0) {
                res.add((double) total / levelLength);
                currLength = list.size();
                levelLength = list.size();
                total = 0;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(averageOfLevels(TreeHelper.makeTree(3, 9, 20, null, null, 15, 7)));


    }
}
