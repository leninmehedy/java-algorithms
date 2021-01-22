package org.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    private int val;
    private List<GraphNode> neighbours;
    private State state;

    public GraphNode(int val) {
        this.val = val;
        this.state = State.UNVISITED;
        this.neighbours = new ArrayList<>();
    }

    public int getVal() {
        return this.val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public List<GraphNode> getNeighbours() {
        return this.neighbours;
    }

    public void getNeighbours(List<GraphNode> neighbours) {
        this.neighbours = neighbours;
    }

    public void addNeighbour(GraphNode neighbour) {
        this.neighbours.add(neighbour);
    }

    public String toString() {
        return String.format("%d", this.val);
    }
}
