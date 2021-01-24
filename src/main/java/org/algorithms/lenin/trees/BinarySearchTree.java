package org.algorithms.lenin.trees;

import org.algorithms.lenin.utils.MinMax;

/**
 * This implementation of Binary Search Tree do not support duplicates
 */
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
            // return the min = Integer.MAX and max = Integer.MIN for null nodes
            return new MinMax(Integer.MAX_VALUE, Integer.MIN_VALUE);
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

        // if existing values is not found then add
        // note we do not support duplicates
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

    /**
     * Find a node in the Binary Search Tree
     *
     * Complexity:
     *  - Time: O(h)
     *  - Space: O(h) in recursion stack
     *
     * Test cases
     *   - Edge case: null tree
     *   - Base case: one node and value exists, one node tree but value does not exists
     *   - Regular case: tree with value, tree without the target value
     *
     * Return the node if it exists, otherwise return null
     */
    public TreeNode find(int val) {
        TreeNode current = this.root;

        while(null != current && val != current.getVal()) {
            if (val < current.getVal()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        return current;
    }

    /**
     * Delete a node from the binary search tree
     *
     * Complexity:
     *  - Space: O(h) for recursion stack
     *  - Time: O(h)
     *
     * Test cases:
     *   - Edge cases: null node
     *   - Base cases: single node tree, left leaf, right leaf
     *   - Regular cases: left child with successor, right child with successor
     */
    public void delete(TreeNode node) {
        if (null == node || null == this.root) {
            return; // nothing to delete
        }

        if (null == node.getLeft() && null == node.getRight()) {
            replaceChild(node.getParent(), node, null);
        } else if(null == node.getLeft()) {
            replaceChild(node.getParent(), node, node.getRight());
        } else if (null == node.getRight()) {
            replaceChild(node.getParent(), node, node.getLeft());
        } else {
            TreeNode successor = node.getRight();
            while (null != successor.getLeft()) {
                successor = successor.getLeft();
            }
            node.setVal(successor.getVal());
            delete(successor);
        }
    }

    private void replaceChild(TreeNode parent, TreeNode oldChild, TreeNode newChild) {
        if (null == parent) {
            this.root = newChild;
        } else if (parent.getLeft() == oldChild) {
            parent.setLeft(newChild);
        } else if (parent.getRight() == oldChild) {
            parent.setRight(newChild);
        } else {
            throw new IllegalArgumentException("Invalid parent-child relationship");
        }
    }
}
