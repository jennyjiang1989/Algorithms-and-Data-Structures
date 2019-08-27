//Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth 
//(e.g., if you have a tree with depth D, you'll have D linked lists).

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
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param root the root of binary tree
     * @return a lists of linked list
     */
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        // Write your code here
        List<ListNode> results=new ArrayList<>();
        if(root==null){
            return results;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()){
            ListNode dummy=new ListNode(0);
            ListNode nodeIterator=dummy;
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode head=queue.poll();
                
                nodeIterator.next=new ListNode(head.val);
                nodeIterator=nodeIterator.next;
                
                if(head.left!=null){
                    queue.add(head.left);
                }
                if(head.right!=null){
                    queue.add(head.right);
                }
            }
            nodeIterator.next=null;//do not forget
            results.add(dummy.next);
        }
        return results;
    }
}
