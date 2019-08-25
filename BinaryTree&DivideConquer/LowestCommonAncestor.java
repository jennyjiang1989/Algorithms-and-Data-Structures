//Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
public class Solution {
    // 在root为根的二叉树中找A,B的LCA:
    // 如果找到了就返回这个LCA
    // 如果只碰到A，就返回A
    // 如果只碰到B，就返回B
    // 如果都没有，就返回null
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }
        
        // Divide
        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);
        
        // Conquer
        if (left != null && right != null) {
            return root;
        } 
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }
}

//有parent指针
/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */
public class Solution {
    /*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // write your code here
        ParentTreeNode LCA=null;
        List<ParentTreeNode> pathA=findPath(A);
        List<ParentTreeNode> pathB=findPath(B);
        int indexA=pathA.size()-1;
        int indexB=pathB.size()-1;
        while(indexA>=0&&indexB>=0){
            if(pathA.get(indexA)!=pathB.get(indexB)){
                break;
            }
            LCA=pathA.get(indexA);
            indexA--;
            indexB--;
        }
        return LCA;
    }
    private List<ParentTreeNode> findPath(ParentTreeNode node){
        List<ParentTreeNode> result=new ArrayList<>();
        while(node!=null){
            result.add(node);
            node=node.parent;
        }
        return result;
    }
}
