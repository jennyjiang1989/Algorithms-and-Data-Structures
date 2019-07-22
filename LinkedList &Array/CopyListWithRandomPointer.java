
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
class Solution {
    public Node copyRandomList(Node head) {
        if(head==null){
            return null;
        }
        HashMap<Node,Node> map=new HashMap<>();//新老节点映射
        Node oldCurr=head;//old list的当前访问节点 用于遍历
        Node dummy=new Node(0);//new list的dummy node
        Node newPrev=dummy;//用于遍历new list
        
        while(oldCurr!=null){
            Node newNode;
            if(map.containsKey(oldCurr)){
                newNode=map.get(oldCurr);
            }else{
                newNode=new Node(oldCurr.val);
                map.put(oldCurr,newNode);
            }
            newPrev.next=newNode;//connect
            
            if(oldCurr.random!=null){
                if(map.containsKey(oldCurr.random)){
                    newNode.random=map.get(oldCurr.random);
                }else{
                    newNode.random=new Node(oldCurr.random.val);
                    map.put(oldCurr.random,newNode.random);
                }
            }
            oldCurr=oldCurr.next;
            newPrev=newNode;
        }
        return dummy.next;
    } 
}
