package org.saga.graph;

public class Graph {
    public Node[] nodes;
    public int totalNodes;
    public Edge[] edges;
    public int totalEdges;
    
    public Graph(Edge[] edges) {
        this.edges = edges;
        this.totalEdges = edges.length;
        this.totalNodes = getTotalNodes();
        nodes = new Node[totalNodes];
        for(int i = 0; i < totalNodes; i++) {
            nodes[i] = new Node(i);
        }
        for(Edge edge : this.edges) {
            nodes[edge.fromIndex].addEdge(edge);
            nodes[edge.toIndex].addEdge(edge);
        }
    }
    
    int getTotalNodes() {
        int totalNodes = 0;
        for(Edge edge : edges) {
            if(edge.fromIndex > totalNodes) {
                totalNodes = edge.fromIndex;
            }
            if(edge.toIndex > totalNodes) {
                totalNodes = edge.toIndex;
            }
        }
        return ++totalNodes;
    }
}
