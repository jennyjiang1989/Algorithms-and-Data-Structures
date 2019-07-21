//Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        
        ListNode prev=dummy;
        while(prev!=null){
            prev=reverseKNodes(prev,k);
        }
        
        return dummy.next;
    }
    //prev->n1->n2...->nk->nk+1
    //prev->nk->nk-1...->n1->nk+1
    //return n1
    //如果不够k个点 return null
    private ListNode reverseKNodes(ListNode prev, int k){
        ListNode node=prev;
        for(int i=0;i<k;i++){
            if(node==null){
                return null;
            }
            node=node.next;
        }
        if(node==null){
            return null;
        }
        ListNode nk=node;//找到nk
        
        //reverse (n1->n2...->nk)
        ListNode n1=prev.next;
        ListNode nkplus=nk.next;
        
        ListNode prev1=null;
        ListNode curr=n1;
        while(curr!=nkplus){
            ListNode temp=curr.next;
            curr.next=prev1;
            prev1=curr;
            curr=temp;
        }
        
        prev.next=nk;
        n1.next=nkplus;
        return n1;    
    }
}
