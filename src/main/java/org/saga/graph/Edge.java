package org.saga.graph;

public class Edge implements Comparable<Edge> {
    public int fromIndex;
    public int toIndex;
    public int weight;
    
    public Edge(int fromIndex, int toIndex, int weight) {
        super();
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.weight = weight;
    }
    
    public int getNeighbourIndex(int nodeIndex) {
        if (this.fromIndex == nodeIndex) {
            return this.toIndex;
        } else {
            return this.fromIndex;
        }
    }
    
    public String toString() {
        return this.fromIndex + " -> " + this.toIndex + " : " + this.weight;
    }

    @Override
    public int compareTo(Edge otherEdge) {
        return this.weight - otherEdge.weight;
    }
}
