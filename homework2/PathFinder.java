package homework2;

import java.util.*;

public class PathFinder<N,P extends Path<N,P>> {

    /*
    PathFinder (){

    }
     */

    /**
     *
     * @requires g != null && starts != null && goals != null
     * @return find a path from one of the start nodes to one of the goal nodes,
     * with minimal weight.null if there is no such path or if starts or goals are empty.
     */
    P findPath (Graph<N> g, Set<P> starts,  Set<P> goals){

        Map<Node<N>,P> paths= new HashMap<>(); // map degenerated path that represent a node to a shortest path to this node.
        PriorityQueue<Node<N>> active = new PriorityQueue<>();
        for(P start:starts){
            Node<N> pathNode = new Node<>(start.getEnd(),start.getCost());
            active.add(pathNode);
            paths.putIfAbsent(pathNode,start);
        }
        Set<Node<N>> goalsNodesPaths = new HashSet<>();
        for(P goal: goals){
            Node<N> pathNode = new Node<>(goal.getEnd(),goal.getCost());
            goalsNodesPaths.add(pathNode);
        }

        Set<Node<N>> finished = new HashSet<>();

        Node<N> queueMin;
        P queueMinPath;
        while (active.size() != 0){
            queueMin = active.poll();
            queueMinPath = paths.get(queueMin);
            boolean cond= goalsNodesPaths.contains(queueMin);
            if (cond){
                return  queueMinPath;
            }
            Set<N> children = g.getChildren((N) queueMin.getNodeData());
            if (children == null) continue;
            for(N child: children){
                P updatedPath=queueMinPath.extend(child);
                Node<N> childNodePath = new Node<>(child,updatedPath.getCost());
                if(!active.contains(childNodePath) && !finished.contains(childNodePath) ){
                    paths.put(childNodePath,updatedPath);
                    active.add(childNodePath);
                }
            }
            finished.add(queueMin);
        }
        return null;

    }



}
