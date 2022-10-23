package org.example.algorithm.BaseDatastructure.BinarySearchTree;

import org.example.algorithm.TreeNode;

/**
 * 99. Recover Binary Search Tree
 * You are given the root of a binary search tree (BST),
 * where the values of exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 * 这道题我理解 是从跟节点开始 递归的寻找不满足二叉搜索树条件的两个节点 有且仅有两个节点
 * 二叉搜索树的条件是 左孩子要小于跟节点，右孩子要大于跟节点
 * 1. 如果这个节点只有一条边(正常有三条) 叶子节点 且这一条边出现问题了
 *
 * 二叉搜索树 + 中序遍历 = 有序数组, 当数组有两个地方无序的情况下 这两个就是我们要找的点
 * 当然还分好几种情况 当前后两个值交换之后，就会出现4个失序的数字和下标
 * 看满足的条件是看后面还是看前面
 * 如果满足的条件是以后面来判断的
 * 当有一个不满足条件的数字 则3不满足 要和后面位置进行交换
 * 当有两个不满足条件的数字 则4 3都不满足则拿4和3后面的位置进行交换
 * 如果以后面为准的话还要考虑 后续有没有东西
 * 好麻烦 抓不到本质
 *
 * 存储下来的数据结构是？
 *
 * // 1 2 3 4
 * // 1 3 2 4 一个降序对
 * // 4 2 3 1 两个降序对
 *
 */
public class RecoverBinarySearchTree {

    private TreeNode node1Father;
    private TreeNode node2Father;
    private int node1Direction;
    private int node2Direction;

    public void recoverTree(TreeNode root) {

        if (root.left == null && root.right == null) {
            return;
        }

        if (root.left == null && root.right.val > root.val) {
            // 这个root出问题了还是这个right出问题了？

        }

    }

    public static void main(String[] args) {

    }
}
