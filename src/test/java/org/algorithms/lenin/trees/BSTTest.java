package org.algorithms.lenin.trees;

import org.algorithms.lenin.utils.MinMax;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    BTreeNode setupBST(List<BTreeNode> nodes) {
        for (int i = 0; i <= 6; i++) {
            nodes.add(new BTreeNode(i));
        }
        BTreeNode root = nodes.get(3);
        root.setLeft(nodes.get(1));
        root.setRight(nodes.get(5));
        root.getLeft().setLeft(nodes.get(0));
        root.getLeft().setRight(nodes.get(2));
        root.getRight().setLeft(nodes.get(4));
        root.getRight().setRight(nodes.get(6));

        return root;
    }

    BTreeNode setupNotBST(List<BTreeNode> nodes) {
        for (int i = 0; i <= 6; i++) {
            nodes.add(new BTreeNode(i));
        }
        BTreeNode root = nodes.get(3);
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
        assertNull(BST.isBST(null));

        // base cases
        assertEquals(new MinMax(0, 0), BST.isBST(new BTreeNode(0)));

        // valid BST
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setupBST(nodes);
        assertEquals(new MinMax(0, 6), BST.isBST(root));

        // invalid BST
        nodes.clear();
        root = setupNotBST(nodes);
        assertNull(BST.isBST(root));
    }
}