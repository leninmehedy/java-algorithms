package org.algorithms.lenin.trees;

import org.algorithms.lenin.utils.MinMax;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinarySearchTree setupBST() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(3);
        bst.add(1);
        bst.add(5);
        bst.add(0);
        bst.add(2);
        bst.add(4);
        bst.add(6);

        return bst;
    }

    private BinarySearchTree setupImbalancedBST() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(3);
        bst.add(1);
        bst.add(0);
        bst.add(10);

        return bst;
    }

    private TreeNode setupNotBST(List<TreeNode> nodes) {
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
        assertEquals(new MinMax(Integer.MAX_VALUE, Integer.MIN_VALUE), BinarySearchTree.isBST(null));

        // base cases
        assertEquals(new MinMax(0, 0), BinarySearchTree.isBST(new TreeNode(0)));

        // valid BST
        BinarySearchTree bst = setupBST();
        assertEquals(new MinMax(0, 6), BinarySearchTree.isBST(bst.getRoot()));

        // invalid BST
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setupNotBST(nodes);
        assertNull(BinarySearchTree.isBST(root));


        bst = setupImbalancedBST();
        assertEquals(new MinMax(0, 10), BinarySearchTree.isBST(bst.getRoot()));

        // add duplicates
        bst.add(10);
        assertEquals(new MinMax(0, 10), BinarySearchTree.isBST(bst.getRoot()));
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

        bst.add(4);
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

        assertNull(bst.find(100));
    }

    @Test
    void delete() {

        BinarySearchTree bst = new BinarySearchTree();

        // null tree
        assertNull(bst.getRoot());
        bst.delete(new TreeNode(0));
        assertNull(bst.getRoot());

        // null node
        bst.delete(null);
        assertNull(bst.getRoot());

        // Base case
        TreeNode n = bst.add(0);
        assertEquals(0, bst.getRoot().getVal());
        bst.delete(n);
        assertNull(bst.getRoot());

        // two nodes tree
        bst.add(3);
        n = bst.add(2);
        bst.delete(n);
        assertNull(bst.find(2));
        assertEquals(3, bst.getRoot().getVal());


        // left most leaf of left subtree
        TreeNode node2 = bst.add(2);
        TreeNode node0 = bst.add(0);
        TreeNode node1 = bst.add(1);
        bst.delete(node0);
        assertNull(bst.find(0));
        bst.add(0);

        // right most leaf of left subtree
        bst.delete(node1);
        assertNull(bst.find(1));
        bst.add(1);

        // left subtree root with successor
        bst.delete(node2);
        assertNull(bst.find(2));
        assertEquals(0, bst.getRoot().getLeft().getVal());


        // right subtree
        bst.add(10);
        TreeNode node5 = bst.add(5);
        TreeNode node4 = bst.add(4);
        bst.add(8);
        bst.add(6);
        bst.add(9);
        TreeNode node12 = bst.add(12);

        // left most node of right subtree
        bst.delete(node4);
        assertNull(bst.find(4));
        bst.add(4);

        // right most node of right subtree
        bst.delete(node12);
        assertNull(bst.find(12));
        bst.add(12);

        // right subtree root with successor
        bst.delete(node5);
        assertEquals(6, bst.getRoot().getRight().getLeft().getVal());
    }

    @Test
    public void findFirst() {
        BinarySearchTree bst = new BinarySearchTree();

        // edge case
        assertNull(bst.findFirst(0));

        bst.add(4);
        assertEquals(bst.getRoot(), bst.findFirst(4));

        bst.add(2);
        bst.add(1);
        TreeNode node4 = bst.add(4);
        bst.add(5);

        assertEquals(node4, bst.findFirst(4));
        assertNotEquals(bst.find(4), bst.findFirst(4));
        assertEquals(bst.find(1), bst.findFirst(1));
    }

    @Test
    public void successor() {
        BinarySearchTree bst = new BinarySearchTree();

        // Edge cases : null node or empty tree
        assertNull(bst.successor(null));
        assertNull(bst.successor(new TreeNode(0)));

        // Base cases - one node tree
        TreeNode node4 = bst.add(4);
        assertNull(bst.successor(node4));

        // Base cases - two nodes with right subtree
        TreeNode node10 = bst.add(10);
        assertEquals(node10, bst.successor(node4));
        assertNull(bst.successor(node10));
        bst.delete(node10);

        // Base cases - two nodes with left subtree
        TreeNode node2 = bst.add(2);
        assertEquals(node4, bst.successor(node2));
        assertNull(bst.successor(node4));

        // node on the deep left subtree
        TreeNode node1 = bst.add(1);
        TreeNode node3 = bst.add(3);
        assertEquals(bst.getRoot(), bst.successor(node3));
        assertEquals(node2, bst.successor(node1));

        // left most child of the right subtree
        bst.add(10);
        TreeNode node5 = bst.add(5);
        TreeNode node11 = bst.add(11);
        assertEquals(node5, bst.successor(node4));
        assertNull(bst.successor(node11));
    }

    @Test
    public void lca() {
        BinarySearchTree bst = new BinarySearchTree();

        // Edge case: null values and null tree
        assertNull(bst.lca(new TreeNode(1), new TreeNode(11)));

        TreeNode node4 = bst.add(4);
        assertNull(bst.lca(null, new TreeNode(11)));
        assertNull(bst.lca(new TreeNode(1), null));

        // Base case
        TreeNode node2 = bst.add(2);
        TreeNode node10 = bst.add(10);
        assertEquals(bst.getRoot(), bst.lca(node2, node10));

        TreeNode node1 = bst.add(1);
        TreeNode node3 = bst.add(3);
        TreeNode node5 =  bst.add(5);
        TreeNode node11 =  bst.add(11);

        // left subtree
        assertEquals(node2, bst.lca(node1, node3));

        // right subtree
        assertEquals(node10, bst.lca(node5, node11));

        // cross subtrees
        assertEquals(node4, bst.lca(node1, node11));
    }

    @Test
    public void reconstruct() {
        // Edge cases
        assertNull(BinarySearchTree.reconstruct(null));
        assertNull(BinarySearchTree.reconstruct(new Integer[0]));

        // Base cases - one node
        assertEquals(0, BinarySearchTree.reconstruct(new Integer[]{0}).getVal());

        // Base cases - two nodes (left subtree)
        TreeNode root = BinarySearchTree.reconstruct(new Integer[]{2, 4});
        assertEquals(2, root.getVal());
        assertEquals(4, root.getRight().getVal());

        // Base cases - three nodes
        root = BinarySearchTree.reconstruct(new Integer[]{2, 4, 10});
        assertEquals(4, root.getVal());
        assertEquals(2, root.getLeft().getVal());
        assertEquals(10, root.getRight().getVal());

        // Regular cases - even nodes
        root = BinarySearchTree.reconstruct(new Integer[]{1, 2, 4, 10});
        assertEquals(2, root.getVal());
        assertEquals(1, root.getLeft().getVal());
        assertEquals(4, root.getRight().getVal());
        assertEquals(10, root.getRight().getRight().getVal());

        // invalid array index
        root = BinarySearchTree.reconstruct(new Integer[]{0}, 0, 10);
        assertNull(root);
    }
}