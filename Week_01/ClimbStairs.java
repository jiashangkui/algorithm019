class Solution {
    public int climbStairs(int n) {
        if (n <= 2){
            return n;
        }
        int a1 = 1, a2 = 2, a3 = 3;
        for (int i=3; i < n + 1; ++i){
            a3 = a1 + a2;
            a1 = a2;
            a2 = a3;
        }
        return a3;
    }
}