package org.example.algorithm.baseDatastructure.BinaryTree;

import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;

import static java.lang.Math.max;

/**
 * 543.Diameter of Binary Tree (Easy)
 * 求一个二叉树的最长直径。直径的定义是二叉树上任意两节点之间的无向距离。
 * 可以被转义为 寻找两个最深度的之和
 * 除了static还可以把max传入
 * 除了求出最大的max和secondMax还可以用递归算出每个节点的diameter
 * diameter: 直径
 */
public class DiameterOfBinaryTree {

    static int firstMax = 0;
    static int secondMax = 0;

    // Helper永远都是来计算当前节点的最大深度 只是根据不同的逻辑可以在返回之前处理
    public static int helper(TreeNode node) {
        // 永恒不变的爱
        if (node == null) {
            return 0;
        }
        int leftDepth = helper(node.left);
        int rightDepth = helper(node.right);
        firstMax = max(leftDepth + rightDepth + 1, firstMax);
//        if (leftDepth > max) {
//            secondMax = max;
//            max = leftDepth;
//        } else if (leftDepth > secondMax) {
//            secondMax = leftDepth;
//        }
//        if (rightDepth > max) {
//            secondMax = max;
//            max = rightDepth;
//        } else if (rightDepth > secondMax) {
//            secondMax = rightDepth;
//        }
        // 永恒不变的爱2
        return max(leftDepth, rightDepth) + 1;
    }

    public static int getDiameterOfBinaryTree(TreeNode root) {
        helper(root);
//        return firstMax + secondMax;
        return firstMax;
    }

    public static void main(String[] args) {

        TreeNode treeNode = TreeHelper.makeTree();
        System.out.println(getDiameterOfBinaryTree(treeNode));


    }

}
