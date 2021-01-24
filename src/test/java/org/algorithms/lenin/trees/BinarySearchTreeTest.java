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
}