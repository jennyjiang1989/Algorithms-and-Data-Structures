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
//非递归
public class Solution {
    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node=stack.pop();
            result.add(node.val);
            //first right
            if(node.right!=null){
                stack.push(node.right);
            }
            //second left
            if(node.left!=null){
                stack.push(node.left);
            }
        }
        return result;
    }
}

//分治算法
public class Solution {
    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        result.add(root.val);
        List<Integer> left=preorderTraversal(root.left);
        List<Integer> right=preorderTraversal(root.right);
        result.addAll(left);
        result.addAll(right);
        return result;
    }
}
//遍历
public class Solution {
    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> result=new ArrayList<>();
        preorder(root,result);
        return result;
    }
    //1.返回值为void 把root为根的preorder加入result里面
    private void preorder(TreeNode root, List<Integer> result){
        //3.出口
        if(root==null){
            return;
        }
        //2.拆解
        result.add(root.val);
        preorder(root.left,result);
        preorder(root.right,result);
    }
}
