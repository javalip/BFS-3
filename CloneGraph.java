import java.util.*;

public class CloneGraph {

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    /**
     Approach. Idea is to perform BFS and create copy.
     take the starting node and make a copy of it. and add original node and copy is the hashmap as key and value.
     then add it to q.
     begin BSF.
     take the first elemnt out of q and get its neghbors.
     Iterate through list of neighbors.
     check if the clone is already in map. if not create copy
     and put in q.
     get copy of current node, the nassign cloned neighbor to copynode of current.
     at the end return the copy node.

     Time complexity - o(V+E)
     space complexity = O(V)
     */


    HashMap<Node, Node> map;

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        Node copyNode = clone(node);
        q.add(node);
        //BFS
        while(!q.isEmpty()){
            // current node
            Node curr = q.poll();
            // get current node neighbors
            List<Node> currneighbors = curr.neighbors;
            // iterate through neighbors and create clone of every neighbor.
            for(Node neighbor: currneighbors){
                if(!map.containsKey(neighbor)){
                    // if map doesnt contai nthe neighbor, add it to q to be processed later
                    q.add(neighbor);
                }
                //cloned neighbor
                Node clonedNeighbor = clone(neighbor);
                // Get the copy of current and assign copied Node's neighbor
                map.get(curr).neighbors.add(clonedNeighbor);
            }
        }
        return copyNode;
    }

    private Node clone(Node node) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node newnode = new Node(node.val);
        map.put(node, newnode);
        return newnode;
    }
}
