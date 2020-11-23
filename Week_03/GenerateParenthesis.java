class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        generate(list,0,0,n,"");
        return list;
    }

    private void generate(List<String> list, int left, int right, int n, String s){
        //terminator
        if(left == n && right == n){
            list.add(s);
            return;
        }
        //process current logic:left, right

        //drill down
        if(left < n){
            generate(list,left + 1, right, n, s + "(");
        }
        if(right < left){
            generate(list,left,right + 1, n, s + ")");
        }

        //reverse states
    }
}