package org.example.algorithm.BaseDatastructure.BinaryTree;

import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. Delete Nodes And Return Forest
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest. // 妈的要返回很多的跟节点
 * You may return the result in any order.
 * 二叉树的前序遍历，中序遍历，后序遍历 这个序指的是根的序
 * 前序是根前后，中序是前根后，后序是前后根
 * <p>
 * 为什么看完出入参之后 和之前的感觉完全不一样
 * 1. 如果根节点不需要被移除 则加入节点列表
 * 2. 如果某个节点需要被移除，则把他的左右节点都加入节点列表(假如不为null且不为叶子节点)
 * 3. 整个流程从根节点开始走
 * // 怎么样把这个大框很好的整理出来 判断当前节点要不要加入返回列表 是哪一边的活儿？
 * 应该是判断两个子节点要不要加入返回列表是递归的活儿
 * 二叉树的删除应该是要带有父子关系断绝
 * ====================================== 别人的题解
 * 1. 后续遍历删除子节点，再删除根节点 呜呜呜呜 昨天晚上看了白看了
 * 2. Set容器存值方便查找，前提是每个val都是唯一的
 * 3. 如果被删节点不是叶子节点，则看他的左孩子和右孩子，把不为空的放进数组，且没被删除
 * 4. 如果处理完后root不为空，要把root节点保存到结果数组里
 */
public class DeleteNodesAndReturnForest {

    // 一定是有父节点的节点
    // 父亲是不是被删掉了至关重要，可以通过参数传下去 当前节点需不需要被加入返回值 就是看父亲有没有删掉和自己有没有被删掉
    // 断绝父子关系可以通过调用儿子返回来处理，可以省去两个参数 尽可能的简化代码
    public static void helper(TreeNode treeNode, Set<Integer> set, List<TreeNode> res, boolean fatherDeleted, TreeNode father, boolean left) {

        if (treeNode == null) {
            return;
        }

        if (set.contains(treeNode.val)) {
            if (left) {
                father.left = null;
            } else {
                father.right = null;
            }
            helper(treeNode.left, set, res, true, treeNode, true);
            helper(treeNode.right, set, res, true, treeNode, false);
            return;
        }

        if (fatherDeleted) {
            res.add(treeNode);
        }
        helper(treeNode.left, set, res, false, treeNode, true);
        helper(treeNode.right, set, res, false, treeNode, false);

    }

    // 简洁而又明了
    public static TreeNode dfs(TreeNode node, List<TreeNode> res, Set<Integer> set, boolean fatherDeleted) {
       if (node == null)  {
           return null;
       }
       boolean isDeleted = set.contains(node.val);
       if (fatherDeleted && !isDeleted) {
           res.add(node);
       }
       node.left = dfs(node.left, res, set, isDeleted);
       node.right = dfs(node.right, res, set, isDeleted);
       return isDeleted ? null : node;
    }

    public static List<TreeNode> delNode3(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int j : to_delete) {
            set.add(j);
        }
        dfs(root, res, set, true);
        return res;

    }

    // 改一改这个
    public static List<TreeNode> delNodes2(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int j : to_delete) {
            set.add(j);
        }
        process(root, res, set);
        if (root != null) {
            res.add(root);
        }
        // 如果root == null 是不是在process里面判断了
        return res;

    }

    private static TreeNode process(TreeNode node, List<TreeNode> res, Set<Integer> set) {
        if (node == null) {
            return null;
        }
        // 后序遍历
        TreeNode left = process(node.left, res, set);
        TreeNode right = process(node.right, res, set);
        node.left = left;
        node.right = right;

        boolean isDeleted = set.contains(node.val);
        // isDeleted and fatherDeleted
        // 才会addToRes
        return isDeleted ? null : node;

//        if (isDeleted) {
//            if (node.left != null) {
//                res.add(node.left);
//            }
//            if (node.right != null) {
//                res.add(node.right);
//            }
//            // 需要解除父子关系
//        }
        // 当前节点要是没删 是不是就不用做任何处理 yes
        // 死在这里了



    }

    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int j : to_delete) {
            set.add(j);
        }
        if (root == null) {
            return res;
        }
        if (set.contains(root.val)) {
            helper(root.left, set, res, true, root, true);
            helper(root.right, set, res, true, root, false);

        } else {
            res.add(root);
            // 因为儿子没法知道父亲的信息，所以需要在父亲这一侧把儿子的也处理掉
            // 把父亲的信息待下去
            helper(root.left, set, res, false, root, true);
            helper(root.right, set, res, false, root, false);
        }

        return res;
    }

    public static void main(String[] args) {
        List<TreeNode> treeNodes = delNodes(TreeHelper.makeTree(1, 2, 3, 4, 5, 6, 7), new int[]{3, 5});
        System.out.println(treeNodes.size());
        System.out.println(treeNodes);

    }


}
