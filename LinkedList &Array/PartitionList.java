//Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//You should preserve the original relative order of the nodes in each of the two partitions.
//idea：用两个list分别存小于x和大于x的，然后连接起来
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
     * @param head: The first node of linked list
     * @param x: An integer
     * @return: A ListNode
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        if(head==null){
            return null;
        }
        ListNode dummy1=new ListNode(0);
        ListNode dummy2=new ListNode(0);
        ListNode node1=dummy1;
        ListNode node2=dummy2;
        while(head!=null){
            if(head.val<x){
                node1.next=head;
                node1=head;
            }else{
                node2.next=head;
                node2=head;
            }
            head=head.next;
        }
        node2.next=null;
        node1.next=dummy2.next;
        return dummy1.next;
    }
}
