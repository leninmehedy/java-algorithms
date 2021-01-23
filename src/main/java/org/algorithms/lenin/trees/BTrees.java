package org.algorithms.lenin.trees;

import org.algorithms.lenin.graphs.State;

import java.util.*;

public class BTrees {
    public static final String TREE_HEIGHT = "height";
    public static final String TREE_LONGEST_PATH = "longestPath";

    public static void inOrder(BTreeNode root, List<Integer> ret) {
        if (null == ret) {
            throw new IllegalArgumentException("Return list cannot be null");
        }

        if (null == root) {
            return;
        }


        inOrder(root.getLeft(), ret);
        ret.add(root.getVal());
        inOrder(root.getRight(), ret);
    }

    public static void preOrder(BTreeNode root, List<Integer> ret) {
        if (null == ret) {
            throw new IllegalArgumentException("Return list cannot be null");
        }

        if (null == root) {
            return;
        }

        ret.add(root.getVal());
        preOrder(root.getLeft(), ret);
        preOrder(root.getRight(), ret);
    }

    public static void postOrder(BTreeNode root, List<Integer> ret) {
        if (null == ret) {
            throw new IllegalArgumentException("Return list cannot be null");
        }

        if (null == root) {
            return;
        }

        postOrder(root.getLeft(), ret);
        postOrder(root.getRight(), ret);
        ret.add(root.getVal());
    }

    /**
     * In order traversal without recursion
     *
     * @param root BTreeNode
     * @param ret  List<Integer> containing the traversal result
     */
    public static void inOrderIterative(BTreeNode root, List<Integer> ret) {
        if (null == ret) {
            throw new IllegalArgumentException("Return list cannot be null");
        }

        if (null == root) {
            return;
        }

        Stack<BTreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BTreeNode node = stack.pop();
            if (node.getState() == State.VISITING) {
                ret.add(node.getVal());
            } else {
                node.setState(State.VISITING);

                if (null != node.getRight()) {
                    stack.push(node.getRight());
                }

                stack.push(node);

                if (null != node.getLeft()) {
                    stack.push(node.getLeft());
                }
            }
        }

    }

    /**
     * Find the height of the binary tree
     * <p>
     * This is a top down approach where we pass the depth of the parent to the children
     * and then compute the max height as we traverse various paths recursively
     *
     * @param root
     * @return
     */
    public static void heightTop2Bottom(BTreeNode root, Integer curDepth, Map<String, Integer> ret) {
        if (null == ret) {
            throw new IllegalArgumentException("Return map object cannot be null");
        }

        if (curDepth < 0) {
            throw new IllegalArgumentException("Current depth of the tree cannot be negative");
        }

        // initialize max height
        if (ret.containsKey(TREE_HEIGHT) == false) {
            ret.put(TREE_HEIGHT, 0);
        }

        if (null == root) {
            return;
        }

        if (curDepth > ret.get(TREE_HEIGHT)) {
            // if current depth is higher than previous height,
            // update the height of the tree to current depth
            ret.put(TREE_HEIGHT, curDepth);
        }

        heightTop2Bottom(root.getLeft(), curDepth + 1, ret);
        heightTop2Bottom(root.getRight(), curDepth + 1, ret);
    }

    /**
     * Find the height of the binary tree
     * <p>
     * This is a bottom to top approach where we compute the height using below formula:
     * height = max(height of left sub tree, height of right sub tree) + 1
     *
     * @param root
     * @return
     */
    public static int heightBottom2Top(BTreeNode root) {
        if (null == root) {
            return -1;
        }

        int leftHeight = heightBottom2Top(root.getLeft());
        int rightHeight = heightBottom2Top(root.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Check if a binary tree is balanced or not
     * <p>
     * If left or right subtree is not balance, the tree is not balanced
     * The difference between the height of the left subtree and the right subtree should be at most 1
     *
     * @param root
     * @return -1 if it is not balanced otherwise it returns a positive integer(i.e. height + 1)
     */
    public static boolean isBalanced(BTreeNode root) {
        int val = isBalancedChecker(root);
        return val != -1;
    }

    private static int isBalancedChecker(BTreeNode root) {
        if (null == root) {
            return 0;
        }

        int leftHeight = isBalancedChecker(root.getLeft());
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = isBalancedChecker(root.getRight());
        if (rightHeight == -1) {
            return -1;
        }

        int diff = Math.abs(leftHeight - rightHeight);
        if (diff > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Find the diameter of a binary tree
     * <p>
     * Diameter is the longest path from one node to another in the tree.
     * Diameter not necessarily goes through the tree root.
     *
     * @param root
     * @return
     */
    public static int diameter(BTreeNode root) {
        Map<String, Integer> ret = diameterHelper(root);
        if (ret.get(TREE_LONGEST_PATH) > 0) {
            return ret.get(TREE_LONGEST_PATH) - 1;
        }
        return 0;
    }

    private static Map<String, Integer> diameterHelper(BTreeNode root) {
        Map<String, Integer> ret = new HashMap<>();
        ret.put(TREE_HEIGHT, 0);
        ret.put(TREE_LONGEST_PATH, 0);
        if (null == root) {
            return ret;
        }

        Map<String, Integer> left = diameterHelper(root.getLeft());
        Map<String, Integer> right = diameterHelper(root.getRight());

        int curHeight = Math.max(left.get(TREE_HEIGHT), right.get(TREE_HEIGHT)) + 1;
        ret.put(TREE_HEIGHT, curHeight);

        int longestPathThroughNode = left.get(TREE_HEIGHT) + right.get(TREE_HEIGHT) + 1;
        int longestPathSubtree = Math.max(left.get(TREE_LONGEST_PATH), right.get(TREE_LONGEST_PATH));
        int longestPath = Math.max(longestPathThroughNode, longestPathSubtree);
        ret.put(TREE_LONGEST_PATH, longestPath);

        return ret;
    }

    /**
     * Find the lowest common ancestor of two nodes in a binary tree
     *
     * @param root root of the tree
     * @param x    target node X
     * @param y    target node Y
     * @return lowest common ancestor of x and y or null if it does not exists
     */
    public static BTreeNode lca(BTreeNode root, BTreeNode x, BTreeNode y) {
        if (null == root) {
            return null;
        }

        if (null == x || null == y) {
            throw new IllegalArgumentException("Target tree nodes x and y cannot be null");
        }

        int xHeight = BTrees.heightBottom2Top(x);
        int yHeight = BTrees.heightBottom2Top(y);

        while (xHeight < yHeight && null != x) {
            x = x.getParent();
            xHeight++;
        }

        while (yHeight < xHeight && null != y) {
            y = y.getParent();
            yHeight++;
        }

        while (x != y && null != x && null != y) {
            x = x.getParent();
            y = y.getParent();
        }

        if (x == y) {
            return x;
        }

        return null;
    }

    /**
     * Find all paths to children from root node
     *
     * @param root
     * @param curPath
     * @param paths
     */
    public static void allPaths(BTreeNode root, List<Integer> curPath, List<List<Integer>> paths) {
        if (null == root) {
            return;
        }

        if (null == curPath) {
            throw new IllegalArgumentException("Cur path cannot be null");
        }

        if (null == paths) {
            throw new IllegalArgumentException("Paths cannot be null");
        }

        curPath.add(root.getVal());

        // if this is a child node,
        // add the path to it to the list of paths
        if (root.getLeft() == null || root.getRight() == null) {
            // make a copy of the path
            List<Integer> p = new ArrayList<>();
            p.addAll(curPath);

            // add to the list
            paths.add(p);
        } else {
            allPaths(root.getLeft(), curPath, paths);
            allPaths(root.getRight(), curPath, paths);
        }


        curPath.remove(curPath.size() - 1);
    }

    /**
     * Reconstruct a binary tree from in-order and pre-order traversals
     */
    public static BTreeNode buildTree(List<Integer> inOrder, List<Integer> preOrder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inOrder.size(); i++) {
            inOrderMap.put(inOrder.get(i), i);
        }

        return reconstructHelper(inOrderMap,
                inOrder, 0, inOrder.size() - 1,
                preOrder, 0, preOrder.size() - 1);
    }

    private static BTreeNode reconstructHelper(Map<Integer, Integer> inOrderMap,
                                               List<Integer> inOrder, int inStart, int inEnd,
                                               List<Integer> preOrder, int preStart, int preEnd) {

        if ((inEnd - inStart) != (preEnd - preStart)) {
            throw new IllegalArgumentException("In-order and Pre-order traversal array must be of same length");
        }

        if (inStart > inEnd || preStart > preEnd) {
            return null;
        }

        int val = preOrder.get(preStart);
        BTreeNode root = new BTreeNode(val);

        int k = inOrderMap.get(val);
        int len = k - inStart;
        BTreeNode left = reconstructHelper(inOrderMap,
                inOrder, inStart, k - 1,
                preOrder, preStart + 1, preStart + len
        );
        BTreeNode right = reconstructHelper(inOrderMap,
                inOrder, k + 1, inEnd,
                preOrder, preStart + len + 1, preEnd
        );

        root.setLeft(left);
        root.setRight(right);

        return root;
    }

    /**
     * Reconstruct a binary tree from in-order and post-order traversals
     */
    public static BTreeNode buildTree2(List<Integer> inOrder, List<Integer> postOrder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inOrder.size(); i++) {
            inOrderMap.put(inOrder.get(i), i);
        }

        return reconstructHelper2(inOrderMap,
                inOrder, 0, inOrder.size() - 1,
                postOrder, 0, postOrder.size() - 1);
    }

    private static BTreeNode reconstructHelper2(Map<Integer, Integer> inOrderMap,
                                               List<Integer> inOrder, int inStart, int inEnd,
                                               List<Integer> postOrder, int postStart, int postEnd) {

        if ((inEnd - inStart) != (postEnd - postStart)) {
            throw new IllegalArgumentException("In-order and Post-order traversal array must be of same length");
        }

        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        int val = postOrder.get(postEnd);
        BTreeNode root = new BTreeNode(val);

        int k = inOrderMap.get(val);
        int len = k - inStart;
        BTreeNode left = reconstructHelper2(inOrderMap,
                inOrder, inStart, k - 1,
                postOrder, postStart, postStart + len - 1
        );
        BTreeNode right = reconstructHelper2(inOrderMap,
                inOrder, k + 1, inEnd,
                postOrder, postStart + len, postEnd - 1
        );

        root.setLeft(left);
        root.setRight(right);

        return root;
    }
}
