package org.algorithms.lenin.graphs;

import org.algorithms.lenin.trees.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class GraphsTest {
    @Test
    public void testDfs() {
        GraphNode graphNode1 = new GraphNode(1);
        GraphNode graphNode2 = new GraphNode(2);
        GraphNode graphNode3 = new GraphNode(3);
        GraphNode graphNode4 = new GraphNode(4);
        GraphNode graphNode5 = new GraphNode(5);
        GraphNode graphNode6 = new GraphNode(6);
        graphNode1.addNeighbour(graphNode2);
        graphNode1.addNeighbour(graphNode3);
        graphNode2.addNeighbour(graphNode4);
        graphNode2.addNeighbour(graphNode5);
        graphNode5.addNeighbour(graphNode6);

        List<GraphNode> expected = new ArrayList<>();
        expected.add(graphNode1);
        expected.add(graphNode3);
        expected.add(graphNode2);
        expected.add(graphNode5);
        expected.add(graphNode6);
        expected.add(graphNode4);

        Graphs gh = new Graphs();
        List<GraphNode> ret = gh.dfs(graphNode1);
        System.out.println(Arrays.toString(expected.toArray()));
        System.out.println(Arrays.toString(ret.toArray()));
        assertArrayEquals(expected.toArray(), ret.toArray());
    }

    @Test
    public void testDfsVisit() {
        GraphNode graphNode1 = new GraphNode(1);
        GraphNode graphNode2 = new GraphNode(2);
        GraphNode graphNode3 = new GraphNode(3);
        GraphNode graphNode4 = new GraphNode(4);
        GraphNode graphNode5 = new GraphNode(5);
        GraphNode graphNode6 = new GraphNode(6);
        graphNode1.addNeighbour(graphNode2);
        graphNode1.addNeighbour(graphNode3);
        graphNode2.addNeighbour(graphNode4);
        graphNode2.addNeighbour(graphNode5);
        graphNode5.addNeighbour(graphNode6);

        List<GraphNode> expected = new ArrayList<>();
        expected.add(graphNode1);
        expected.add(graphNode2);
        expected.add(graphNode4);
        expected.add(graphNode5);
        expected.add(graphNode6);
        expected.add(graphNode3);

        Graphs gh = new Graphs();
        List<GraphNode> visited = new ArrayList<>();
        gh.dfsVisit(graphNode1, visited);
        System.out.println(Arrays.toString(expected.toArray()));
        System.out.println(Arrays.toString(visited.toArray()));
        assertArrayEquals(expected.toArray(), visited.toArray());
    }

    @Test
    public void testBfs() {
        GraphNode graphNode1 = new GraphNode(1);
        GraphNode graphNode2 = new GraphNode(2);
        GraphNode graphNode3 = new GraphNode(3);
        GraphNode graphNode4 = new GraphNode(4);
        GraphNode graphNode5 = new GraphNode(5);
        GraphNode graphNode6 = new GraphNode(6);
        graphNode1.addNeighbour(graphNode2);
        graphNode1.addNeighbour(graphNode3);
        graphNode2.addNeighbour(graphNode4);
        graphNode2.addNeighbour(graphNode5);
        graphNode5.addNeighbour(graphNode6);

        List<GraphNode> expected = new ArrayList<>();
        expected.add(graphNode1);
        expected.add(graphNode2);
        expected.add(graphNode3);
        expected.add(graphNode4);
        expected.add(graphNode5);
        expected.add(graphNode6);
        Graphs gh = new Graphs();
        List<GraphNode> ret = gh.bfs(graphNode1);
        System.out.println(Arrays.toString(expected.toArray()));
        System.out.println(Arrays.toString(ret.toArray()));
        assertArrayEquals(expected.toArray(), ret.toArray());
    }

    @Test
    public void testTopologicalSortRecursive() {
        List<GraphNode> graphNodes = new ArrayList<>();
        GraphNode graphNode1 = new GraphNode(1);
        GraphNode graphNode2 = new GraphNode(2);
        GraphNode graphNode3 = new GraphNode(3);
        GraphNode graphNode4 = new GraphNode(4);
        GraphNode graphNode5 = new GraphNode(5);
        GraphNode graphNode6 = new GraphNode(6);
        graphNodes.add(graphNode1);
        graphNodes.add(graphNode2);
        graphNodes.add(graphNode3);
        graphNodes.add(graphNode4);
        graphNodes.add(graphNode5);

        graphNode1.addNeighbour(graphNode2);
        graphNode1.addNeighbour(graphNode4);
        graphNode2.addNeighbour(graphNode3);
        graphNode2.addNeighbour(graphNode5);
        graphNode2.addNeighbour(graphNode4);
        graphNode3.addNeighbour(graphNode5);

        Stack<GraphNode> expected = new Stack<>();
        expected.add(graphNode5);
        expected.add(graphNode3);
        expected.add(graphNode4);
        expected.add(graphNode2);
        expected.add(graphNode1);

        Graphs gh = new Graphs();
        Stack<GraphNode> sorted = gh.topologicalSortRecursive(graphNodes);
        System.out.println(Arrays.toString(expected.toArray()));
        System.out.println(Arrays.toString(sorted.toArray()));
        assertArrayEquals(expected.toArray(), sorted.toArray());
    }

    @Test
    public void testTopologicalSort() {
        List<GraphNode> graphNodes = new ArrayList<>();
        GraphNode graphNode1 = new GraphNode(1);
        GraphNode graphNode2 = new GraphNode(2);
        GraphNode graphNode3 = new GraphNode(3);
        GraphNode graphNode4 = new GraphNode(4);
        GraphNode graphNode5 = new GraphNode(5);
        GraphNode graphNode6 = new GraphNode(6);
        graphNodes.add(graphNode1);
        graphNodes.add(graphNode2);
        graphNodes.add(graphNode3);
        graphNodes.add(graphNode4);
        graphNodes.add(graphNode5);

        graphNode1.addNeighbour(graphNode2);
        graphNode1.addNeighbour(graphNode4);
        graphNode2.addNeighbour(graphNode3);
        graphNode2.addNeighbour(graphNode5);
        graphNode2.addNeighbour(graphNode4);
        graphNode3.addNeighbour(graphNode5);

        Stack<GraphNode> expected = new Stack<>();
        expected.add(graphNode4);
        expected.add(graphNode5);
        expected.add(graphNode3);
        expected.add(graphNode2);
        expected.add(graphNode1);

        Graphs gh = new Graphs();
        Stack<GraphNode> sorted = gh.topologicalSort(graphNodes);
        System.out.println(Arrays.toString(expected.toArray()));
        System.out.println(Arrays.toString(sorted.toArray()));
        assertArrayEquals(expected.toArray(), sorted.toArray());
    }

    @Test
    void inOrder() {
        List<TreeNode> nodes = new ArrayList<>();
        for(int i = 0; i <= 6; i++) {
            nodes.add(new TreeNode(i));
        }
        nodes.get(3).setLeft(nodes.get(2));
        nodes.get(3).setRight(nodes.get(4));

        nodes.get(2).setLeft(nodes.get(0));
        nodes.get(2).setRight(nodes.get(1));

        nodes.get(4).setLeft(nodes.get(5));
        nodes.get(4).setRight(nodes.get(6));
    }
    List<Integer> expInOrder = Arrays.asList(new Integer[]{0, 2, 1, 3, 5, 4, 6});
    List<Integer> ret = new ArrayList<>();
}