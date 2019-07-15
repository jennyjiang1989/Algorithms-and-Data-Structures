public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        if(n==0){
            return false;
        }
        if(edges.length!=n-1){
            return false;
        }
        HashMap<Integer,Set<Integer>> graph=initializeGraph(n,edges);
        //bfs
        Queue<Integer> queue=new LinkedList<>();
        Set<Integer> set=new HashSet<>();
        
        queue.offer(0);
        set.add(0);
        while(!queue.isEmpty()){
            int node=queue.poll();
            for(Integer neighbor:graph.get(node)){
                if(set.contains(neighbor)){
                    continue;
                }
                queue.offer(neighbor);
                set.add(neighbor);
            }
        }
        return (set.size()==n);
    }
    private HashMap<Integer, Set<Integer>> initializeGraph(int n, int[][] edges){
        HashMap<Integer, Set<Integer>> graph=new HashMap<>();
        for(int i=0;i<n;i++){
            graph.put(i,new HashSet<Integer>());
        }
        for(int i=0;i<edges.length;i++){
            int u=edges[i][0];
            int v=edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }
}
