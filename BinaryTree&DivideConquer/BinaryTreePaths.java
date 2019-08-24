//Given a binary tree, return all root-to-leaf paths.

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
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        List<String> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        //叶子节点
        if(root.left==null&&root.right==null){
            result.add(root.val+"");
        }
        List<String> left=binaryTreePaths(root.left);
        List<String> right=binaryTreePaths(root.right);
        for(String path:left){
            result.add(root.val+"->"+path);
        }
        for(String path:right){
            result.add(root.val+"->"+path);
        }
        return result;
    }
}
