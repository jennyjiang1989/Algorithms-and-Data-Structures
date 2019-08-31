//Given a list, rotate the list to the right by k places, where k is non-negative.
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
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        if(head==null||head.next==null){
            return head;
        }
        int length=getLength(head);
        k=k%length;
        //corner case
        if(k==0){
            return head;
        }
        
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        
        //(1 2 3 4 5) k=2 (4 5 1 2 3) 最后一个是3;
        //(1 2 3 4 5) k=3 (3 4 5 1 2) 最后一个是2
        //head移动length-k-1步得到新的最后一个节点
        //找到newLast
        ListNode newLast=head;
        for(int i=1;i<length-k;i++){
            if(newLast!=null){
                newLast=newLast.next;
            }
        }
        //得到newHead
        ListNode newHead=newLast.next;
        
        //继续遍历得到oldLast
        ListNode oldLast=newLast;
        while(oldLast.next!=null){
            oldLast=oldLast.next;
        }
    
        //Connect 
        dummy.next=newHead;
        oldLast.next=head;
        newLast.next=null;
        
        return dummy.next;
    }
    private int getLength(ListNode head){
        int length=0;
        while(head!=null){
            head=head.next;
            length++;
        }
        return length;
    }
}
