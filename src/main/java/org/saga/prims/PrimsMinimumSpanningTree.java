package org.saga.prims;

import java.util.PriorityQueue;

import org.saga.graph.Edge;
import org.saga.graph.Graph;
import org.saga.graph.Node;

public class PrimsMinimumSpanningTree {

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
        System.out.println("minimumSpanningTreeByPrims : ");
        minimumSpanningTreeByPrims(graph, 7);
    }

    static void minimumSpanningTreeByPrims(Graph graph, int source) {
        PriorityQueue<Edge> sortedEdges = new PriorityQueue<>();
        Node[] nodes = graph.nodes;
        
        Node currentNode = nodes[source];
        currentNode.isVisited = true;
        sortedEdges.addAll(currentNode.edges);
        int minimumSpanningTreeWeight = 0;
        while(!sortedEdges.isEmpty()) {
            Edge edge = sortedEdges.poll();
            if(nodes[edge.fromIndex].isVisited && nodes[edge.toIndex].isVisited) {
                continue;
            }
            minimumSpanningTreeWeight += edge.weight;
            System.out.println("Edge added : " + edge);
            int neighbourNodeIndex = 0;
            if(nodes[edge.fromIndex].isVisited) {
                neighbourNodeIndex = edge.toIndex;
                nodes[edge.toIndex].isVisited = true;
            } else {
                neighbourNodeIndex = edge.fromIndex;
                nodes[edge.fromIndex].isVisited = true;
            }
            currentNode = nodes[neighbourNodeIndex];
            sortedEdges.addAll(currentNode.edges);
        }
        System.out.println("MST weight : " + minimumSpanningTreeWeight);
    }
}
