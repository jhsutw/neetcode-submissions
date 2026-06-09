// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        // 基底：t 的空字串（長度 n）在 s[i..] 中的匹配數為 1（選空子序列）
        for (int i = 0; i <= m; i++) { // t 走到底就算完成一次配對，不管n有沒有走完
            dp[i][n] = 1;
        }

        // 從字尾往前填表
        // dp[i][j]：s[i..] 中等於 t[j..] 的不同子序列個數
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // 不使用 s[i] 的情況
                dp[i][j] = dp[i + 1][j];
                // 若 s[i] 可匹配 t[j]，再加上「使用 s[i]」的情況
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i + 1][j + 1];
                }
            }
        }

        // 回傳 s[0..] 對 t[0..] 的匹配總數
        return dp[0][0];
    }
}