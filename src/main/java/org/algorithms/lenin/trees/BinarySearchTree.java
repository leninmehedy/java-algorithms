package org.algorithms.lenin.trees;

import org.algorithms.lenin.utils.MinMax;

public class BinarySearchTree {
    TreeNode root;

    /**
     * Check if a binary tree is a Binary Search Tree (BST) or not
     *
     * If null is returned, the tree is not a BST
     * otherwise, the min and max values of the BST will be returned
     *
     * Solution: Find the min and max values in left and right subtrees of the root
     * and then compare that:
     *   - max of the left subtree is less than root
     *   - min of the right subtree is greater than root
     *
     * Complexity:
     *  - Time: O(n)
     *  - Space: O(h) on recursion stack
     *
     * Test cases:
     *   - Edge cases: Empty tree or NULL root
     *   - Base cases: Single node, Two nodes
     *   - Regular cases: not BST, valid BST, one leaf invalid
     */
    public static MinMax isBST(TreeNode root) {
        if (null == root) {
            return null;
        }

        if (null == root.getLeft() && null == root.getRight()) {
            return new MinMax(root.getVal(), root.getVal());
        }

        MinMax leftMinMax = isBST(root.getLeft());
        MinMax rightMinMax = isBST(root.getRight());

        // if either leftMinMax or rightMinMax subtree is not a BST,
        // the tree is not a BST
        if (null == leftMinMax || null == rightMinMax) {
            return null;
        }

        // BST check for this subtree
        if (leftMinMax.getMax() > root.getVal() || rightMinMax.getMin() < root.getVal()) {
            return null;
        }

        // If we are here, that means the subtree is a BST
        // so we return min and max of the subtree
        Integer min = root.getLeft() == null ? root.getVal() : leftMinMax.getMin();
        Integer max = root.getRight() == null ? root.getVal() : rightMinMax.getMax();
        return new MinMax(min, max);
    }

    /**
     * Get the root of the Binary Search Tree
     */
    public TreeNode getRoot() {
        return this.root;
    }

    /**
     * Add a new value to the binary search tree
     *
     * Solution: Find the position of the value and the parent node.
     *   - If parent is null, then it should be the root
     *   - otherwise,
     *         - set the node as left child of parent if value is less than parent,
     *         - set the node as right child of parent if value is greater than parent,
     *
     * Complexity:
     *   - Space: O(1) for new node and O(h) in the recursion stack
     *   - Time: O(h)
     *
     * Test cases:
     *  - Edge cases: first val (root == NULL)
     *  - Base cases: one node
     *  - Regular cases: node for left subtree, node for the right subtree
     */
    public TreeNode add(int val) {
        TreeNode parent = null;
        TreeNode current = this.root;

        while(null != current && val != current.getVal()) {
            parent = current;
            if (val < current.getVal()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (null == current) {
            current = new TreeNode(val);
            if (null == parent) {
                this.root = current;
            } else {
                if (val < parent.getVal()) {
                    parent.setLeft(current);
                } else {
                    parent.setRight(current);
                }
            }
        }

        return current;
    }
}
