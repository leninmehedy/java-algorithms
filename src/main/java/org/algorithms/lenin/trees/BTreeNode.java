package org.algorithms.lenin.trees;

import org.algorithms.lenin.graphs.State;

public class BTreeNode {
    private int val;
    private BTreeNode left;
    private BTreeNode right;
    private State state;

    public BTreeNode(int val) {
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

    public void setLeft(BTreeNode n) {
        this.left = n;
    }

    public BTreeNode getLeft() {
        return this.left;
    }

    public void setRight(BTreeNode n) {
        this.right = n;
    }
    public BTreeNode getRight() {
        return this.right;
    }
}
