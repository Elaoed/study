package org.example.algorithm.BaseDatastructure.BinaryTree;

import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;

import java.util.Stack;


/**
 *  对于二叉树这种题目就很容易 基本上都是 每一个节点有些什么样的考虑 从上还是从下
 */
public class BinarySearchTree {

    // 除了递归别无他法
    // 104 Maximum Depth of Binary Tree (Easy)
    public static int getDepthCapacity(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        return Math.max(getDepthCapacity(tree.left) + 1, getDepthCapacity(tree.right) + 1);
    }

    /**
     * 110. Balanced Binary Tree (Easy)
     * 二叉平衡树
     * 判断一个二叉树是否平衡。树平衡的定义是，对于树上的任意节点，其两侧节点的最大深度 的差值不得大于 1。1
     */
    // 一边是boolean 一边是数字 原先以为是递归isBalance 但凡是false就返回false
    // 但是发现返回boolean无法满足上层的要求 上层要求可以相减就必须要int
    // 所以拆开成两个函数比较合适
    public static boolean isBalancedTree(TreeNode root) {
        return getMaximumDepth(root) != -1;
    }

    public static int getMaximumDepth(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int left = getMaximumDepth(treeNode.left);
        int right = getMaximumDepth(treeNode.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        //
//        return left - right;
        // 我要返回的是什么? 返回的是要未来拿去比较的，比较的一定是更深的那个层 所以返回left - right完全是错误的
        return 1 + Math.max(left, right);
    }

    /**
     * 标准的递归版前序遍历，相当于隐式的维护了一个栈
     * @param root
     */
    public static void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);

    }

    // 还有中序 后序 层序，前三者都是深度优先，层序是广度优先 通过Queue来实现
    /**
     * 前序遍历的迭代版本，需要显示的维护一个栈
     * @param root
     */
    public static void preorderTraversalIterVersion(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        stack.push(root);
        while (!stack.isEmpty() || node == null) {
            while (node != null) {
                System.out.println(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
    }


    public static void main(String[] args) {
        TreeNode root = TreeHelper.makeTree();
        System.out.println(getDepthCapacity(root));
        System.out.println(isBalancedTree(root));
        System.out.println("================> preorder traversal recursive version");
//        root = TreeHelper.makeTree(1, null, 2, 3);
        // 10 8 6 4 null 2
        root = TreeHelper.makeTree();
        preorderTraversal(root);
        System.out.println("================> preorder traversal iteration version");
//        preorderTraversalIterVersion(root);

    }
}
