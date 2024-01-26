import org.w3c.dom.Node;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.*;

class AStarPathingStrategy implements PathingStrategy {
    //    A* Algorithm
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {

        List<Point> path = new LinkedList<Point>();
        HashMap<Point, NodePath> oList = new HashMap<Point, NodePath>();
        HashMap<Point, Point> cList = new HashMap<Point, Point>();
        NodePath cNode = new NodePath(start, 0, Point.distanceSquared(start, end), null);


        Comparator<NodePath> cNodes = Comparator.comparing(NodePath::getF).reversed().thenComparing(NodePath::getG).thenComparing(NodePath::getH).reversed();
        PriorityQueue<NodePath> nextStep = new PriorityQueue<NodePath>(1000, cNodes);


        oList.put(cNode.getCurrent(), cNode);

        while (!withinReach.test(cNode.getCurrent(), end)) {
            List<Point> neighbors = potentialNeighbors.apply(cNode.getCurrent()).filter(canPassThrough).collect(Collectors.toList());
            for (Point adjacent : neighbors) {
                if (!cList.containsKey(adjacent)) {
                    int newG = cNode.getG() + 1;
                    if (oList.containsKey(adjacent)) {
                        if (newG < oList.get(adjacent).getG()) {
                            NodePath betterN = new NodePath(adjacent, newG, Point.distanceSquared(adjacent, end), cNode.getCurrent());
                            oList.replace(adjacent, betterN);
                        }

                    } else { //    Add node to open list (replacing the node if there already was one in step b)
                        NodePath next = new NodePath(adjacent, newG, Point.distanceSquared(adjacent, end), cNode.getCurrent());
                        oList.put(adjacent, next);
                        nextStep.add(next);
                    }
                }

//
            }

            cList.put(cNode.getCurrent(), cNode.getPrev());
            oList.remove(cNode.getCurrent());
            cNode = nextStep.peek();
            try {
                nextStep.remove();
            } catch (Exception e) {
                return path;
            }
        }

        addPath(path, cNode, start, cList);
        return path;
    }


//        //    Choose/know starting and ending points of the path (passed in as parameters
//
//        List<Point> path = new ArrayList<>();
//        Queue<Node> openList = new PriorityQueue<Node>(Comparator.comparingInt(Node::getF));
//
//        //hash map to easily search for already visited points with O(1) time complexity
//        Map<Point,Node> closedHash = new HashMap<Point, Node>();
//        Map<Point,Node> openHash = new HashMap<Point, Node>();
//
//        //    Add start node to the open list and mark it as the current node
//        Node startNode = new Node(0, getHeuristic(start, end), getHeuristic(start, end), start, null);
//        openList.add(startNode);
//        openHash.put(start, startNode);
//        Node curNode = null;
//
//        //begin analyzing neighbors of current Node
//    //    Analyze all valid adjacent nodes that are not on the closed list.  For each valid neighbor:
//        while (!openList.isEmpty()){
//
//            curNode = openList.remove(); //    Choose a node from the open list with the smallest f value and make it the current node
//
//
//            if (withinReach.test(curNode.position, end)) //    Repeat until within reach of the goal or you can no longer search.
//                return createPath(path, curNode);
//
//            List<Point> validNeighbors = potentialNeighbors.apply(curNode.position).filter(canPassThrough).filter(
//                    p -> !p.equals(start) && !p.equals(end)).toList();
//
//            for (Point n : validNeighbors) {
//
//                if (!closedHash.containsKey(n)){
//                    int newG = curNode.g + 1; //    Determine distance from start node (g value)
////    g = Distance of current node from start (known NOT heuristic) + distance from cur node to adjacent
//                    if(openHash.containsKey(n)){ //    If the node is already on the open list:
//
//                        if(newG < openHash.get(n).g){
//                            Node betterN = new Node(newG, getHeuristic(n, end), getHeuristic(n, end)+ newG, n, curNode);
//                            openList.remove(openHash.get(n));
//                            openList.add(betterN);
//                            openHash.replace(n, betterN);
//                        }//    If the calculated g value is better than a previously calculated g value, replace the
//                        // old g value with the new one and proceed to c.  Otherwise, skip to step a for the next node.
//                        //    Estimate distance of adjacent node to the end point (h)
////    This is called the heuristic.  It can be Euclidean distance, Manhattan distance, etc.
////    Add g and h to get an f value
////    Save the prior node of this neighbor (prior node is ‘current node’)
//
//                    }
//
//                    else{ //    Add node to open list (replacing the node if there already was one in step b)
//                        Node newN = new Node(newG, getHeuristic(n, end), newG +getHeuristic(n, end), n, curNode);
//                        openList.add(newN);
//                        openHash.put(n, newN);
//                    }
//                }
//            }
//
//            closedHash.put(curNode.position, curNode); //    Move the current node to the closed list
//
//        }
//
//        return path;

    public void addPath(List<Point> mypath, NodePath mynode, Point mystart, HashMap<Point, Point> mycList) {
        mypath.add(0, mynode.getCurrent());
        Point add = mynode.getPrev();
        while (add != mystart) {
            mypath.add(0, add);
            add = mycList.get(add);
        }
    }
}

//    public int getHeuristic(Point current, Point goal){
//        return Point.distanceSquared(current,goal); }
//
//
//    public List<Point> createPath(List<Point> path, Node reached)
//    {
//        path.add(reached.position);
//
//        if(reached.prevNode == null)
//        {
//            Collections.reverse(path);
//            path.remove(0);
//            return path;
//        }
//
//        // recurse until prevNode doesn't exist'
//        return createPath(path, reached.prevNode);
//
//    }

//
//    class Node {
//        private int g;
//        private int h;
//        private int f;
//        private Node prevNode;
//        private Point position;
//
//        public Node (int g, int h, int f, Point position, Node prev_node){
//            this.g = g;
//            this.h = h;
//            this.f = f;
//            this.prevNode = prev_node;
//            this.position = position;
//        }
//
//
//        public int getH(){return h;}
//        public int getF(){return f;}
//        public int getG(){return g;}
//        public Point getPosition(){return position;}
//        public Node getPrevNode(){return prevNode;}
//
//        @Override
//        public String toString(){return "getX() = "+ this.position.getX() + " getY() = " + this.position.getY(); }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Node node = (Node) o;
//            return g == node.g && h == node.h && f == node.f && Objects.equals(prevNode, node.prevNode) && Objects.equals(position, node.position);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(g, h, f, prevNode, position);
//        }
//    }
//}