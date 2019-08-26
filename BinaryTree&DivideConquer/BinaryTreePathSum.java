//Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.
//A valid path is from root node to any of the leaf nodes.

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
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> results=new ArrayList<>();
        if(root==null){
            return results;
        }
        List<Integer> path=new ArrayList<>();//current path
        path.add(root.val);//添加root节点
        helper(path,root,root.val,target,results);
        return results;
    }
    //"sum": the sum of each node from root to current node.
    private void helper(List<Integer> path, TreeNode root, int sum, int target, List<List<Integer>> results){
        //叶子节点
        if(root.left==null&&root.right==null){
            if(sum==target){
                //wrong: results.add(path);
                results.add(new ArrayList<Integer>(path));
                return;
            }
        }
        if(root.left!=null){
            path.add(root.left.val);
            helper(path,root.left,sum+root.left.val,target,results);
            path.remove(path.size()-1);
        }
        if(root.right!=null){
            path.add(root.right.val);
            helper(path,root.right,sum+root.right.val,target,results);
            path.remove(path.size()-1);
        }
    }
}
