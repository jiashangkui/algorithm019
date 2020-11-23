class Solution {
    public int climbStairs(int n) {
        int memo[] = new int[n+1];
        return climbStairsMemo(n,memo);
    }

    public int climbStairsMemo(int n, int memo[]){
        if(memo[n]>0){
            return memo[n];
        }
        if(n == 1){
            memo[n] = 1;
        }else if(n == 2){
            memo[n] = 2;
        }else{
            memo[n] = climbStairsMemo(n-1,memo)+climbStairsMemo(n-2,memo);
        }
        return memo[n];
    }
}