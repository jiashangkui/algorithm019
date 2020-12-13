class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // 获取两个串字符
                char c1 = text1.charAt(i - 1), c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    // 去找它们前面各退一格的值加1即可
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    //要么是text1往前退一格，要么是text2往前退一格，两个的最大值
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[m][n];
    }
}