//Given a binary tree, find the length of the longest consecutive sequence path.
//The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
//The longest consecutive path need to be from parent to child (cannot be the reverse).
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
     * @return: the length of the longest consecutive sequence path
     */
    private int longest=0;
    public int longestConsecutive(TreeNode root) {
        // write your code here
        helper(root);
        return longest;
    }
    //返回从root出发的longest consecutive sequence path length
    private int helper(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=helper(root.left);
        int right=helper(root.right);
        int subtreeLongest=1;//at lease we have root
        if(root.left!=null&&root.val+1==root.left.val){
            subtreeLongest=Math.max(subtreeLongest,left+1);
        }
        if(root.right!=null&&root.val+1==root.right.val){
            subtreeLongest=Math.max(subtreeLongest,right+1);
        }
        //traverse 
        longest=Math.max(longest,subtreeLongest);
        return subtreeLongest;
    }
}
