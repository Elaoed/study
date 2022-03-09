package org.example.algorithm.baseDatastructure.BinaryTree;

import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;

import java.util.HashMap;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * HashMap 替代每次遍历找对应下标是空间换时间的方法
 * 这道题我算是犯了一个经常犯的很严重的问题
 * 就是演算只进行了一步，就跟测试的时候只传了全空和全满一样，没有测中间的case
 * 演算两步的时候就发现只有left和right是完全不够的
 * 这里最难的是画图
 * 最重要的是定几个节点，这道题节点里面最难定的也就是前序遍历左子树和右子树的分界，用给的example容易被带进去
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    // 有了他就不用传inOrder了
    private static HashMap<Integer, Integer> indexMap = new HashMap<>();

    public static TreeNode helper(int[] preorder, int preLeft, int preRight, int inLeft, int inRight) {
        // 什么情况下停下来? 总有需要返回null的
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        TreeNode newFather = new TreeNode(preorder[preLeft]);
        if (preLeft == preRight) {
            // 最终相遇的时候是取左边还是右边，引申出来，两个数组的分工
            // leftRight 还是要是preorder -> 因为preOrder的最左边是头 除了头之外 两个数组一段内的数字是一样的，只是顺序不同
            return newFather;
        }
        // 比如左边第一个 newFatherIndex = 0
        // 找到father在inorder列表中的位置
        // 这种找到下标的方式可以用hashMap替代
        Integer pIndex = indexMap.get(preorder[preLeft]);
        // 构建左边的树 以pre为蓝本构建 去掉了最左边的头left 剩下的这段是他左边的数
        //
        newFather.left = helper(preorder, preLeft + 1, pIndex - inLeft + preLeft, inLeft, pIndex - 1);
        // 构建右边的树 以pre为蓝本构建 i + 1 可能是会大于right的
        newFather.right = helper(preorder, pIndex - inLeft + preLeft + 1, pIndex - 1, pIndex + 1, inRight);

        return newFather;

    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return helper(preorder, 0, inorder.length - 1, 0, inorder.length - 1);
    }

    public static void main(String[] args) {
        TreeHelper.printTree(buildTree(new int[]{1, 2}, new int[]{1, 2}));
    }

}
