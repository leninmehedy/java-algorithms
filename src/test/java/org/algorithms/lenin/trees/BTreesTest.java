package org.algorithms.lenin.trees;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BTreesTest {

    BTreeNode setup(List<BTreeNode> nodes) {
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
    void preOrder() { List<BTreeNode> nodes = new ArrayList<>();
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

    @Test
    void isBalanced() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);

        assertEquals(true, BTrees.isBalanced(null));
        assertEquals(true, BTrees.isBalanced(root));

        // make an imbalanced tree
        root.getLeft().getLeft().setLeft(new BTreeNode(100));
        root.getLeft().getLeft().getLeft().setLeft(new BTreeNode(101));
        List<Integer> ret = new ArrayList<>();
        BTrees.inOrder(root, ret);
        System.out.println(Arrays.toString(ret.toArray()));
        ret.clear();
        BTrees.preOrder(root, ret);
        System.out.println(Arrays.toString(ret.toArray()));

        assertEquals(false, BTrees.isBalanced(root));


    }

    @Test
    void diameter() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);

        assertEquals(0, BTrees.diameter(null));
        assertEquals(4, BTrees.diameter(root));
        assertEquals(2, BTrees.diameter(root.getLeft()));
    }

    @Test
    void lca() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);
        BTreeNode x = nodes.get(0);
        BTreeNode y = nodes.get(1);

        assertEquals(null, BTrees.lca(null, x, y));
        assertThrows(IllegalArgumentException.class, () -> {
           BTrees.lca(root, null, nodes.get(1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.lca(root, nodes.get(0), null);
        });

        // same subtree
        assertEquals(nodes.get(2), BTrees.lca(root, x, y));

        // different subtree
        y = nodes.get(6);
        assertEquals(root, BTrees.lca(root, x, y));

        // parent and left child
        x = nodes.get(4);
        y = nodes.get(5);
        assertEquals(x, BTrees.lca(root, x, y));

        // parent and right child
        x = nodes.get(4);
        y = nodes.get(6);
        assertEquals(x, BTrees.lca(root, x, y));

        // parent and right most child
        x = nodes.get(3);
        y = nodes.get(6);
        assertEquals(x, BTrees.lca(root, x, y));

        // parent and left most child
        x = nodes.get(3);
        y = nodes.get(6);
        assertEquals(x, BTrees.lca(root, x, y));

        // parent and left most child
        x = nodes.get(6);
        y = nodes.get(3);
        assertEquals(y, BTrees.lca(root, x, y));

        // nodes with no LCA
        x = nodes.get(6);
        y = new BTreeNode(7);
        assertEquals(null, BTrees.lca(root, x, y));
    }

    @Test
    void lca2() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);
        BTreeNode x = nodes.get(0);
        BTreeNode y = nodes.get(1);

        assertEquals(null, BTrees.lca2(null, x, y));
        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.lca2(root, null, nodes.get(1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.lca2(root, nodes.get(0), null);
        });

        // same subtree
        assertEquals(nodes.get(2), BTrees.lca2(root, x, y));

        // different subtree
        y = nodes.get(6);
        assertEquals(root, BTrees.lca2(root, x, y));

        // parent and left child
        x = nodes.get(4);
        y = nodes.get(5);
        assertEquals(x, BTrees.lca2(root, x, y));

        // parent and right child
        x = nodes.get(4);
        y = nodes.get(6);
        assertEquals(x, BTrees.lca2(root, x, y));

        // parent and right most child
        x = nodes.get(3);
        y = nodes.get(6);
        assertEquals(x, BTrees.lca2(root, x, y));

        // parent and left most child
        x = nodes.get(3);
        y = nodes.get(6);
        assertEquals(x, BTrees.lca2(root, x, y));

        // parent and left most child
        x = nodes.get(6);
        y = nodes.get(3);
        assertEquals(y, BTrees.lca2(root, x, y));
    }

    @Test
    public void allPaths() {
        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);

        List<Integer> curPath = new ArrayList<>();
        List<List<Integer>> paths = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.allPaths(root, null, paths);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.allPaths(root, curPath, null);
        });

        BTrees.allPaths(null, curPath, paths);
        assertEquals(0, paths.size());

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(new Integer[]{3, 2, 0}));
        expected.add(Arrays.asList(new Integer[]{3, 2, 1}));
        expected.add(Arrays.asList(new Integer[]{3, 4, 5}));
        expected.add(Arrays.asList(new Integer[]{3, 4, 6}));
        BTrees.allPaths(root, curPath, paths);
        assertEquals(expected.size(), paths.size());
        for (int i = 0; i < paths.size(); i++) {
            assertArrayEquals(expected.get(i).toArray(), paths.get(i).toArray());
        }
    }

    @Test
    public void buildTree() {
        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.buildTree(Arrays.asList(new Integer[]{0, 2}), Arrays.asList(new Integer[]{2}));
        });

        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);

        List<Integer> inOrder = new ArrayList<>();
        BTrees.inOrder(root, inOrder);

        List<Integer> preOrder= new ArrayList<>();
        BTrees.preOrder(root, preOrder);

        BTreeNode newRoot = BTrees.buildTree(inOrder, preOrder);

        // match the in-order traversal on the new tree
        List<Integer> ret = new ArrayList<>();
        BTrees.inOrder(newRoot, ret);
        assertArrayEquals(inOrder.toArray(), ret.toArray());

        // match the pre-order traversal on the new tree
        ret.clear();
        BTrees.preOrder(newRoot, ret);
        assertArrayEquals(preOrder.toArray(), ret.toArray());


        // single node tree
        inOrder = Arrays.asList(new Integer[]{0});
        preOrder = Arrays.asList(new Integer[]{0});
        newRoot = BTrees.buildTree(inOrder, preOrder);
        ret.clear();
        BTrees.inOrder(newRoot, ret);
        assertArrayEquals(inOrder.toArray(), ret.toArray());

        // two node tree
        inOrder = Arrays.asList(new Integer[]{0, 1});
        preOrder = Arrays.asList(new Integer[]{1, 0});
        newRoot = BTrees.buildTree(inOrder, preOrder);
        ret.clear();
        BTrees.preOrder(newRoot, ret);
        assertArrayEquals(preOrder.toArray(), ret.toArray());
    }

    @Test
    public void buildTree2() {
        assertThrows(IllegalArgumentException.class, () -> {
            BTrees.buildTree2(Arrays.asList(new Integer[]{0, 2}), Arrays.asList(new Integer[]{2}));
        });

        List<BTreeNode> nodes = new ArrayList<>();
        BTreeNode root = setup(nodes);

        List<Integer> inOrder = new ArrayList<>();
        BTrees.inOrder(root, inOrder);

        List<Integer> postOrder= new ArrayList<>();
        BTrees.postOrder(root, postOrder);

        BTreeNode newRoot = BTrees.buildTree2(inOrder, postOrder);

        // match the in-order traversal on the new tree
        List<Integer> ret = new ArrayList<>();
        BTrees.inOrder(newRoot, ret);
        assertArrayEquals(inOrder.toArray(), ret.toArray());

        // match the post-order traversal on the new tree
        ret.clear();
        BTrees.postOrder(newRoot, ret);
        assertArrayEquals(postOrder.toArray(), ret.toArray());


        // single node tree
        inOrder = Arrays.asList(new Integer[]{0});
        postOrder = Arrays.asList(new Integer[]{0});
        newRoot = BTrees.buildTree2(inOrder, postOrder);
        ret.clear();
        BTrees.inOrder(newRoot, ret);
        assertArrayEquals(inOrder.toArray(), ret.toArray());

        // two node tree
        inOrder = Arrays.asList(new Integer[]{0, 1});
        postOrder = Arrays.asList(new Integer[]{0, 1});
        newRoot = BTrees.buildTree2(inOrder, postOrder);
        ret.clear();
        BTrees.postOrder(newRoot, ret);
        assertArrayEquals(postOrder.toArray(), ret.toArray());
    }
}