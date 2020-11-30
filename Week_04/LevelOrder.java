
//BFS
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(root == null){
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for(int i = 1;i<=currentLevelSize;++i){
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }
}


//DFS
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<List<Integer>>();
        }
        //用来存放结果
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(1,root,res);
        return res;
    }

    void dfs(int index,TreeNode root, List<List<Integer>> res){
        //假设res是[ [1],[2,3] ]， index是3，就再插入一个空list放到res中
        if(res.size()<index){
            res.add(new ArrayList<Integer>());
        }
        //将当前节点的值加入到res中，index代表当前层，假设index是3，节点值是99
        //res是[ [1],[2,3] [4] ]，加入后res就变为 [ [1],[2,3] [4,99] ]
        res.get(index - 1).add(root.val);
        //递归的处理左子树，右子树，同时将层数index+1
        if(root.left!=null){
            dfs(index+1,root.left,res);
        }
        if(root.right!=null){
            dfs(index+1,root.right,res);
        }
    }
}