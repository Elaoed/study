package org.example.algorithm.BaseDatastructure.BinarySearchTree;


import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 230
 */
public class KthSmallestElementInABST {

    public static PriorityQueue<Integer> heap = new PriorityQueue<>();
    public static List<Integer> col = new ArrayList<>();

    public static void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        // 从小到大
        dfs(root.left);
        col.add(root.val);
        dfs(root.right);
    }

    public static int kthSmallest(TreeNode root, int k) {
        dfs(root);
        return col.get(k - 1);
    }

    public static int kthSmallestV2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        // 替代了 参数校验 而不是真正里面的root
        while (root != null || !stack.isEmpty()) {
            // 中序遍历先把所有的left都加进去 我就说按照root = root.left的方式 先放进去的应该后出
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.println(stack);
            System.out.println(root);
            if (--k == 0) {
                return root.val;
            }
            // 妙啊
            root = root.right;

        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(kthSmallestV2(TreeHelper.makeTree(3, 1, 4, null, 2), 1));
    }

}
