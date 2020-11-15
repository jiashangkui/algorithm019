class Solution {
    public List<Integer> preorder(Node root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        preorder(root,res);
        return res;
        
    }

    public void preorder(Node root, List<Integer> res){
        if(root == null){
            return;
        }
        res.add(root.val);
        if(root.children == null){
            return;
        }
        for(Node child:root.children){
            preorder(child,res);
        }
    }
}