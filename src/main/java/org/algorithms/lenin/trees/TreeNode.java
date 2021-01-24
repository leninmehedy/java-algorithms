package org.algorithms.lenin.trees;

import org.algorithms.lenin.graphs.State;

public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;
    private State state;

    public TreeNode(int val) {
        this.val = val;
        this.state = State.UNVISITED;
        this.left = null;
        this.right = null;
    }

    public int getVal() {
        return this.val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public String toString() {
        return String.format("%d", this.val);
    }

    public void setLeft(TreeNode n) {
        if (null == n) {
            return;
        }

        n.parent = this;
        this.left = n;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public TreeNode getParent() {
        return this.parent;
    }

    public void setRight(TreeNode n) {
        if (null == n) {
            return;
        }

        n.parent = this;
        this.right = n;
    }

    public TreeNode getRight() {
        return this.right;
    }
}
