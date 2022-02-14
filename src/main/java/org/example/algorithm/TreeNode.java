package org.example.algorithm;

import lombok.Data;

@Data
public class TreeNode {

    private int val;

    private TreeNode left;

    private TreeNode right;

    public TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }
}
