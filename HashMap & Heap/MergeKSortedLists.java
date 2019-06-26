import java.util.*;
class ListNode{
   int val;
   ListNode next;
   ListNode(int val){
      this.val=val;
      this.next=null;
   }  
}
public class MergeKSortedLists {
   //Divide and Conquer
   public ListNode merge1(List<ListNode> lists){
      if(lists==null || lists.size()==0){
         return null;
      }
      return helper(lists,0,lists.size()-1);
   }
   private ListNode helper(List<ListNode> lists, int start, int end){
      if (start==end){
         return lists.get(start);
      }
      int mid=start+(end-start)/2;
      ListNode left=helper(lists,start,mid);
      ListNode right=helper(lists,mid+1,end);
      return mergeTwoLists(left,right);
   }
   private ListNode mergeTwoLists(ListNode list1,ListNode list2){
      ListNode dummy=new ListNode(0);
      ListNode node=dummmy;//node is for the merged list
      while(list1!=null && list2!=null){
         if(list1.val<list2.val){
            node.next=list1;
            node=list1;
            list1=list1.next;
         }else{
            node.next=list2;
            node=list2;
            list2=list2.next;
         }
      }
      if(list1!=null){
         node.next=list1;
      }
      if(list2!=null){
         node.next=list2;
      }
      return dummy.next;
   }
   //Heap
   private Comparator<ListNode> listNodeComparator=new Comparator<ListNode>(){
      public int compare(ListNode n1, ListNode n2){
         return n1.val-n2.val;
      };
      public ListNode merge2(List<ListNode> lists){
		if(lists==null||lists.size()==0){
			return null;
		}
		Queue<ListNode> heap=new PriorityQueue<>(lists.size(),listNodeComparator);//两个参数 最小堆
		for(int i=0;i<lists.size();i++){
			if(lists.get(i)!=null){
				heap.add(lists.get(i));
			}
		}
		ListNode dummy=new ListNode(0);
		ListNode n=dummy;//用于移动
		while(!heap.isEmpty()){
			ListNode head=heap.poll();
			n.next=head;
			n=n.next;
			//下一个丢进去
			if(head.next!=null){
				heap.add(head.next);
			}
		}
		return dummy.next;
   }
}
