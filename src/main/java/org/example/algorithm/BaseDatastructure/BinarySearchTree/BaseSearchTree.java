package org.example.algorithm.BaseDatastructure.BinarySearchTree;

import lombok.ToString;
import org.example.algorithm.TreeTypeNode;

/**
 * 二叉查找树相比较于二叉树的区别是
 * 可以在O(NlogN)的时间复杂度之内查找到一个数
 * 左孩子都比父亲要小，右孩子都比父亲要大
 *
 * 二叉排序树和二叉查找树的区别 是同一个东西吧
 * 平衡二叉树保证两边不太倾斜: 左右子树的高度差不大于1 递归起作用
 * 平衡二叉树会根据条件，如果左右子树的高度差大于1会进行旋转
 * 所以平衡二叉树是一种二叉查找树、也是一种完全二叉树，是一种完全二叉查找树 ::)
 * 其实就也不难
 *
 * 考虑二叉查找树是否允许插入重复值 注意: 二叉查找树不是满二叉树，也不是完全二叉树， 甚至可能是链表
 * 二叉树的删除涉及到拼接，所以都会通过递归把自己的的儿子们组成一颗心树 然后给到爷爷拼接
 *
 */
@ToString
public class BaseSearchTree<E extends Comparable<E>> {

    public TreeTypeNode<E> root;

    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 二叉查找树的判断元素是否存在
     * @param node
     * @param target
     * @return
     */
    public boolean contains(TreeTypeNode<E> node, E target){

        if (node == null) {
            return false;
        }

        if (node.element.equals(target)) {
            return true;
        }

        return target.compareTo(node.element) > 0 ? contains(node.right, target) : contains(node.left, target);

    }

    public boolean contains(E target) {
        return contains(root, target);
    }

    public void insert(TreeTypeNode<E> node, E newElement) {

        if (newElement.compareTo(node.element) == 0) {
            node.left = new TreeTypeNode<>(newElement);
            return;
        }

        if (newElement.compareTo(node.element) > 0)  {
            if (node.right == null) {
                node.right = new TreeTypeNode<>(newElement);
                size++;
            } else {
                insert(node.right, newElement);
            }
        }

        if (newElement.compareTo(node.element) < 0)  {
            if (node.left == null) {
                node.left = new TreeTypeNode<>(newElement);
                size++;
            } else {
                insert(node.left, newElement);
            }
        }

    }

    public void insert(E newElement) {
        if (root == null) {
            root = new TreeTypeNode<>(newElement);
            size = 1;
            return;
        }
        insert(root, newElement);
    }

    // 删除某个节点的意思也就是 删除以这个节点为根节点
    public TreeTypeNode<E> delete(TreeTypeNode<E> node, E target) {

        if (node == null) {
            return null;
        }

        if (node.element.compareTo(target) == 0) {
            // 叶子节点可以直接删除
            if (node.left == null && node.right == null) {
                return null;
            }
            // 如何把下面的节点提上来
            // 分只有一个节点和有两个节点
            if (node.left != null && node.right != null) {
                // 组成一颗新树返回
                // 需要找一个备胎，备胎的话需要找目标左树的最大值 或者目标右树的最小值
                TreeTypeNode<E> successorRight = removeMin(node.right); // 这个remove相当于pop
//                TreeTypeNode<E> successorLeft = removeMax(node.left);
                node.right = successorRight;
                return node;
            }

            // size--; node.left = null, node.right = null;
            return node.left == null ? node.right : node.left;

        }

        if (target.compareTo(node.element) > 0) {
            node.right = delete(node.right, target);
        }

        if (target.compareTo(node.element) < 0) {
            node.left = delete(node.left, target);
        }

        return node;

    }

    public void delete(E target) {
        if (root == null) {
            throw new RuntimeException("已经没有可以删除的数据了");
        }
        delete(root, target);
    }

    public TreeTypeNode<E> findMaximum() {
        TreeTypeNode<E> curr = root;
        TreeTypeNode<E> father = root;
        while (curr != null) {
            father = curr;
            curr = curr.left;
        }
        return father;
    }

    public TreeTypeNode<E> findMinimum() {
        TreeTypeNode<E> curr = root;
        TreeTypeNode<E> father = root;
        while (curr != null) {
            father = curr;
            curr = curr.right;
        }
        return father;

    }

    public E removeMin() {
        if (root == null) {
            throw new RuntimeException("你的树已经没有元素了");
        }
        E e = findMinimum().element;
        root = removeMin(root);
        return e;
    }

    public TreeTypeNode<E> removeMin(TreeTypeNode<E> node) {
        // 当前节点就是最小节点
        if (node.left == null) {
            TreeTypeNode<E> rightNode = node.right;
            node.right = null;
            size--;
            // 返回孙子给爷爷，去掉爸爸
            return rightNode;
        } else {
            node.left = removeMin(node.left);
            return node;
        }
    }
    // 删除最大元素和这个类似

}
