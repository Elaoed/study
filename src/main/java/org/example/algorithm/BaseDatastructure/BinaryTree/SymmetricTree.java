package org.example.algorithm.BaseDatastructure.BinaryTree;


import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;

/**
 * 101. Symmetric Tree
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * 在原先的解法之上主要是判断 左左和右右保持相等并且都是对称？
 * 还是要审题，好好审题 对称的定义是什么 不是左右相等 而是左边的左边 = 右边的右边 && 左边的右边 = 右边的左边
 */
public class SymmetricTree {

    public static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
    // 什么?
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
//        if (root.left == null || root.right == null) {
//            return false;
//        }
//        return isSymmetric(root.left) && isSymmetric(root.right);
        return isSymmetric(root.left, root.right);
    }


    public static void main(String[] args) {
        System.out.println(isSymmetric(TreeHelper.makeTree(1, 2, 3)));
    }

}
