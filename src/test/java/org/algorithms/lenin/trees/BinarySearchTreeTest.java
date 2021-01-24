package org.algorithms.lenin.trees;

import org.algorithms.lenin.utils.MinMax;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    TreeNode setupBST(List<TreeNode> nodes) {
        for (int i = 0; i <= 6; i++) {
            nodes.add(new TreeNode(i));
        }
        TreeNode root = nodes.get(3);
        root.setLeft(nodes.get(1));
        root.setRight(nodes.get(5));
        root.getLeft().setLeft(nodes.get(0));
        root.getLeft().setRight(nodes.get(2));
        root.getRight().setLeft(nodes.get(4));
        root.getRight().setRight(nodes.get(6));

        return root;
    }

    TreeNode setupNotBST(List<TreeNode> nodes) {
        for (int i = 0; i <= 6; i++) {
            nodes.add(new TreeNode(i));
        }
        TreeNode root = nodes.get(3);
        root.setLeft(nodes.get(1));
        root.setRight(nodes.get(4));
        root.getLeft().setLeft(nodes.get(0));
        root.getLeft().setRight(nodes.get(2));
        root.getRight().setLeft(nodes.get(5));
        root.getRight().setRight(nodes.get(6));

        return root;
    }

    @Test
    void isBST() {
        // edge cases
        assertNull(BinarySearchTree.isBST(null));

        // base cases
        assertEquals(new MinMax(0, 0), BinarySearchTree.isBST(new TreeNode(0)));

        // valid BST
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setupBST(nodes);
        assertEquals(new MinMax(0, 6), BinarySearchTree.isBST(root));

        // invalid BST
        nodes.clear();
        root = setupNotBST(nodes);
        assertNull(BinarySearchTree.isBST(root));
    }

    @Test
    void add() {
        BinarySearchTree bst = new BinarySearchTree();

        // Edge cases
        assertNull(bst.getRoot());
        bst.add(3);
        assertNotNull(bst.getRoot());
        assertEquals(3, bst.getRoot().getVal());

        // Base cases
        // add a node to the left of the root
        TreeNode n = bst.add(1);
        assertEquals(n, bst.getRoot().getLeft());

        // add a node to the right of the root
        n = bst.add(5);
        assertEquals(n, bst.getRoot().getRight());

        // add a node to the left subtree
        n = bst.add(0);
        assertEquals(n, bst.getRoot().getLeft().getLeft());
        n = bst.add(2);
        assertEquals(n, bst.getRoot().getLeft().getRight());

        // add a node to the right subtree
        n = bst.add(4);
        assertEquals(n, bst.getRoot().getRight().getLeft());
        n = bst.add(6);
        assertEquals(n, bst.getRoot().getRight().getRight());

        assertNotNull(BinarySearchTree.isBST(bst.getRoot()));
    }

    @Test
    public void find() {
        BinarySearchTree bst = new BinarySearchTree();

        // Edge case
        assertNull(bst.find(0));

        // Base case
        TreeNode n = bst.add(3);
        assertEquals(n, bst.find(3));

        // left subtree
        bst.add(1);
        bst.add(0);
        n = bst.add(2);
        assertEquals(n, bst.find(2));

        // right subtree
        bst.add(5);
        n = bst.add(4);
        assertEquals(n, bst.find(4));

        n = bst.add(6);
        assertEquals(n, bst.find(6));
    }
}