package org.algorithms.lenin.trees;

import org.algorithms.lenin.graphs.State;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class BTrees {
    public static final String TREE_HEIGHT = "height";

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

    /**
     * Find the height of the binary tree
     *
     * This is a top down approach where we pass the depth of the parent to the children
     * and then compute the max height as we traverse various paths recursively
     *
     * @param root
     * @return
     */
    public static void heightTop2Bottom(BTreeNode root, Integer curDepth, Map<String, Integer> ret) {
        if (null == ret) {
            throw new IllegalArgumentException("Return map object cannot be null");
        }

        if (curDepth < 0) {
            throw new IllegalArgumentException("Current depth of the tree cannot be negative");
        }

        // initialize max height
        if (ret.containsKey(TREE_HEIGHT) == false) {
            ret.put(TREE_HEIGHT, 0);
        }

        if (null == root) {
            return;
        }

        if (curDepth > ret.get(TREE_HEIGHT)) {
            // if current depth is higher than previous height,
            // update the height of the tree to current depth
            ret.put(TREE_HEIGHT, curDepth);
        }

        heightTop2Bottom(root.getLeft(), curDepth + 1, ret);
        heightTop2Bottom(root.getRight(), curDepth + 1, ret);
    }

    /**
     * Find the height of the binary tree
     *
     * This is a bottom to top approach where we compute the height using below formula:
     *   height = max(height of left sub tree, height of right sub tree) + 1
     *
     * @param root
     * @return
     */
    public static int heightBottom2Top(BTreeNode root) {
        if (null == root) {
            return -1;
        }

        int leftHeight = heightBottom2Top(root.getLeft());
        int rightHeight = heightBottom2Top(root.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
