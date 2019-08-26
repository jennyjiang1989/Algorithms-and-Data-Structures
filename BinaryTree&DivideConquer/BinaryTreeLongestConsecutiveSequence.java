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

//The path could be start and end at any node in the tree
public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    private class ResultType{
        public int max_length;
        public int max_down;
        public int max_up;
        public ResultType(int max_length,int max_down,int max_up){
            this.max_length=max_length;
            this.max_down=max_down;
            this.max_up=max_up;
        }
    }
    public int longestConsecutive2(TreeNode root) {
        // write your code here
        return helper(root).max_length;
    }
    private ResultType helper(TreeNode root){
        if(root==null){
            return new ResultType(0,0,0);
        }
        ResultType left=helper(root.left);
        ResultType right=helper(root.right);
        
        int down=0,up=0;
        if(root.left!=null&&root.val-1==root.left.val){
            down=Math.max(down,left.max_down+1);
        }
        if(root.left!=null&&root.val+1==root.left.val){
            up=Math.max(up,left.max_up+1);
        }
        if(root.right!=null&&root.val-1==root.right.val){
            down=Math.max(down,right.max_down+1);
        }
        if(root.right!=null&&root.val+1==root.right.val){
            up=Math.max(up,right.max_up+1);
        }
        int length=down+1+up;
        length=Math.max(length,Math.max(left.max_length,right.max_length));
        return new ResultType(length,down,up);
    }
}
