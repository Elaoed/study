package org.example.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class TreeHelper {

    public static TreeNode makeTree() {
        TreeNode root = new TreeNode(10);
        TreeNode left1 = new TreeNode(8);
        root.setLeft(left1);
        TreeNode right1 = new TreeNode(6);
        root.setRight(right1);
        TreeNode left2 = new TreeNode(4);
        left1.setLeft(left2);
        TreeNode left3 = new TreeNode(2);
        left2.setLeft(left3);
        return root;
    }

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
                father.setLeft(child);
            } else {
                father.setRight(child);
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
            System.out.println(node.getVal());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            } else {
                queue.add(null);
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            } else {
                queue.add(node.getRight());
            }
        }

    }

}
