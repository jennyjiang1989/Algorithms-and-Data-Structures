//Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//You should preserve the original relative order of the nodes in each of the two partitions.
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
        ListNode node=head;
        ListNode node1=dummy1;
        ListNode node2=dummy2;
        while(node!=null){
            if(node.val<x){
                node1.next=new ListNode(node.val);
                node1=node1.next;
                node=node.next;
            }else{
                node2.next=new ListNode(node.val);
                node2=node2.next;
                node=node.next;
            }
        }
        node1.next=dummy2.next;
        return dummy1.next;
    }
}
