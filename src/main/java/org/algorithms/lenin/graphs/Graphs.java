package org.algorithms.lenin.graphs;

import java.util.*;

public class Graphs {
    public List<GraphNode> dfs(GraphNode root) {
        Stack<GraphNode> stack = new Stack<>();
        List<GraphNode> result = new ArrayList<>();

        root.setState(State.VISITING);
        stack.push(root);

        while(!stack.isEmpty()) {
            GraphNode n = stack.pop();

            // process
            result.add(n);

            for (GraphNode ne : n.getNeighbours()) {
                if (ne.getState() == State.UNVISITED) {
                    ne.setState(State.VISITING);
                    stack.push(ne);
                }
            }

            n.setState(State.VISITED);
        }

        return result;
    }

    public List<GraphNode> bfs(GraphNode root) {
        Queue<GraphNode> queue = new ArrayDeque<>();
        List<GraphNode> result = new ArrayList<>();

        root.setState(State.VISITING);
        queue.add(root);

        while(!queue.isEmpty()) {
            GraphNode n = queue.remove();

            // process
            result.add(n);

            for (GraphNode ne : n.getNeighbours()) {
                if (ne.getState() == State.UNVISITED) {
                    ne.setState(State.VISITING);
                    queue.add(ne);
                }
            }

            n.setState(State.VISITED);
        }

        return result;
    }

    public void dfsVisit(GraphNode graphNode, List<GraphNode> visited) {
        graphNode.setState(State.VISITING);
        visited.add(graphNode);
        for (GraphNode nb : graphNode.getNeighbours()) {
            if (nb.getState() == State.UNVISITED) {
                dfsVisit(nb, visited);
            }
        }
        graphNode.setState(State.VISITED);
    }

    public void dfsTopologicalRecursive(GraphNode graphNode, Stack<GraphNode> sorted) {
        graphNode.setState(State.VISITING);
        for (GraphNode nb : graphNode.getNeighbours()) {
            if (nb.getState() == State.UNVISITED) {
                dfsTopologicalRecursive(nb, sorted);
            }
        }
        graphNode.setState(State.VISITED);
        sorted.push(graphNode);
    }

    public Stack<GraphNode> topologicalSortRecursive(List<GraphNode> graphNodes) {
        Stack<GraphNode> sorted = new Stack<>();

        for (GraphNode graphNode : graphNodes) {
            if (graphNode.getState() == State.UNVISITED) {
                dfsTopologicalRecursive(graphNode, sorted);
            }
        }

        return sorted;
    }

    public void dfsTopological(GraphNode graphNode, Stack<GraphNode> sorted) {
        Stack<GraphNode> stack = new Stack<>();
        graphNode.setState(State.VISITING);
        stack.add(graphNode);
        while (!stack.isEmpty()) {
            GraphNode n = stack.peek();
            if (n.getState() == State.VISITING) {
                for (GraphNode nb : n.getNeighbours()) {
                    if (nb.getState() == State.UNVISITED) {
                        System.out.println(nb);
                        nb.setState(State.VISITING);
                        stack.push(nb);
                    }
                }
                n.setState(State.VISITED);
            } else {
                sorted.push(stack.pop());
            }
        }
    }

    public Stack<GraphNode> topologicalSort(List<GraphNode> graphNodes) {
        Stack<GraphNode> sorted = new Stack<>();

        for (GraphNode graphNode : graphNodes) {
            if (graphNode.getState() == State.UNVISITED) {
                dfsTopological(graphNode, sorted);
            }
        }

        return sorted;
    }

}
