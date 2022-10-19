package org.example.algorithm.BaseDatastructure.BinaryTree;

import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;

public class BalancedBinaryTree {

    public static int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(helper(root.left), helper(root.right));

    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(helper(root.left) - helper(root.right)) <= 1;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeHelper.makeTree(1, 2, 2, 3, null, null, 3, 4, null, null, 4);
        System.out.println(isBalanced(treeNode));

    }

}
