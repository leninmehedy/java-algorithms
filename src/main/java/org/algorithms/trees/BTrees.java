package org.algorithms.trees;

import org.algorithms.graphs.State;

import java.util.List;
import java.util.Stack;

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

    /**
     * In order traversal without recursion
     * @param root BTreeNode
     * @param ret List<Integer> containing the traversal result
     */
    public static void inOrderIterative(BTreeNode root, List<Integer> ret) {
        if (null == ret) {
            throw new IllegalArgumentException("Return list cannot be null");
        }

        if (null == root) {
            return;
        }

        Stack<BTreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            BTreeNode node = stack.pop();
            if (node.getState() == State.VISITING) {
                ret.add(node.getVal());
            } else {
                node.setState(State.VISITING);

                if (null != node.getRight()) {
                    stack.push(node.getRight());
                }

                stack.push(node);

                if (null != node.getLeft()) {
                    stack.push(node.getLeft());
                }
            }
        }

    }
}
