//Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
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
     * @return: the root of the maximum average of subtree
     */
    private class ResultType{
        public int sum;
        public int size;
        public ResultType(int sum,int size){
            this.sum=sum;
            this.size=size;
        }
    }
    //全局变量
    private ResultType subtreeResult=null;
    private TreeNode maxAveSubtree=null;
    
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        helper(root);
        return maxAveSubtree;
    }
    private ResultType helper(TreeNode root){
        if(root==null){
            return new ResultType(0,0);
        }
        ResultType left=helper(root.left);
        ResultType right=helper(root.right);
        ResultType rootResult=new ResultType(left.sum+right.sum+root.val,left.size+right.size+1);
        //sum1/size1>sum2/size2 => sum1*size2>sum2*size1
        if(maxAveSubtree==null||rootResult.sum*subtreeResult.size>subtreeResult.sum*rootResult.size){
            subtreeResult=rootResult;
            maxAveSubtree=root;
        }
        return rootResult;
    }
}
