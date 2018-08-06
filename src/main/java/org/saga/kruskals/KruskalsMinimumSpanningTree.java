package org.saga.kruskals;

import java.util.PriorityQueue;

import org.saga.graph.Edge;
import org.saga.graph.Graph;
import org.saga.graph.Node;

public class KruskalsMinimumSpanningTree {

    public static void main(String[] args) {
        System.out.println("Hello");
        Edge[] edges = {
                new Edge(0, 2, 1), new Edge(0, 3, 4), new Edge(0, 4, 2),
                new Edge(0, 1, 3), new Edge(1, 3, 2), new Edge(1, 4, 3),
                new Edge(1, 5, 1), new Edge(2, 4, 1), new Edge(3, 5, 4),
                new Edge(4, 5, 2), new Edge(4, 6, 7), new Edge(4, 7, 2),
                new Edge(5, 6, 4), new Edge(6, 7, 5)
              };
        Graph graph = new Graph(edges);
        System.out.println("minimumSpanningTreeByKruskals : ");
        minimumSpanningTreeByKruskals(graph);
    }
    
    static void minimumSpanningTreeByKruskals(Graph graph) {
        PriorityQueue<Edge> sortedEdges = new PriorityQueue<>();
        DisjointSets sets = new DisjointSets();
        for(Node node : graph.nodes) {
            sets.makeSet(node);
        }
        for(Edge edge : graph.edges) {
            sortedEdges.add(edge);
        }
        Node[] nodes = graph.nodes;
        int minimumSpanningTreeWeight = 0;
        while(!sortedEdges.isEmpty()) {
            Edge edge = sortedEdges.poll();
            Node setOne = sets.findset(nodes[edge.fromIndex]);
            Node setTwo = sets.findset(nodes[edge.toIndex]);
            if(setOne == setTwo) {
                continue;
            }
            System.out.println("Edge added : " + edge);
            minimumSpanningTreeWeight += edge.weight;
            sets.union(edge.fromIndex, edge.toIndex);
        }
        System.out.println("MST weight : " + minimumSpanningTreeWeight);
    }
}
