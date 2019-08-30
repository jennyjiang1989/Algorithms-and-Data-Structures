//Given a linked list and two values v1 and v2. Swap the two nodes in the linked list with values v1 and v2. 
//It's guaranteed there is no duplicate values in the linked list. 
//If v1 or v2 does not exist in the given linked list, do nothing.
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
     * @param head: a ListNode
     * @param v1: An integer
     * @param v2: An integer
     * @return: a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        // write your code here
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        
        ListNode node1Prev=null;
        ListNode node2Prev=null;
        ListNode curr=dummy;//用于遍历
        //找到node1Prev node2Prev
        while(curr.next!=null){
            if(curr.next.val==v1){
                node1Prev=curr;
            }else if(curr.next.val==v2){
                node2Prev=curr;
            }
            curr=curr.next;
        }
        //没有找到
        if(node1Prev==null || node2Prev==null){
            return head;
        }
        ListNode node1=node1Prev.next;
        ListNode node2=node2Prev.next;
        ListNode node1Next=node1.next;
        ListNode node2Next=node2.next;
        //v1 v2相邻并且v1在v2前面
        //node1Prev node1 node2 node2Next
        if(node2==node1Next){
            node1Prev.next=node2;
            node2.next=node1;
            node1.next=node2Next;
        //v1 v2相邻并且v2在v1前面
        //node2Prev node2 node1 node1Next
        }else if(node1==node2Next){
            node2Prev.next=node1;
            node1.next=node2;
            node2.next=node1Next;
        //v1 v2不相邻
        //node1Prev node1 node1Next node2Prev node2 node2Next
        }else{
            node1Prev.next=node2;
            node2.next=node1Next;
            node2Prev.next=node1;
            node1.next=node2Next;
        }
        return dummy.next;
    }
}
