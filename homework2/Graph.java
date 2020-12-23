package homework2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
public class Graph<Node extends Cloneable> implements Iterable<Node> /*,Comparable<Node> */{

    // RepInvariant:
    //      adjacencyList!= null
    //      ..todo..


  	// Abstraction Function:
    //      ..todo..
      
    private void checkRep(){
        // TODO
    }


    //private final String name;
    private final Map<Node, Set<Node>> adjacencyList = new HashMap<>();
    



    /**
     * CreateGraph graphName
     */
    // public Graph(String name){
    //     this.name = name;
    // }



    /**
  	 * Adds a new node to the graph if not allready exists.
     * @requires
     * @effects 
     */
    public boolean addNode(Node node){
        if(node==null) return false;
        adjacencyList.putIfAbsent(node.clone(), new HashSet<>());
    }

    public void addEdge (Node parentNote, Node childNode){
        //TODO
    }


    public List<Node> getNodes (){
        //TODO
    }

    public List<Node> getChildren(){
        //TODO
    }

    public List<Node> findShortestPath (List<Node> sourceNodes, List<Node> destNodes){
        // TODO
    }

    public Iterator<Node> iterator(){
        //TODO
    }

    // public int compareTo(Path<?,?> p){
    //     //TODO
    // }


}
