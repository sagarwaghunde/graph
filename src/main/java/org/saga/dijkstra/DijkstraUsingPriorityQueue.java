package org.saga.dijkstra;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import org.saga.graph.Edge;
import org.saga.graph.Graph;
import org.saga.graph.Node;

class DijkstraUsingPriorityQueue {
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
        System.out.println("Using priority query : ");
        calculateShortestDistancesUsingPQ(graph, 6);
    }
    
    static void calculateShortestDistancesUsingPQ(Graph graph, int sourceIndex) {
        Node[] nodes = graph.nodes;
        nodes[sourceIndex].distanceFromSource = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(nodes[sourceIndex]);
        while(!pq.isEmpty()) {
            Node currentNode = pq.poll();
            currentNode.pathFromSource.add(currentNode.index);
            List<Edge> edgesFromCurrentNode = currentNode.edges;
            for(int edge = 0; edge < edgesFromCurrentNode.size(); edge++) {
                int neighbourIndex = edgesFromCurrentNode.get(edge).getNeighbourIndex(currentNode.index);
                if (!nodes[neighbourIndex].isVisited) {
                    int tentative = currentNode.distanceFromSource + edgesFromCurrentNode.get(edge).weight;
                    if (tentative < nodes[neighbourIndex].distanceFromSource) {
                        if(Integer.MAX_VALUE != nodes[neighbourIndex].distanceFromSource) {
                            nodes[neighbourIndex].pathFromSource.clear();
                            pq.remove(nodes[neighbourIndex]);
                        }
                        nodes[neighbourIndex].pathFromSource.addAll(currentNode.pathFromSource);
                        nodes[neighbourIndex].distanceFromSource = tentative;
                        pq.add(nodes[neighbourIndex]);
                    }
                }
            }
            currentNode.isVisited = true;
        }
        
        for(int i = 0; i< nodes.length; i++) {
            System.out.print(sourceIndex + " to " + i + " : " + nodes[i].distanceFromSource + " | Path is -> ");
            printPath(nodes[i].pathFromSource);
            System.out.println("\n");
        }
    }
    
    
    static void printPath(List<Integer> path) {
        for(Integer index : path) {
            System.out.print(index + " ");
        }
    }
}
