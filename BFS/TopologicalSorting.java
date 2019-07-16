/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public class Solution {
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result=new ArrayList<>();
        //step 1: count indegree
        HashMap<DirectedGraphNode,Integer> map=new HashMap<>();
        for(DirectedGraphNode node:graph){
            for(DirectedGraphNode neighbor:node.neighbors){
                if(map.containsKey(neighbor)){
                    map.put(neighbor,map.get(neighbor)+1);
                }else{
                    map.put(neighbor,1);
                }
            }
        }
        //Step 2: put start nodes (nodes with indegree = 0) into queue and result
        Queue<DirectedGraphNode> q=new LinkedList<>();
        for(DirectedGraphNode node:graph){
            //indegree == 0 
            if(!map.containsKey(node)){
                q.offer(node);
                result.add(node);
            }
        }
        //Step 3: BFS
        while (!q.isEmpty()){
            DirectedGraphNode node = q.poll();
            for(DirectedGraphNode n:node.neighbors){
                //indegree - 1
                map.put(n,map.get(n)-1);
                if(map.get(n)==0){
                    result.add(n);
                    q.offer(n);
                }
            }
        }
        //判断拓扑排序是否存在
        if(result.size()==graph.size()){
            return result;
        }
        return null;
    }
}
