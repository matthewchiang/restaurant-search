package cosc150.restaurantsearch;

public class LNode extends Node {

    // next maintains the next leaf node that the current one points to
    protected Node next;

    /**
     * Initialize LNode with specified capacity and parent.
     *
     * @param capacity
     * @param parent
     */
    LNode(int capacity, Node parent) {
        super(capacity, parent);
        next = null;
    }

    /**
     * Set neighboring node.
     *
     * @param next
     */
    public void setNext(Node next) {
        assert(next instanceof LNode);
        this.next = next;
    }

    /**
     * Return neigboring Node
     *
     * @return Node
     */
    public Node getNext() {
        return next;
    }

    /**
     * Returns location of key in keys array if present.
     *
     * @param key
     * @return int
     */
    public int getLocation(Restaurant key) {
        return keys.indexOf(key);
    }

    /**
     * Insert key into Node.
     *
     * @param key
     */
    public void insertKey(Restaurant key) {

        // Find proper insertion location.
        boolean inserted = false;
        int i = keys.size();
        this.keys.add(new Restaurant());
        while(!inserted){
            if (i > 0 && key.restaurantRating < this.keys.get(i-1).restaurantRating){
                this.keys.set(i, this.keys.get(i-1));
                i--;
            }
            else{
                inserted = true;
            }
        }

        // Check if key is improperly inserted on "edge" of the wrong node.
        if(i == this.keys.size()-1 && this.next != null && this.next.keys.get(0).restaurantRating < key.restaurantRating){
            this.next.insertKey(key);
            this.keys.remove(i);
        }
        else {
            this.keys.set(i, key);
        }

        // Split node if full.
        if (this.isFull()){
            this.split();
        }
    }

    /**
     * Split node to ensure it does not exceed capacity.
     *
     */
    public void split() {

        // Create new LNode.
        LNode toInsert = new LNode(capacity, this.parent);
        toInsert.setNext(this.next);
        this.setNext(toInsert);

        // Insert half of elements into new LNode.
        int splitSize = this.keys.size()/2;
        for (int i = this.keys.size() - 1; i >= splitSize; i--){
            toInsert.insertKey(this.keys.get(i));
            this.keys.remove(i);
        }

        // Insert new key into parent.
        if (this.parent != null){
            this.parent.insertKey(toInsert.keys.get(0));
            ((INode)this.parent).insertChildAt(this.parent.getLocation(toInsert.keys.get(0)), toInsert);

            // Split parent if insertion causes parent to reach capacity.
            if (this.parent.isFull()) {
                this.parent.split();
            }
        }

        // Create new parent if necessary.
        else {
            this.parent = new INode(capacity, null);
            ((INode)this.parent).insertChildAt(0, this);
            ((INode)this.parent).insertChildAt(1, toInsert);
            toInsert.setParent(this.parent);
            this.parent.insertKey(toInsert.keys.get(0));
        }
    }

}
