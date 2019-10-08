https://upload.wikimedia.org/wikipedia/commons/f/f7/Binary_tree.svg

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val){
        this.val=val;
        this.left=null;
        this.right=null;
    }
}
public List<Integer> traverse(TreeNode root){
    List<Integer> result=new ArrayList<>();
    if(root==null){
        return result;
    }
    List<List<Integer>> results=new ArrayList<>();//from up to bottom
    Queue<TreeNode> queue=new LinkedList<>();
    queue.offer(root);
    while(!queue.isEmpty()){
        int size=queue.size();
        List<TreeNode> currentLevel=new ArrayList<>();
        for(int i=0;i<size;i++){
            TreeNode head=queue.poll();
            currentLevel.add(head.val);
            if(head.left!=null){
                queue.offer(head.left);
            }
            if(head.right!=null){
                queue.offer(head.right);
            }
        }
        results.add(currentLevel);
    }
    for(int i=results.size()-1;i>=0;i--){
        List<Integer> current=results.get(i);
        result.addAll(current);
    }
    return result;
}
