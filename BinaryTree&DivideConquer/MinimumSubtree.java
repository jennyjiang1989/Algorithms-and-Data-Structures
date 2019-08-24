//Given a binary tree, find the subtree with minimum sum. Return the root of the subtree.
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
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    //全局变量
    private int minSum=Integer.MAX_VALUE;
    private TreeNode minRoot=null;
    
    public TreeNode findSubtree(TreeNode root) {
        // write your code here
        helper(root);
        return minRoot;
    }
    
    private int helper(TreeNode root){
        if(root==null){
            return 0;
        }
        //divide conquer
        int sum=helper(root.left)+helper(root.right)+root.val;
        //traverse
        if(sum<minSum){
            minSum=sum;
            minRoot=root;
        }
        return sum;
    }
}
