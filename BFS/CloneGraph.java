/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */


public class Solution {
    /*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null){
            return node;
        }
        //step 1: use BFS to traverse the graph and get all nodes
        ArrayList<UndirectedGraphNode> nodes=getNodes(node);
        //step 2: copy nodes, store the old->new mapping info in a hash map
        HashMap<UndirectedGraphNode,UndirectedGraphNode> mapping=new HashMap<>();
        for(UndirectedGraphNode n:nodes){
            mapping.put(n,new UndirectedGraphNode(n.label));
        }
        //step 3: copy neighbors(edges)
        for(UndirectedGraphNode n:nodes){
            UndirectedGraphNode newNode=mapping.get(n);
            for(UndirectedGraphNode neighbor:n.neighbors){
                UndirectedGraphNode newNeighbor=mapping.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }
        return mapping.get(node);
    }
    private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node){
        Queue<UndirectedGraphNode> queue=new LinkedList<>();
        Set<UndirectedGraphNode> set=new HashSet<>();
        
        queue.offer(node);
        set.add(node);
        
        while(!queue.isEmpty()){
            UndirectedGraphNode head=queue.poll();
            for(UndirectedGraphNode neighbor:head.neighbors){
                if(!set.contains(neighbor)){
                    queue.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }
        return new ArrayList<UndirectedGraphNode>(set);
    }
}
