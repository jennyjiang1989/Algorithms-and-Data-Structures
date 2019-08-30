//   1->2->3->4->N
//N<-1<-2<-3<-4

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseLinkedList {
	public ListNode reverse(ListNode head){
		//删除节点的时候也需要prev指针跟curr指针
		ListNode prev=null;
		ListNode curr=head;
		while(curr!=null){
			ListNode temp=curr.next;
			curr.next=prev;
			//移动
			prev=curr;
			curr=temp;
		}
		return prev;//此时curr=null		
	}
}


//Reverse a linked list from position m to n.
//Input: 1->2->3->4->5->NULL, m = 2 and n = 4 Output: 1->4->3->2->5->NULL.
/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param head: ListNode head is the head of the linked list 
     * @param m: An integer
     * @param n: An integer
     * @return: The head of the reversed ListNode
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write your code here
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        
        ListNode nodeIterator=dummy;
        for(int i=1;i<m;i++){
            if(nodeIterator==null){
                return null;
            }
            nodeIterator=nodeIterator.next;
        }//得到mNode的prev node
        //prev->m->m+1...->n->n+1
        //prev->n->n-1...->m->n+1
        ListNode prevmNode=nodeIterator;
        ListNode mNode=nodeIterator.next;
        //初始化
        ListNode nNode=mNode;
        ListNode postnNode=mNode.next;
	//1  2 3 4  null    1  4 3 2  null
        for(int i=m;i<n;i++){
            if(postnNode==null){
                return null;
            }
            ListNode temp=postnNode.next;
            postnNode.next=nNode;
            nNode=postnNode;
            postnNode=temp;
        }
        mNode.next=postnNode;
        prevmNode.next=nNode;
        
        return dummy.next;
    }
}
