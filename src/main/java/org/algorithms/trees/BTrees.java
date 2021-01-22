package org.algorithms.trees;

import java.util.List;

public class BTrees {
    public static void inOrder(BTreeNode root, List<Integer> ret) {
        if (null == ret) {
            throw new IllegalArgumentException("Return list cannot be null");
        }

        if (null == root) {
            return;
        }


        inOrder(root.getLeft(), ret);
        ret.add(root.getVal());
        inOrder(root.getRight(), ret);
    }

    public static void preOrder(BTreeNode root, List<Integer>ret) {
        if (null == ret) {
            throw new IllegalArgumentException("Return list cannot be null");
        }

        if (null == root) {
            return;
        }

        ret.add(root.getVal());
        preOrder(root.getLeft(), ret);
        preOrder(root.getRight(), ret);
    }

    public static void postOrder(BTreeNode root, List<Integer> ret) {
        if (null == ret) {
            throw new IllegalArgumentException("Return list cannot be null");
        }

        if (null == root) {
            return;
        }

        postOrder(root.getLeft(), ret);
        postOrder(root.getRight(), ret);
        ret.add(root.getVal());
    }
}
