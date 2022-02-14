package org.example.algorithm.baseDatastructure.BinaryTree;

import org.example.algorithm.TreeNode;

public class BaseBinaryTree {

    // 除了递归别无他法
    public static int getDepthCapacity(TreeNode tree) {
        if (tree == null) {
            return 1;
        }
        return Math.max(getDepthCapacity(tree.getLeft()), getDepthCapacity(tree.getRight()));
    }

//    public static boolean isBalancedTree(TreeNode tree) {
//
//    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode left1 = new TreeNode(8);
        TreeNode right1 = new TreeNode(6);
        root.setLeft(left1);
        root.setRight(right1);
        TreeNode left2 = new TreeNode(4);
        left1.setLeft(left2);
        System.out.println(getDepthCapacity(root));

    }
}
