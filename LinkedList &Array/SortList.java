//Sort a linked list in O(n log n) time using constant space complexity.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode mid=findMiddle(head);
        ListNode right=sortList(mid.next);//递归 Merge Sort
        mid.next=null;
        ListNode left=sortList(head);//递归 Merge Sort
        return merge(left,right);
    }
    private ListNode findMiddle(ListNode head){
        ListNode slow=head;
        ListNode fast=head.next;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    private ListNode merge(ListNode head1,ListNode head2){
        ListNode dummy=new ListNode(0);
        ListNode prev=dummy;//iterator
        while(head1!=null&&head2!=null){
            if(head1.val<head2.val){
                prev.next=head1;
                prev=prev.next;
                head1=head1.next;
            }else{
                prev.next=head2;
                prev=prev.next;
                head2=head2.next;
            }
            
        }
        if(head1!=null){
            prev.next=head1;
        }else{
            prev.next=head2;
        }
        return dummy.next;
    }
}
