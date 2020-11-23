class Solution {
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0){
            return new ArrayList();
        }
        Map<Character, String> map = new HashMap<Character, String>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        List<String> res = new LinkedList<String>();
        search("",digits,0,res,map);
        return res;
    }

    private void search(String s, String digits, int i, List<String> res, Map<Character, String> map){
        //terminator
        if(i == digits.length()){
            res.add(s);
            return;
        }

        //process
        String letters = map.get(digits.charAt(i));
        for(int j = 0; j < letters.length(); j++){
            //drill down
            search(s+letters.charAt(j), digits, i+1, res, map);
        }

        //reverse
    }
}