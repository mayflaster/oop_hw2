package homework2;

import java.util.*;

public class PathFinder<N,P extends Path<N,P>> {

    PathFinder (){

    }

    /**
     *
     * @requires starts != null && goals != null
     * @return find a path from one of the start nodes to one of the goal nodes,
     * with minimal weight.null if there is no such path.
     */
    P findPath (Graph<N> g, Set<P> starts,  Set<P> goals){

        Map<Node<N>,P> paths= new HashMap<>(); // map degenerated path that represent a node to a shortest path to this node.
        //PriorityQueue<P> active = new PriorityQueue<>(starts);
        PriorityQueue<Node<N>> active = new PriorityQueue<>();
        for(P start:starts){
            Node<N> pathNode = new Node<>(start.getEnd(),start.getCost());
            active.add(pathNode);
            paths.putIfAbsent(pathNode,start);
        }
        Set<Node<N>> finished = new HashSet<>();

        Node<N> queueMin;
        P queueMinPath;
        while (active.size() != 0){
            queueMin = active.poll();
            queueMinPath = paths.get(queueMin);
            if (goals.contains(queueMin)){
                return  queueMinPath;
            }
            Set<N> children = g.getChildren((N) queueMin.getNodeData());
            for(N child: children){
                P updatedPath=queueMinPath.extend(child);
                Node<N> childNodePath = new Node<>(child,updatedPath.getCost());
                if(!active.contains(child) && !finished.contains(child) ){
                    paths.put(childNodePath,updatedPath);
                    active.add(childNodePath);
                }
            }
            finished.add(queueMin);
        }
        return null;

    }

    /*P findPath (Graph<N> g, Set<P> starts,  Set<P> goals){



        Map<P,P> paths= new HashMap<>(); // map degenerated path that represent a node to a shortest path to this node.
        PriorityQueue<P> active = new PriorityQueue<>(starts);
        Set<P> finished = new HashSet<>();

        P queueMin;
        P queueMinPath;
        while (active.size() != 0){
            queueMin = active.poll();
            queueMinPath = paths.get(queueMin);
            if (goals.contains(queueMin)){
                return  queueMinPath;
            }
            Set<N> children = g.getChildren(queueMin.getEnd());
            for(N child: children){
                P updatedPath=queueMinPath.extend(child);
                if(!active.contains(child) && !finished.contains(child) ){
                    // HOW TO CREATE A PATH FROM THIS NODE?!
                    //paths.put(nodeToPath.get(child),updatedPath);
                    //active.add(nodeToPath.get(child));
                }
            }
            finished.add(queueMin);
        }
        return null;

    }*/

}
