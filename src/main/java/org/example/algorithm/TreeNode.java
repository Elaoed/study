package org.example.algorithm;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }

}
