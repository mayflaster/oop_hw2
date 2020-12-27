package homework2;


/**
 *
 * Wrapping class for node class, that implements Comparable, mainly for PathFinder use.
 */
public class Node<N> implements Comparable<Node<N>> {

    /**
     * the actual class of the node that contains the data about
     * the node
     */
    private final N nodeData;

    /**
     * the cost of the path from some node to this node.
     */
    private final double cost;

    /**
     *
     * constructor for Node
     * @effects create new node with data of the true class N,
     * and cost/
     */
    public Node (N node, double cost){
        this.nodeData = node;
        this.cost=cost;
    }

    public N getNodeData(){return nodeData;}

    /**
     * Compares this with the specified object for order.
     * @return a negative integer, zero, or a positive integer as this
     * 		   object is respectively less than, equal to, or greater than
     *         the specified object .
     *
     */
    public int compareTo(Node<N> node) {
        double d= (this.cost - node.cost);
        if (d>0){
            return 1;
        }
        else if (d<0){
            return -1;
        }
        return 0;
    }

    /**
     *  equality operation.
     * @return true iff o.instaceOf(Node)
     *          && (this.nodeData.equals(o.nodeData))
     */
    public boolean equals(Object o) {
        if (o instanceof Node)
            return this.nodeData.equals(((Node<?>) o).nodeData);
        return false;
    }

    /**
     * Returns a hash code value for this.
     * @return a hash code value for this.
     */
    public int hashCode() {
        return this.nodeData.hashCode();
    }
}
