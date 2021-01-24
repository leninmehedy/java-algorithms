package org.algorithms.lenin.trees;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    TreeNode setup(List<TreeNode> nodes) {
        for (int i = 0; i <= 6; i++) {
            nodes.add(new TreeNode(i));
        }
        TreeNode root = nodes.get(3);
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
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.inOrder(root, null);
        });

        List<Integer> ret = new ArrayList<>();
        BinaryTree.inOrder(null, ret);
        assertArrayEquals(new Integer[0], ret.toArray());

        List<Integer> expInOrder = Arrays.asList(new Integer[]{0, 2, 1, 3, 5, 4, 6});
        BinaryTree.inOrder(root, ret);
        assertArrayEquals(expInOrder.toArray(), ret.toArray());

    }

    @Test
    void preOrder() { List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.preOrder(root, null);
        });

        List<Integer> ret = new ArrayList<>();
        BinaryTree.preOrder(null, ret);
        assertArrayEquals(new Integer[0], ret.toArray());

        List<Integer> expected = Arrays.asList(new Integer[]{3, 2, 0, 1, 4, 5, 6});
        BinaryTree.preOrder(root, ret);
        assertArrayEquals(expected.toArray(), ret.toArray());

    }

    @Test
    void postOrder() {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);

        List<Integer> ret = new ArrayList<>();
        BinaryTree.postOrder(null, ret);
        assertArrayEquals(new Integer[0], ret.toArray());

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.postOrder(root, null);
        });

        List<Integer> expected = Arrays.asList(new Integer[]{0, 1, 2, 5, 6, 4, 3});
        BinaryTree.postOrder(root, ret);
        assertArrayEquals(expected.toArray(), ret.toArray());
    }

    @Test
    void inOrderIterative() {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);

        List<Integer> ret = new ArrayList<>();
        BinaryTree.inOrderIterative(null, ret);
        assertArrayEquals(new Integer[0], ret.toArray());

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.inOrderIterative(root, null);
        });

        List<Integer> expInOrder = Arrays.asList(new Integer[]{0, 2, 1, 3, 5, 4, 6});
        BinaryTree.inOrderIterative(root, ret);
        assertArrayEquals(expInOrder.toArray(), ret.toArray());
    }

    @Test
    void heightTop2Bottom() {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);
        Map<String, Integer> ret = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.heightTop2Bottom(null, -1, ret);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.heightTop2Bottom(null, 0, null);
        });

        // null root will just return ret with initialized height
        BinaryTree.heightTop2Bottom(null, 0, ret);
        assertEquals(0, ret.get(BinaryTree.TREE_HEIGHT));

        // null root will just return the ret as it is
        ret.put(BinaryTree.TREE_HEIGHT, -1);
        BinaryTree.heightTop2Bottom(null, 0, ret);
        assertEquals(-1, ret.get(BinaryTree.TREE_HEIGHT));
        ret.clear();

        BinaryTree.heightTop2Bottom(root, 0, ret);
        assertEquals(2, ret.get(BinaryTree.TREE_HEIGHT));
    }

    @Test
    void heightBottom2Top() {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);

        assertEquals(-1, BinaryTree.heightBottom2Top(null));
        assertEquals(2, BinaryTree.heightBottom2Top(root));
    }

    @Test
    void isBalanced() {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);

        assertEquals(true, BinaryTree.isBalanced(null));
        assertEquals(true, BinaryTree.isBalanced(root));

        // make an imbalanced tree
        root.getLeft().getLeft().setLeft(new TreeNode(100));
        root.getLeft().getLeft().getLeft().setLeft(new TreeNode(101));
        List<Integer> ret = new ArrayList<>();
        BinaryTree.inOrder(root, ret);
        System.out.println(Arrays.toString(ret.toArray()));
        ret.clear();
        BinaryTree.preOrder(root, ret);
        System.out.println(Arrays.toString(ret.toArray()));

        assertEquals(false, BinaryTree.isBalanced(root));


    }

    @Test
    void diameter() {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);

        assertEquals(0, BinaryTree.diameter(null));
        assertEquals(4, BinaryTree.diameter(root));
        assertEquals(2, BinaryTree.diameter(root.getLeft()));
    }

    @Test
    void lca() {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);
        TreeNode x = nodes.get(0);
        TreeNode y = nodes.get(1);

        assertEquals(null, BinaryTree.lca(null, x, y));
        assertThrows(IllegalArgumentException.class, () -> {
           BinaryTree.lca(root, null, nodes.get(1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.lca(root, nodes.get(0), null);
        });

        // same subtree
        assertEquals(nodes.get(2), BinaryTree.lca(root, x, y));

        // different subtree
        y = nodes.get(6);
        assertEquals(root, BinaryTree.lca(root, x, y));

        // parent and left child
        x = nodes.get(4);
        y = nodes.get(5);
        assertEquals(x, BinaryTree.lca(root, x, y));

        // parent and right child
        x = nodes.get(4);
        y = nodes.get(6);
        assertEquals(x, BinaryTree.lca(root, x, y));

        // parent and right most child
        x = nodes.get(3);
        y = nodes.get(6);
        assertEquals(x, BinaryTree.lca(root, x, y));

        // parent and left most child
        x = nodes.get(3);
        y = nodes.get(6);
        assertEquals(x, BinaryTree.lca(root, x, y));

        // parent and left most child
        x = nodes.get(6);
        y = nodes.get(3);
        assertEquals(y, BinaryTree.lca(root, x, y));

        // nodes with no LCA
        x = nodes.get(6);
        y = new TreeNode(7);
        assertEquals(null, BinaryTree.lca(root, x, y));
    }

    @Test
    void lca2() {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);
        TreeNode x = nodes.get(0);
        TreeNode y = nodes.get(1);

        assertEquals(null, BinaryTree.lca2(null, x, y));
        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.lca2(root, null, nodes.get(1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.lca2(root, nodes.get(0), null);
        });

        // same subtree
        assertEquals(nodes.get(2), BinaryTree.lca2(root, x, y));

        // different subtree
        y = nodes.get(6);
        assertEquals(root, BinaryTree.lca2(root, x, y));

        // parent and left child
        x = nodes.get(4);
        y = nodes.get(5);
        assertEquals(x, BinaryTree.lca2(root, x, y));

        // parent and right child
        x = nodes.get(4);
        y = nodes.get(6);
        assertEquals(x, BinaryTree.lca2(root, x, y));

        // parent and right most child
        x = nodes.get(3);
        y = nodes.get(6);
        assertEquals(x, BinaryTree.lca2(root, x, y));

        // parent and left most child
        x = nodes.get(3);
        y = nodes.get(6);
        assertEquals(x, BinaryTree.lca2(root, x, y));

        // parent and left most child
        x = nodes.get(6);
        y = nodes.get(3);
        assertEquals(y, BinaryTree.lca2(root, x, y));
    }

    @Test
    public void allPaths() {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);

        List<Integer> curPath = new ArrayList<>();
        List<List<Integer>> paths = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.allPaths(root, null, paths);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.allPaths(root, curPath, null);
        });

        BinaryTree.allPaths(null, curPath, paths);
        assertEquals(0, paths.size());

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(new Integer[]{3, 2, 0}));
        expected.add(Arrays.asList(new Integer[]{3, 2, 1}));
        expected.add(Arrays.asList(new Integer[]{3, 4, 5}));
        expected.add(Arrays.asList(new Integer[]{3, 4, 6}));
        BinaryTree.allPaths(root, curPath, paths);
        assertEquals(expected.size(), paths.size());
        for (int i = 0; i < paths.size(); i++) {
            assertArrayEquals(expected.get(i).toArray(), paths.get(i).toArray());
        }
    }

    @Test
    public void buildTree() {
        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.buildTree(Arrays.asList(new Integer[]{0, 2}), Arrays.asList(new Integer[]{2}));
        });

        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);

        List<Integer> inOrder = new ArrayList<>();
        BinaryTree.inOrder(root, inOrder);

        List<Integer> preOrder= new ArrayList<>();
        BinaryTree.preOrder(root, preOrder);

        TreeNode newRoot = BinaryTree.buildTree(inOrder, preOrder);

        // match the in-order traversal on the new tree
        List<Integer> ret = new ArrayList<>();
        BinaryTree.inOrder(newRoot, ret);
        assertArrayEquals(inOrder.toArray(), ret.toArray());

        // match the pre-order traversal on the new tree
        ret.clear();
        BinaryTree.preOrder(newRoot, ret);
        assertArrayEquals(preOrder.toArray(), ret.toArray());


        // single node tree
        inOrder = Arrays.asList(new Integer[]{0});
        preOrder = Arrays.asList(new Integer[]{0});
        newRoot = BinaryTree.buildTree(inOrder, preOrder);
        ret.clear();
        BinaryTree.inOrder(newRoot, ret);
        assertArrayEquals(inOrder.toArray(), ret.toArray());

        // two node tree
        inOrder = Arrays.asList(new Integer[]{0, 1});
        preOrder = Arrays.asList(new Integer[]{1, 0});
        newRoot = BinaryTree.buildTree(inOrder, preOrder);
        ret.clear();
        BinaryTree.preOrder(newRoot, ret);
        assertArrayEquals(preOrder.toArray(), ret.toArray());
    }

    @Test
    public void buildTree2() {
        assertThrows(IllegalArgumentException.class, () -> {
            BinaryTree.buildTree2(Arrays.asList(new Integer[]{0, 2}), Arrays.asList(new Integer[]{2}));
        });

        List<TreeNode> nodes = new ArrayList<>();
        TreeNode root = setup(nodes);

        List<Integer> inOrder = new ArrayList<>();
        BinaryTree.inOrder(root, inOrder);

        List<Integer> postOrder= new ArrayList<>();
        BinaryTree.postOrder(root, postOrder);

        TreeNode newRoot = BinaryTree.buildTree2(inOrder, postOrder);

        // match the in-order traversal on the new tree
        List<Integer> ret = new ArrayList<>();
        BinaryTree.inOrder(newRoot, ret);
        assertArrayEquals(inOrder.toArray(), ret.toArray());

        // match the post-order traversal on the new tree
        ret.clear();
        BinaryTree.postOrder(newRoot, ret);
        assertArrayEquals(postOrder.toArray(), ret.toArray());


        // single node tree
        inOrder = Arrays.asList(new Integer[]{0});
        postOrder = Arrays.asList(new Integer[]{0});
        newRoot = BinaryTree.buildTree2(inOrder, postOrder);
        ret.clear();
        BinaryTree.inOrder(newRoot, ret);
        assertArrayEquals(inOrder.toArray(), ret.toArray());

        // two node tree
        inOrder = Arrays.asList(new Integer[]{0, 1});
        postOrder = Arrays.asList(new Integer[]{0, 1});
        newRoot = BinaryTree.buildTree2(inOrder, postOrder);
        ret.clear();
        BinaryTree.postOrder(newRoot, ret);
        assertArrayEquals(postOrder.toArray(), ret.toArray());
    }
}