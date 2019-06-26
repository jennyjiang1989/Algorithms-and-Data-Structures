//   1->2->3->4->N
//N<-1<-2<-3<-4
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
