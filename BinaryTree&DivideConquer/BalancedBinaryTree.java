//Given a binary tree, determine if it is height-balanced.
//For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

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
class ResultType{
    public boolean isBalanced;
    public int depth;
    public ResultType(boolean isBalanced, int depth){
        this.isBalanced=isBalanced;
        this.depth=depth;
    }
}

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    //左右子树都是balanced,并且高度差<=1
    public boolean isBalanced(TreeNode root) {
        // write your code here
        ResultType result=helper(root);
        return result.isBalanced;
    }
    private ResultType helper(TreeNode root){
        if(root==null){
            return new ResultType(true,0);
        }
        ResultType left=helper(root.left);
        ResultType right=helper(root.right);
        int depth=Math.max(left.depth,right.depth)+1;
        
        //subtree not balance
        if(left.isBalanced==false||right.isBalanced==false){
            return new ResultType(false,-1);
        }
        //root not balance
        if(Math.abs(left.depth-right.depth)>1){
            return new ResultType(false,-1);
        }
        return new ResultType(true,depth);
    }
}
