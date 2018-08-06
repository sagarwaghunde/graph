package org.saga.graph;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
    public int index;
    public int distanceFromSource;
    public boolean isVisited;
    public Node parent;
    public List<Edge> edges = new ArrayList<>();
    public List<Integer> pathFromSource = new ArrayList<>();
    
    Node(int index) {
        this.index = index;
        distanceFromSource = Integer.MAX_VALUE;
        isVisited = false;
    }
    
    void addEdge(Edge edge) {
        edges.add(edge);
    }
    
    public String toString() {
        return this.index + " -> DistFrmSrc " + this.distanceFromSource;
    }

    @Override
    public int compareTo(Node other) {
        return this.distanceFromSource - other.distanceFromSource;
    }
}
