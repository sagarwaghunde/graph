package org.saga.dijkstra;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import org.saga.graph.Edge;
import org.saga.graph.Graph;
import org.saga.graph.Node;

class DijkstraUsingForLoop {
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
        System.out.println("Using forloop : ");
        calculateShortestDistances(graph, 6);
    }
    
    static void calculateShortestDistances(Graph graph, int sourceIndex) {
        Node[] nodes = graph.nodes;
        nodes[sourceIndex].distanceFromSource = 0;
        int currentNode = sourceIndex;
        for(int i = 0; i < nodes.length; i++) {
            nodes[currentNode].pathFromSource.add(currentNode);
            List<Edge> edgesFromCurrentNode = nodes[currentNode].edges;
            for(int edge = 0; edge < edgesFromCurrentNode.size(); edge++) {
                int neighbourIndex = edgesFromCurrentNode.get(edge).getNeighbourIndex(currentNode);
                if (!nodes[neighbourIndex].isVisited) {
                    int tentative = nodes[currentNode].distanceFromSource + edgesFromCurrentNode.get(edge).weight;
                    if (tentative < nodes[neighbourIndex].distanceFromSource) {
                        if(Integer.MAX_VALUE != nodes[neighbourIndex].distanceFromSource) {
                            nodes[neighbourIndex].pathFromSource.clear();
                        }
                        nodes[neighbourIndex].pathFromSource.addAll(nodes[currentNode].pathFromSource);
                        nodes[neighbourIndex].distanceFromSource = tentative;
                    }
                }
            }
            nodes[currentNode].isVisited = true;
            currentNode = getNextNode(nodes);
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
    
    static int getNextNode(Node[] nodes) {
        int shortDist = Integer.MAX_VALUE;
        int nextNode = 0;
        
        for(int i = 0; i < nodes.length; i++) {
            int currentDist = nodes[i].distanceFromSource;
            if(!nodes[i].isVisited && currentDist < shortDist) {
                shortDist = currentDist;
                nextNode = i;
            }
        }
        return nextNode;
    }
}
