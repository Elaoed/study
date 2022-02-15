package org.example.algorithm.baseDatastructure.BinaryTree;

import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;


/**
 *  对于二叉树这种题目就很容易 基本上都是 每一个节点有些什么样的考虑 从上还是从下
 */
public class BaseBinaryTree {

    // 除了递归别无他法
    // 104 Maximum Depth of Binary Tree (Easy)
    public static int getDepthCapacity(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        return Math.max(getDepthCapacity(tree.getLeft()) + 1, getDepthCapacity(tree.getRight()) + 1);
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
        int left = getMaximumDepth(treeNode.getLeft());
        int right = getMaximumDepth(treeNode.getRight());
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        //
//        return left - right;
        // 我要返回的是什么? 返回的是要未来拿去比较的，比较的一定是更深的那个层 所以返回left - right完全是错误的
        return 1 + Math.max(left, right);
    }


    public static void main(String[] args) {
        TreeNode root = TreeHelper.makeTree();
        System.out.println(getDepthCapacity(root));
        System.out.println(isBalancedTree(root));

    }
}
