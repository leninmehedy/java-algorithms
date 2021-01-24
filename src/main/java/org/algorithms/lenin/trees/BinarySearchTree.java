package org.algorithms.lenin.trees;

import org.algorithms.lenin.utils.MinMax;

/**
 * This implementation of Binary Search Tree supports duplicates
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
        if (root == null) {
            // return the min = Integer.MAX and max = Integer.MIN for null nodes
            return new MinMax(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        MinMax leftMinMax = isBST(root.getLeft());
        MinMax rightMinMax = isBST(root.getRight());

        // if either leftMinMax or rightMinMax subtree is not a BST,
        // the tree is not a BST
        if (leftMinMax == null || rightMinMax == null) {
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

        while(current != null) {
            parent = current;
            if (val <= current.getVal()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        // if existing values is not found then add
        // note we do not support duplicates
        if (current == null) {
            current = new TreeNode(val);
            if (null == parent) {
                this.root = current;
            } else {
                if (val <= parent.getVal()) {
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
        if (node == null || this.root == null) {
            return; // nothing to delete
        }

        if (node.getLeft() == null && node.getRight() == null) {
            replaceChild(node.getParent(), node, null);
        } else if(node.getLeft() == null) {
            replaceChild(node.getParent(), node, node.getRight());
        } else if (node.getRight() == null) {
            replaceChild(node.getParent(), node, node.getLeft());
        } else {
            TreeNode successor = node.getRight();
            while (successor.getLeft() != null) {
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

    /**
     * Find first occurrence in in-order traversal
     *
     * Solution:
     *  - record match and move on until traverse is finished in the appropriate subtre
     *
     * Complexity:
     *  - Space: O(1)
     *  - Time: O(h)
     */
    public TreeNode findFirst(int val) {
        if (this.root == null) {
            return null;
        }

        TreeNode result = null;
        TreeNode current = this.root;
        while (current != null) {
            if (val < current.getVal()) {
                current = current.getLeft();
            } else if (val > current.getVal()) {
                current = current.getRight();
            } else {
                result = current; // record
                current = current.getLeft(); // move-on to left
            }
        }

        return result;
    }

    /**
     * Find successor of a node in BST
     *
     * Solution:
     *  - If node has right child, find the left most node in that right subtree
     *  - If node has no right child, search for the node from root and find the last node
     *  from where we had to traverse left to find the node
     *  - If node has neither of the above, there is no successor
     *
     * Complexity:
     *  - Space: O(1)
     *  - Time: O(h)
     *
     *  Test cases:
     *   - Edge cases: null root, single node
     *   - Base cases: two nodes with left subtree, two nodes with right subtree
     *   - Regular cases: node with left subtree, node with right subtree, last node of the tree
     */
    public TreeNode successor(TreeNode n) {
        if (n == null || this.root == null) {
            return null;
        }

        TreeNode successor = null;
        if (n.getRight() != null) {
            successor = n.getRight();
            while(null != successor.getLeft()) {
                successor = successor.getLeft();
            }
        } else if (n.getParent() != null){
            TreeNode current = this.root;
            while (null != current && !current.equals(n)) {
                if(n.getVal() < current.getVal()) {
                    successor = current;
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }
        }

        return successor;
    }

    /**
     * Find lowest common ancestor in BST
     *
     * Assumption: Both nodes exist in the tree
     *
     * Complexity:
     *  - Space: O(1)
     *  - Time: O(h)
     *
     * Test cases:
     *  - Edge cases: null values, empty tree
     *  - Base cases: tree nodes tree
     *  - Regular cases: nodes in same subtree, nodes in different subtree
     */
    public TreeNode lca(TreeNode a, TreeNode b) {
        if (a == null || b == null || this.root == null) {
            return null;
        }

        TreeNode current = this.root;
        while(current != null) {
            if (a.getVal() < current.getVal() && b.getVal() < current.getVal()) {
                current = current.getLeft();
            } else if (a.getVal() > current.getVal() && b.getVal() > current.getVal()) {
                current = current.getRight();
            } else {
                break; // current is between a and b, so this is the LCA
            }
        }

        return current;
    }

    /**
     * Reconstruct a BST from a sorted array
     *
     * Solution:
     *  - Find the median and make it a root
     *  - Recursively form the left subtree and right subtree from left and right parts of the array around the median
     *
     *  Complexity:
     *   - Space: O(n)
     *   - Time: O(n)
     *
     *  Test cases:
     *   - Edge cases: empty array
     *   - Base case: single element, two element, three element arrays
     *   - Regular case: even items array, odd items array
     */
    public static TreeNode reconstruct(int[] items) {
        if (items == null || items.length == 0) {
            return null;
        }

        return reconstruct(items, 0, items.length - 1);
    }

    public static boolean oob(int[] items, int i) {
        if (i < 0 || i >= items.length) {
            return true;
        }

        return false;
    }

    public static TreeNode reconstruct(int[] items, int start, int end) {
        if (start > end || oob(items, start) || oob(items, end)) {
            return null;
        }

        // find median
        int k = start + (end - start) / 2;

        // construct the tree
        TreeNode root =  new TreeNode(items[k]);
        TreeNode left = reconstruct(items, start, k - 1);
        TreeNode right = reconstruct(items, k + 1, end);

        root.setLeft(left);
        root.setRight(right);

        return root;
    }

}
