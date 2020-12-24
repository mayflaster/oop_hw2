package homework2;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * A Graph is a a collection of nodes. some of the nodes could be connected
 * by edges, and also each edge has a direction. the Graph is mutable, but it's
 * Node's are muttable, and each one of them has an exclusive identifier, meaning
 * there arn't any pair of identical nodes exists in the Graph.
 * <p>
 * Graph has'nt got 2 edges that are identical.
 * meaning that 2 edges can connect the same nodes,
 * but in the oppesite direction.
 * <p>
 * <b>Implementation description</b>:<br>
 * an adjacency-List is the data-Structure used for representing
 * the graph. hence the class is very convinent for most algorithems.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *  adjacencyList : a dictionary in which the Key uniqe vertex V of the graph.
 *  the value is a collection of all the vertices U such that the edge V->U exists.
 *  
 * </pre>
 **/
public class Graph<Node/* extends Comparable<Node>*/> /*implements Iterable<Node> ,Comparable<Node> */{

    // Abstraction Function:
    //      Graph represents vertices and edges that conects those edges.
    //      the keys for the values adjacencyList are the vertices available.
    //      a value V which is mapped by some key K represents the outGoing edges of the vertex K,
    //      and each one of the elements in V is a destination vertex.


    // RepInvariant:
    //      adjacencyList != null.
    //      adjacencyList = { (key,value) | (key!=null)^(value!=null) }.
    //      key is generic type and value is a HashSet containing the same type.

      
    private void checkRep(){
        assert adjacencyList != null:
				"adjacencyList is null";

		for (Node node : adjacencyList.keySet()){
			assert node!=null:
					"null node found in adjacencyList keys";
        }
        
        for (Set<Node> set : adjacencyList.values() ){
			assert set!=null : "null set-of-edges found in adjacencyList values";
            for(Node node : set){
                assert node!=null : "null dest-node found in a set of edges";
            }
        }
        
        
        for (Node sourceNode : adjacencyList.keySet()){
			for( Node otherSource : adjacencyList.keySet() ){
                if (otherSource!=sourceNode){
                    assert !otherSource.equals(sourceNode) : "two nodes are the same";
                }
            }
            for(Node dest : adjacencyList.get(sourceNode)){
                for( Node otherDest : adjacencyList.keySet() ){
                    if (otherDest!=dest){
                        assert !dest.equals(otherDest) : "two nodes are the same";
                    }
                }
            }
        }

    }


    private final Map<Node, Set<Node>> adjacencyList = new HashMap<>();
    
    
    
    
    //private final String name;
    // //add the C'tor if more fields are added
    // public Graph(String name){
    //     if ( name==null ) return;
    //     this.name = name;
    // }



     /**
	 * Adds a new vertex to the graph if it isn't already exists.
	 * 
	 * @param node - an immutable generic type of vertex
	 * @return false if node==null, true otherwise.
     * @requires an immutable node
     * @effects puts a vertex in the graph with no edges
     *
     */
    public boolean addNode(Node node) {
        checkRep();
        if(node==null) return false;
        adjacencyList.putIfAbsent(node, new HashSet<>());
        checkRep();
        return true;
    }


    /**
  	 * Adds a new edge to the graph if there isn't such edge allready.
     * @requires immutable node
     * @effects 
     * @throws
     */


    /**
     * Adds a new edge to the graph if it isn't already exists.
     * 
     * @param parentNode - an immutable generic type of source vertex     
     * @param childNode - an immutable generic type of destination vertex   
     * @return false if (parentNode==null || childNode==null) , 
     *         true otherwise.
     * @requires immutable parentNode, immutable childNode.
     * @effects conects parentNode to childNode (and put either one
     *          of them in the graph also if needed)
     */
    public boolean addEdge(Node parentNode, Node childNode){
        checkRep();
        if(parentNode==null && childNode==null) return false;
        addNode(parentNode);
        addNode(childNode);
        adjacencyList.get(parentNode).add(childNode);
        checkRep();
        return true;
    }


    /**
     * gets all the vertices that exists in this Graph
     *
     * @return a Set containing every vertex Element in the Graph
     *         (an empty Set if the Graph is empty)
     */
    public Set<Node> getNodes(){
        checkRep();
        Set<Node> allNodes = Collections.unmodifiableSet( adjacencyList.keySet() );
        checkRep();
        return allNodes;
    }


    
    /**
     * Adds a new edge to the graph if it isn't already exists.
     * 
     * @param parentNode - an immutable generic type of source vertex     
     * @param childNode - an immutable generic type of destination vertex   
     * @return false if (parentNode==null || childNode==null) , 
     *         true otherwise.
     * @requires immutable parentNode, immutable childNode.
     * @effects conects parentNode to childNode (and put either one
     *          of them in the graph also if needed)
     */
    public Set<Node> getChildren(Node parent){
        checkRep();
        Set<Node> allChildren = Collections.unmodifiableSet( adjacencyList.get(parent) );
        checkRep();
        return allChildren;
    }



    /**
     * bla...bla...bla
     * 
     * @param sourceNodes - todo  
     * @param destNodes - todo
     * @return todo
     * @requires todo
     * @effects todo
     * 
     */
    public Set<Node> findShortestPath(Set<Node> sourceNodes, Set<Node> destNodes){
        checkRep();
        //Todo
        checkRep();
        return new HashSet<Node>();
    }





    //add this func if implementing iterable
    //public Iterator<Node> iterator(){
    // checkRep();
    //Set<Node> set = Collections.unmodifiableSet( adjacencyList.keySet() );
    // checkRep();
    //return set.iterator();
    //}




    //add this func if implementing comparable
    // public int compareTo(Path<?,?> p){
    //     //TODO
    // }





    // //testing the graph minimally
    // public static void main(String[] args){
    //     Graph<String> g1 = new Graph<>();
    //     assert( g1.addNode( null )==false ) 
    //     : "null-problem with node insertion";

    //     for(int i=0 ; i < 5 ; i++){
    //         g1.addNode( String.valueOf(i) );
    //         for(int j=0 ; j<i ; j++) g1.addEdge(String.valueOf(i), String.valueOf(j));
    //     }

    //     for(int i=0 ; i < 5 ; i++){
    //         g1.addNode( String.valueOf(i) );
    //         for(int j=0 ; j<i ; j++){
    //             assert (g1.getChildren(String.valueOf(i)).contains(String.valueOf(j))) 
    //             : "node insertion overrides edges";
    //         }
    //     }


    //     return;
    // }
}
