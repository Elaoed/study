package org.example.algorithm;

import java.util.HashMap;
import java.util.LinkedList;

public class TreeHelper {

    public static TreeNode makeTree() {
        TreeNode root = new TreeNode(10);
        TreeNode left1 = new TreeNode(8);
        root.left = left1;
        TreeNode right1 = new TreeNode(6);
        root.right = right1;
        TreeNode left2 = new TreeNode(4);
        left1.left = left2;
        TreeNode left3 = new TreeNode(2);
        left2.left = left3;
        return root;
    }

    // 有缺陷 这个只针对于一个堆可以用
    public static TreeNode makeTree(Integer... nodeList) {

        if (nodeList.length == 0) {
            return null;
        }

        HashMap<Integer, TreeNode> map = new HashMap<>();

        final TreeNode root = new TreeNode(nodeList[0]);
        map.put(0, root);
        for (int i = 1; i < nodeList.length; i++) {
            if (nodeList[i] == null) {
                continue;
            }
            TreeNode father = map.get(i % 2 == 0 ? (i - 2) / 2 : (i - 1) / 2);
            TreeNode child = new TreeNode(nodeList[i]);
            if (i % 2 == 1) {
                father.left = child;
            } else {
                father.right = child;
            }
            map.put(i, child);
        }
        return root;
    }

    public static void printTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return;
        }

        queue.push(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            if (node == null) {
                System.out.println("null");
                continue;
            }
            System.out.println(node.val);
            if (node.left != null) {
                queue.add(node.left);
            } else {
                queue.add(null);
            }
            if (node.right != null) {
                queue.add(node.right);
            } else {
                queue.add(node.right);
            }
        }

    }

    // 左右子节点都为null则为叶子节点
    public static boolean isLeaf(TreeNode node) {
        if (node == null) {
            return true;
        }
        return node.left == null && node.right == null;
    }

}
