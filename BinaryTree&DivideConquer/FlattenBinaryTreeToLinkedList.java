//Flatten a binary tree to a fake "linked list" in pre-order traversal. Here we use the right pointer in TreeNode as the next pointer in ListNode.
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
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // write your code here
        helper(root);
    }
    //flatten以root为根的树，返回最后一个节点
    private TreeNode helper(TreeNode root){
        if(root==null){
            return null;
        }
        TreeNode leftLast=helper(root.left);
        TreeNode rightLast=helper(root.right);
        
        if(leftLast!=null){
            leftLast.right=root.right;
            root.right=root.left;
            root.left=null;
        }
        if(rightLast!=null){
            return rightLast;
        }
        if(leftLast!=null){
            return leftLast;
        }
        return root;
    }
}
