package homework2;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * A Graph is a a collection of nodes. some of the nodes could be connected
 * by edges, and in addition each edge has a direction (a directed graph).
 * the Graph is mutable, but it's Nodes are muttable, and each one of them has an exclusive
 * identifier, meaning there arn't any pair of identical nodes exists in the Graph.
 * <p>
 * Graph has'nt got 2 edges that are identical.
 * meaning that 2 edges might connect the same 2 vertices,
 * but only in the oppesite direction (No parallel edges).
 * <p>
 * <b>Implementation description</b>:<br>
 * an adjacency-List is the data-Structure used for representing
 * the graph, hence the class is very convinent for most algorithems.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *  adjacencyList : a dictionary in which the Key is a uniqe vertex V of the graph.
 *  the value is a collection of all the vertices U such that the edge V->U exists.
 *
 * </pre>
 **/
public class Graph<N/* extends Comparable<Node>*/> /*implements Iterable<Node> ,Comparable<Node> */{

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

        for (N node : adjacencyList.keySet()){
            assert node!=null:
                    "null node found in adjacencyList keys";
        }

        for (Set<N> set : adjacencyList.values() ){
            assert set!=null : "null set-of-edges found in adjacencyList values";
            for(N node : set){
                assert node!=null : "null dest-node found in a set of edges";
            }
        }


        for (N sourceNode : adjacencyList.keySet()){
            for( N otherSource : adjacencyList.keySet() ){
                if (otherSource!=sourceNode){
                    assert !otherSource.equals(sourceNode) : "two nodes are the same";
                }
            }
            for(N dest : adjacencyList.get(sourceNode)){
                for( N otherDest : adjacencyList.keySet() ){
                    if (otherDest!=dest){
                        assert !dest.equals(otherDest) : "two nodes are the same";
                    }
                }
            }
        }

    }


    private final Map<N, Set<N>> adjacencyList = new HashMap<>();




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
     * @requires an immutable node
     * @modifies this
     * @effects puts a vertex in the graph with no edges
     *  and return false if node==null,or if node is already in the graph, and true otherwise.
     *
     */
    public boolean addNode(N node) {
        checkRep();
        if(node==null) return false;
        if (adjacencyList.putIfAbsent(node, new HashSet<>())!=null){
            checkRep();
            return false;
        }
        checkRep();
        return true;
    }




    /**
     * Adds a new edge to the graph if it isn't already exists.
     *
     * @param parentNode - an immutable generic type of source vertex
     * @param childNode - an immutable generic type of destination vertex
     *
     * @requires immutable parentNode, immutable childNode.
     * @modifies this
     * @effects connects parentNode to childNode (and put either one
     *          of them in the graph also if needed) and return false if (parentNode==null || childNode==null),
     *          or if there is already an edge from parentNode to childNode, true otherwise.
     *
     */
    public boolean addEdge(N parentNode, N childNode){
        checkRep();
        if(parentNode==null && childNode==null) return false;
        addNode(parentNode);
        addNode(childNode);
        if(!adjacencyList.get(parentNode).add(childNode)){
            checkRep();
            return false;
        }
        checkRep();
        return true;
    }



    /**
     * gets all the vertices that exists in this Graph
     *
     * @return a Set containing every vertex Element in the Graph
     *         (an empty Set if the Graph is empty)
     */
    public Set<N> getNodes(){
        checkRep();
        Set<N> allNodes = Collections.unmodifiableSet( adjacencyList.keySet() );
        checkRep();
        return allNodes;
    }



    /**
     * @requires immutable Node.
     * @return a Set containing every vertex Element that the parent node has edges to.
     *         (an empty Set if the the node has no edges out of him)
     *         null if the graph does not contains the parent node
     */
    public Set<N> getChildren(N parent){
        checkRep();
        if (!adjacencyList.containsKey(parent)){
            return null;
        }
        Set<N> allChildren = Collections.unmodifiableSet( adjacencyList.get(parent) );
        checkRep();
        return allChildren;
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