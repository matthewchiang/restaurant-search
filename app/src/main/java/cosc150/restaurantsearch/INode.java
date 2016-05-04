package cosc150.restaurantsearch;

import java.util.ArrayList;

public class INode extends Node{

    protected ArrayList<Node> children;

    /**
     * Default constructor for INode. Initializes children array.
     *
     * @param capacity
     * @param parent
     */
    INode(int capacity, Node parent) {
        super(capacity, parent);
        children = new ArrayList<Node>();
    }

    /**
     * Insert child at specified location in children array.
     *
     * @param index
     * @param child
     */
    public void insertChildAt(int index, Node child) {
        children.add(index, child);
    }

    /**
     * Delete child at specified location in children array.
     *
     * @param index
     */
    public void deleteChildAt(int index) {
        children.remove(index);
    }

    /**
     * Return child at specified location in children array.
     *
     * @param index
     * @return Node
     */
    public Node getChildAt(int index) {
        return children.get(index);
    }

    /**
     * Return children array.
     *
     * @return children
     */
    public ArrayList<Node> getChildren() {
        return children;
    }

    /**
     * Return location of child node that would or does contain a key.
     *
     * @param key
     * @return int
     */
    public int getLocation(Restaurant key) {
        int i = 0;
        while (i < keys.size() && key.restaurantRating >= keys.get(i).restaurantRating){
            i++;
        }
        return i;
    }

    /**
     * Insert key into Node.
     *
     * @param key
     */
    public void insertKey(Restaurant key) {
        boolean inserted = false;
        int i = keys.size();
        this.keys.add(new Restaurant());

        // Find proper insertion location.
        while(!inserted){
            if (i > 0 && key.restaurantRating < this.keys.get(i-1).restaurantRating){
                this.keys.set(i, this.keys.get(i-1));
                i--;
            }
            else{
                inserted = true;
            }
        }

        // Insert key.
        this.keys.set(i, key);
    }

    /**
     *  Split node when node reaches capacity.
     *
     */
    public void split() {
        // Create new internal node.
        INode toInsert = new INode(capacity, this.parent);

        // Move half of children/keys to new node.
        int i = this.keys.size() - 1;
        while (i >= capacity/2){
            toInsert.insertKey(this.keys.get(i));
            this.keys.remove(i);
            toInsert.insertChildAt(0,this.children.get(i+1));
            this.children.remove(i+1);
            i--;
        }

        // Ensure all children nodes have correct parent.
        for (int j = 0; j < ((INode)toInsert).children.size(); j++){
            ((INode)toInsert).children.get(j).setParent(toInsert);
        }

        // Ensure invariants
        toInsert.insertChildAt(0,this.children.get(this.children.size()-1));
        toInsert.children.get(0).parent = toInsert;
        this.children.remove(this.children.size()-1);
        this.keys.remove(this.keys.size()-1);

        // Add pointer to parent node.
        if (this.parent != null){
            this.parent.insertKey(toInsert.keys.get(0));
            ((INode)this.parent).insertChildAt(this.parent.getLocation(toInsert.keys.get(0)), toInsert);
        }

        // Insert level if necessary
        else {
            this.parent = new INode(capacity, null);
            toInsert.parent = this.parent;
            ((INode)this.parent).insertChildAt(0, this);
            ((INode)this.parent).insertChildAt(1, toInsert);
            this.parent.insertKey(toInsert.keys.get(0));
        }
    }

}
