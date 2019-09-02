//A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
//Return a deep copy of the list.

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
//1st implementation
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
//2nd implementation
class Solution {
    public Node copyRandomList(Node head) {
        if(head==null){
            return null;
        }
        HashMap<Node,Node> map=new HashMap<>();//新老节点映射
        Node oldCurr=head;//用于遍历老节点
        Node dummy=new Node(0);
        Node newPrev=dummy;//用于构建新节点
        //第一遍遍历只考虑节点本身
        while(oldCurr!=null){
            Node newNode=new Node(oldCurr.val);
            map.put(oldCurr,newNode);
            //connect
            newPrev.next=newNode;
            newPrev=newNode;
            oldCurr=oldCurr.next;
        }
        Node oldIterator=head;
        Node newIterator=dummy.next;
        //第二遍遍历考虑random指针
        while(oldIterator!=null){
            Node oldRandom=oldIterator.random;
            Node newRandom=map.get(oldRandom);
            newIterator.random=newRandom;
            oldIterator=oldIterator.next;
            newIterator=newIterator.next;
        }
        return dummy.next;
    }
}
}
/*第一遍扫的时候巧妙运用next指针， 开始数组是1->2->3->4  。 然后扫描过程中 先建立copy节点 1->1`->2->2`->3->3`->4->4`, 然后第二遍copy的时候去建立边的copy， 拆分节点, 一边扫描一边拆成两个链表，这里用到两个dummy node。第一个链表变回  1->2->3 , 然后第二变成 1`->2`->3`  */
//No HashMap version
public class Solution {
    private void copyNext(RandomListNode head) {
        while (head != null) {
            RandomListNode newNode = new RandomListNode(head.label);
            newNode.random = head.random;
            newNode.next = head.next;
            head.next = newNode;
            head = head.next.next;
        }
    }

    private void copyRandom(RandomListNode head) {
        while (head != null) {
            if (head.next.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }

    private RandomListNode splitList(RandomListNode head) {
        RandomListNode newHead = head.next;
        while (head != null) {
            RandomListNode temp = head.next;
            head.next = temp.next;
            head = head.next;
            if (temp.next != null) {
                temp.next = temp.next.next;
            }
        }
        return newHead;
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        copyNext(head);
        copyRandom(head);
        return splitList(head);
    }
}
