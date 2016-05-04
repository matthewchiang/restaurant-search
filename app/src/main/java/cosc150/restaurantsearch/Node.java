package cosc150.restaurantsearch;

import java.util.ArrayList;

import cosc150.restaurantsearch.Restaurant;

public abstract class Node {
    protected int capacity;
    protected Node parent;

    protected ArrayList<Restaurant> keys;

    /**
     * Initializes Node to specified capacity and parent.
     *
     * @param capacity
     * @param parent
     *
     */
    Node(int capacity, Node parent) {
        this.capacity = capacity;
        this.keys = new ArrayList<Restaurant>();
        this.parent = parent;
    }

    /**
     * Checks if keys size is equal to or greater than capacity.
     *
     */
    public boolean isFull() {
        return keys.size() >= capacity;
    }

    /**
     * Checks if node's key size is less than or equal to half of capacity.
     *
     */
    public boolean isHalfFull() {
        return keys.size() <= capacity / 2;
    }

    /**
     * Returns node capacity.
     *
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns element count of keys.
     *
     */
    public int getSize() {
        return keys.size();
    }

    /**
     * Returns keys array
     *
     */
    public ArrayList<Restaurant> getKeys() {
        return keys;
    }

    /**
     * Returns parent of Node.
     *
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets parent of Node.
     *
     * @param parent
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Prints keys of Node.
     * Code does not work for restaurant.
     *
     public void printKeys(){
     for (int i = 0; i < keys.size(); i++){
     System.out.print(keys.get(i) + " ");
     }
     System.out.print("   ");
     }
     */
    // getLocation returns the index in the key list that matches the key
    // For LNodes, it returns i, the index of the key such that keys[i] == key, and -1 if the key cannot be found
    // For INodes, it returns i, the index of the key such that keys[i-1] <= key < keys[i]; note the boundary conditions
    abstract public int getLocation(Restaurant toSearch);

    // insertKey inserts the restaurant into the key list
    abstract public void insertKey(Restaurant toInsert);

    // split is called when a key list overflows (i.e., it exceeds its capacity)
    // split splits a node into two nodes and pushes up a key to the parent node
    abstract public void split();

    // OPTIONAL:
    //
    // deleteKey deletes the key from the key list
    // deleteKey return 0 if the deletion succeeds; or -1 when it fails
    // abstract public void deleteKey(int key);
}
