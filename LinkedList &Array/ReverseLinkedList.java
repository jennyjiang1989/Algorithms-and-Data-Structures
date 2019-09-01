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
        
        ListNode prevmNode=nodeIterator;
        ListNode mNode=nodeIterator.next;
        
        //初始化prev curr 套用reverse whole linked list
        ListNode prev=mNode;
        ListNode curr=mNode.next;
        for(int i=m;i<n;i++){
            if(curr==null){
                return null;
            }
            ListNode temp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=temp;
        }//3->2 4->3 prev:4 curr:5
        
        //connect
        mNode.next=curr;//2连接5
        prevmNode.next=prev;//1连接4
        
        return dummy.next;
    }
}
