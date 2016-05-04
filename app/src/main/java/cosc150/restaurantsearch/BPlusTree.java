package cosc150.restaurantsearch;

import java.io.*;
import java.util.*;

public class BPlusTree {
    private Node root;

    BPlusTree(int capacity) {
        root = new LNode(capacity, null);
    }

    public boolean isEmpty() {
        if (root.getKeys().size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    // Return the leaf node that contains the key
    // Return null if the key is not in the tree
    public Node find(Restaurant key) {
        Node result = root;

        // Descend tree until reach LNode.
        while(result instanceof INode){
            INode toDescend = (INode)result;
            result = toDescend.getChildAt(toDescend.getLocation(key));
        }
        result = (LNode)result;

        // Move left if not in found node.
        // Done to confirm that element assumed to be in rightmost child not in leftmost child of next leaf node.
        while (result != null && (result.keys.size() == 0 || (result.getLocation(key) == -1 && result.keys.get(0).restaurantRating < key.restaurantRating))){
            if (result != null){
                result = ((LNode)result).next;
            }
        }

        // Set result to null if not found.
        if (result != null && result.keys.get(0).restaurantRating > key.restaurantRating){
            result = null;
        }
        return result;
    }

    public void insert(Restaurant key) {

        // Descend down tree to find insertion point.
        Node toInsert = root;
        while(toInsert instanceof INode){
            INode toDescend = (INode)toInsert;
            toInsert = toDescend.getChildAt(toDescend.getLocation(key));
        }

        // Insert element into LNode
        toInsert = (LNode)toInsert;
        toInsert.insertKey(key);

        // Move root up if insertion increased tree height.
        if(root.getParent() != null){
            root = root.getParent();
        }
    }

    public void printLeaves() {
        // Navigate to leftmost LNode.
        Node toIterate = root;
        while (toIterate instanceof INode){
            toIterate = ((INode)toIterate).children.get(0);
        }

        int count = 0;
        System.out.println("The ratings are: ");

        // Iterate through LNodes, from left to right, storing keys count.
        while (toIterate != null) {
            count += toIterate.keys.size();
            for (int i = 0; i < toIterate.keys.size(); i++) {
                System.out.println(toIterate.keys.get(i).restaurantRating);
            }
            toIterate = ((LNode)toIterate).next;
        }
        System.out.println("\nCount: " + count);
    }

}