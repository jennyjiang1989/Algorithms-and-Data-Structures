//Implement an algorithm to delete a node in the middle of a singly linked list, given only access to that node.
//先把当前节点的值用下一个节点的值覆盖了，然后我们删除下一个节点即可
/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */
public class Solution {
    /*
     * @param node: the node in the list should be deleted
     * @return: nothing
     */
    public void deleteNode(ListNode node) {
        // write your code here
        if(node==null){
            return;
        }
        if(node.next==null){
            node=null;
            return;
        }
        node.val=node.next.val;
        node.next=node.next.next;
    }
}
