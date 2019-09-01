//Given a sorted (increasing order) array, Convert it to create a binary search tree with minimal height.
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /*
     * @param A: an integer array
     * @return: A tree node
     */
    public TreeNode sortedArrayToBST(int[] A) {
        // write your code here
        if(A==null||A.length==0){
            return null;
        }
        return buildTree(A,0,A.length-1);
    }
    private TreeNode buildTree(int[] A, int start, int end){
        if(start>end){
            return null;
        }
        TreeNode root=new TreeNode(A[(start+end)/2]);
        TreeNode left=buildTree(A,start,(start+end)/2-1);
        TreeNode right=buildTree(A,(start+end)/2+1,end);
        root.left=left;
        root.right=right;
        return root;
    }
}

//Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
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
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {
        // write your code here
        if(head==null){
            return null;
        }
        if(head.next==null){
            return new TreeNode(head.val);
        }
        ListNode middlePrev=findMiddlePrev(head);
        ListNode middle=middlePrev.next;
        TreeNode root=new TreeNode(middle.val);
        root.right=sortedListToBST(middle.next);
        middlePrev.next=null;//断开
        root.left=sortedListToBST(head);
        return root;
    }
    //1->2->3->4->5 返回2   1->2->3->4 返回2
    private ListNode findMiddlePrev(ListNode head){
        ListNode slow=head;
        ListNode fast=head.next;
        // Always check if the fastNode can move forward for 2 steps
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
}
