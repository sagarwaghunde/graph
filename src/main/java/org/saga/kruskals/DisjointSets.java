package org.saga.kruskals;

import java.util.HashMap;
import java.util.Map;

import org.saga.graph.Node;

public class DisjointSets {
    public Map<Integer, Node> nodesByIndex = new HashMap<>();
    
    public void makeSet(Node node) {
        node.parent = node;
        nodesByIndex.put(node.index, node);
    }
    
    public Node findset(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        return findset(node.parent);
    }
    
    public void union(int indexOne, int indexTwo) {
        Node nodeOne = nodesByIndex.get(indexOne);
        Node nodeTwo = nodesByIndex.get(indexTwo);
        
        Node parentOne = findset(nodeOne);
        Node parentTwo = findset(nodeTwo);
        
        if(parentOne == parentTwo) {
            return;
        }
        if(indexOne < indexTwo) {
            parentTwo.parent = parentOne;
        } else {
            parentOne.parent = parentTwo;
        }
    }
}

