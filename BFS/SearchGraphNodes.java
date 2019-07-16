//Given a undirected graph, a node and a target, return the nearest node to the given node which value of it is target
//return NULL if you can't find
public class SearchGraphNodes {
	//graph: a list of Undirected graph node
  //values: a hash mapping
	public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
			                             Map<UndirectedGraphNode,Integer> values,
			                             UndirectedGraphNode node,
			                             int target){
		Queue<UndirectedGraphNode> queue=new LinkedList<>();
		HashSet<UndirectedGraphNode> set=new HashSet<>();
		queue.add(node);
		set.add(node);
		
		while(!queue.isEmpty()){
			UndirectedGraphNode head=queue.poll();
			if(values.get(head)==target){
				return head;
			}
			for(UndirectedGraphNode neighbor:head.neighbors){
				if(!set.contains(neighbor)){
					queue.add(neighbor);
					set.add(neighbor);
				}
			}
		}
		return null;
	}
}
