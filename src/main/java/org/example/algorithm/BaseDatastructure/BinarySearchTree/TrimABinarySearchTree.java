package org.example.algorithm.BaseDatastructure.BinarySearchTree;

import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;

/**
 * 669. Trim a Binary Search Tree
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high,
 * trim the tree so that all its elements lies in [low, high].
 * Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant).
 * It can be proven that there is a unique answer.
 * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
 *
 */
public class TrimABinarySearchTree {

    /**
     * 中序遍历这棵树，如果到了low和high的交界处 把他们相邻的都处理掉
     * 相邻的有几种方案？
     * 1. 如果在左子树, 直接把左子树减掉(把父亲的左节点set null)
     * 2. 如果在右子树，用右子树替代父节点的位置
     * 3. 如果是父节点，往上走着看 不可能走到这一步 因为当父亲是儿子的时候已经被处理过了
     * 也没这样吧 其实很简单
     */
    public static TreeNode trimBST(TreeNode node, int low, int high) {

        // 不仅仅是这样 如果当前节点在范围之外 可能他的左右子树是在范围之内
        if (node == null) {
            return null;
        }

        // 如果当前节点(根节点)比low还要小，说明左侧全部都是要切除的
        if (node.val < low) {
            return trimBST(node.right, low, high);
        }

        // 说明右侧全部都是要切除的
        if (node.val > high) {
            return trimBST(node.left, low, high);
        }
        // 到这里root节点可以被保留 检查他的左右子树的修剪情况
        node.left = trimBST(node.left, low, high);
        node.right = trimBST(node.right, low, high);

        return node;

    }

    public static void main(String[] args) {
        TreeHelper.printTree(trimBST(TreeHelper.makeTree(1, 0, 2), 1, 2));

    }
}
