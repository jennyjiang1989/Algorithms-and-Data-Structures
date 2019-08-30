//Given a singly linked list L: L0 → L1 → … → Ln-1 → Ln
//reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

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
     * @param head: The head of linked list.
     * @return: nothing
     */
    public void reorderList(ListNode head) {
        // write your code here
        if(head==null||head.next==null){
            return;
        }
        ListNode mid=findMiddle(head);
        ListNode newHead=reverse(mid.next);
        mid.next=null;
        merge(head,newHead);
    }
    //1 2 3 4      1 2 3 4 5
    private ListNode findMiddle(ListNode head){
        ListNode slow=head;
        ListNode fast=head.next;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    private ListNode reverse(ListNode head){
        if(head==null){
            return null;
        }
        ListNode prev=null;
        ListNode curr=head;
        while(curr!=null){
            ListNode temp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=temp;
        }
        return prev;
    }
    //1 2    4 3
    private void merge(ListNode head,ListNode newHead){
        if(head==null||newHead==null){
            return;
        }
        ListNode dummy=new ListNode(0);
        int index=0;
        while(head!=null&&newHead!=null){
            if(index%2==0){
                dummy.next=head;
                head=head.next;
            }else{
                dummy.next=newHead;
                newHead=newHead.next;
            }
            dummy=dummy.next;
            index++;
        }
        if(head!=null){
            dummy.next=head;
        }
        if(newHead!=null){
            dummy.next=newHead;
        }
    }
}
