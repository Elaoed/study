package org.example.algorithm;

import lombok.ToString;

@ToString
public class TreeTypeNode<E extends Comparable<E>> {

    public E element;

    public TreeTypeNode<E> left;

    public TreeTypeNode<E> right;

    public TreeTypeNode(E element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

}
