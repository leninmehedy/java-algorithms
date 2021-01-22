package org.algorithms.lenin.trees;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BTreesTest {

    BTreeNode setup(List<BTreeNode> nodes) {
        nodes = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            nodes.add(new BTreeNode(i));
        }
        BTreeNode root = nodes.get(3);
        root.setLeft(nodes.get(2));
        root.setRight(nodes.get(4));
        root.getLeft().setLeft(nodes.get(0));
        root.getLeft().setRight(nodes.get(1));
        root.getRight().setLeft(nodes.get(5));
        root.getRight().setRight(nodes.get(6));

        return root;
    }

    @Test
    void inOrder() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);

        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.inOrder(root, null);
        });

        List<Integer> ret = new ArrayList<>();
        BTrees.inOrder(null, ret);
        assertArrayEquals(new Integer[0], ret.toArray());

        List<Integer> expInOrder = Arrays.asList(new Integer[]{0, 2, 1, 3, 5, 4, 6});
        BTrees.inOrder(root, ret);
        assertArrayEquals(expInOrder.toArray(), ret.toArray());

    }

    @Test
    void preOrder() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);

        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.preOrder(root, null);
        });

        List<Integer> ret = new ArrayList<>();
        BTrees.preOrder(null, ret);
        assertArrayEquals(new Integer[0], ret.toArray());

        List<Integer> expected = Arrays.asList(new Integer[]{3, 2, 0, 1, 4, 5, 6});
        BTrees.preOrder(root, ret);
        assertArrayEquals(expected.toArray(), ret.toArray());

    }

    @Test
    void postOrder() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);

        List<Integer> ret = new ArrayList<>();
        BTrees.postOrder(null, ret);
        assertArrayEquals(new Integer[0], ret.toArray());

        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.postOrder(root, null);
        });

        List<Integer> expected = Arrays.asList(new Integer[]{0, 1, 2, 5, 6, 4, 3});
        BTrees.postOrder(root, ret);
        assertArrayEquals(expected.toArray(), ret.toArray());
    }

    @Test
    void inOrderIterative() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);

        List<Integer> ret = new ArrayList<>();
        BTrees.inOrderIterative(null, ret);
        assertArrayEquals(new Integer[0], ret.toArray());

        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.inOrderIterative(root, null);
        });

        List<Integer> expInOrder = Arrays.asList(new Integer[]{0, 2, 1, 3, 5, 4, 6});
        BTrees.inOrderIterative(root, ret);
        assertArrayEquals(expInOrder.toArray(), ret.toArray());
    }

    @Test
    void heightTop2Bottom() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);
        Map<String, Integer> ret = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.heightTop2Bottom(null, -1, ret);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.heightTop2Bottom(null, 0, null);
        });

        // null root will just return ret with initialized height
        BTrees.heightTop2Bottom(null, 0, ret);
        assertEquals(0, ret.get(BTrees.TREE_HEIGHT));

        // null root will just return the ret as it is
        ret.put(BTrees.TREE_HEIGHT, -1);
        BTrees.heightTop2Bottom(null, 0, ret);
        assertEquals(-1, ret.get(BTrees.TREE_HEIGHT));
        ret.clear();

        BTrees.heightTop2Bottom(root, 0, ret);
        assertEquals(2, ret.get(BTrees.TREE_HEIGHT));
    }

    @Test
    void heightBottom2Top() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);

        assertEquals(-1, BTrees.heightBottom2Top(null));
        assertEquals(2, BTrees.heightBottom2Top(root));
    }
}