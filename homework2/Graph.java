package homework2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * A Graph is a a collection of nodes. some of the nodes could be connected
 * by edges, and also each edge has a direction. the Graph is mutable.
 * <p>
 * Graph has'nt got 2 edges that are identical.
 * meaning that 2 edges can connect the same nodes,
 * but in the oppesite direction.
 * <p>
 * 
 * <p>
 * <b>Implementation direction</b>:<br>
 * 
 * <p>
 * 
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   
 * </pre>
 **/
public class Graph<Node> implements Iterable<Node> /*,Comparable<Node> */{

    // RepInvariant:
    //      adjacencyList!= null
    //      ..todo..


  	// Abstraction Function:
    //      ..todo..
      
    private void checkRep(){
        // TODO
    }


    private final Map<Node, Set<Node>> adjacencyList = new HashMap<>();
    //private final String name;
    
    
    

    /**
     * CreateGraph graphName
     */
    // public Graph(String name){
    //     if ( name==null ) return;
    //     this.name = name;
    // }



    /**
  	 * Adds a new node to the graph if not allready exists.
     * @requires immutable node
     * @effects 
     */
    public boolean addNode(Node node){
        checkRep();
        if(node==null) return false;
        adjacencyList.putIfAbsent(node, new HashSet<>());
        checkRep();
        return true;
    }



    public boolean addEdge(Node parentNode, Node childNode){
        checkRep();
        if(parentNode==null && childNode==null) return false;
        adjacencyList.get(parentNode).add(childNode);
        checkRep();
        return true;
    }



    public Set<Node> getNodes(){
        checkRep();
        return ImmutableSet.copyOf(adjacencyList.keySet());
        //fixme
    }

    public HashSet<Node> getChildren(){
        checkRep();
        return 
    }

    // public List<Node> findShortestPath(List<Node> sourceNodes, List<Node> destNodes){
    //     // TODO
    // }

    public Iterator<Node> iterator(){
        //TODO
    }

    // public int compareTo(Path<?,?> p){
    //     //TODO
    // }


}
