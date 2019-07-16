/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        boolean reverse=false;//a flag
        while(!q.isEmpty()){
            int size=q.size();
            ArrayList<Integer> currentLevel=new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode head=q.poll();
                currentLevel.add(head.val);
                if(head.left!=null){
                    q.add(head.left);
                }
                if(head.right!=null){
                    q.add(head.right);
                }
            }
            if(reverse){
                Collections.reverse(currentLevel);
                reverse=false;
            }else{
                reverse=true;
            }
            result.add(currentLevel);
        }
        return result;
    }
}
