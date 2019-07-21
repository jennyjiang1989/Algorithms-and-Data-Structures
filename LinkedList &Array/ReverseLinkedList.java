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
