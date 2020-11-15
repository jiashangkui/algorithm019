class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<Integer>();
        postorder(root,res);
        return res;
    }

    public void postorder(Node root, List<Integer> res){
        if(root == null){
            return;
        }
        if(root.children == null){
            return;
        }
        for(Node child:root.children){
            postorder(child,res);
        }
        res.add(root.val);
    }
