class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; ++i) {
            dp[i] = Math.max(0, dp[i - 1]) + nums[i];
        }
        int res = dp[0];
        for (int i = 1; i < len; ++i) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

//空间优化
class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int pre = nums[0];
        int res = pre;
        for (int i = 1; i < len; ++i) {
            pre = Math.max(0, pre) + nums[i];
            res = Math.max(res, pre);
        }
        return res;
    }
}