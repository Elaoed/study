package org.example.algorithm.BaseDatastructure.BinaryTree;

import org.example.algorithm.TreeHelper;
import org.example.algorithm.TreeNode;

/**
 * 437. Path Sum III (Easy)
 * 给定一个整数二叉树，求有多少条路径节点值的和等于给定值。
 * 从下到上是行不通的，只能从上到下，把自己这个端点的值给去掉，让下一层去求余数
 * 需要考虑这道题的组成成分
 * 当我在一个父节点的时候 有哪几种可能组成我的答案
 * 1. 父节点自身就满足了targetSum
 * 2. 子节点以及子节点的子节点列表组成了targetSum
 * 3. 父节点 + 子节点以及子节点的子节点组成了targetSum
 * 广度优先就是弄了个LinkedList 从头拿出一个把两个孩子往屁股后面塞
 * 深度优先就是Stack每次从屁股取一个还要再丢回去两个
 */
public class PathSumIII {


    public static int pathSum(TreeNode root, int targetSum) {

        if (root == null) {
            return 0;
        }

        // 已经把带头的都算上去了
        int res = rootSum(root, targetSum);
        // 都是不带头的解决方案
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);

        return res;

    }

    public static int rootSum(TreeNode root, int targetSum) {
        int res = 0;
        // 带头的又是只有头自己的
        if (root.val == targetSum) {
            res += 1;
        }
        // 和不仅有自己还有子子孙孙的 之所以上面==targetSum不return 是因为头是5 孩子还有加起来=0的
        // 带了头的 就要把头减去 剩下的来算 不过为啥是root而不是path, 就这么说吧一旦头带了，连下去的就必须带头
        // rootSum和pathSum的区别就在于 可不可以不带头 而在这个环境下 必须要带
        res += rootSum(root.left, targetSum - root.val);
        res += rootSum(root.right, targetSum - root.val);
        return res;
    }



    public static void main(String[] args) {

        TreeNode treeNode = TreeHelper.makeTree(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1);
//        TreeHelper.printTree(treeNode);
        System.out.println(pathSum(treeNode, 8));

    }
}
