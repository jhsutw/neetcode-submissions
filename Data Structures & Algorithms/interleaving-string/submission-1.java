// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        // 長度不吻合，必不可能
        if (m + n != s3.length()) {
            return false;
        }

        // dp[i][j] 表示：s1 的子字串 s1[i..] 與 s2 的子字串 s2[j..]
        // 能否交錯組成 s3 的子字串 s3[i + j..]
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 基底：都用完時，空字串可以對上空字串 (能到達base case回傳true)
        dp[m][n] = true;

        // 由底往上填（確保用到的 dp[i+1][j] 與 dp[i][j+1] 已經算過）
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                // 若還能用 s1[i]，且與 s3[i+j] 相等，且「後續」可行 (因為是從底往上填，所以每次都會檢查上一層有沒有為true)
                if (i < m && s1.charAt(i) == s3.charAt(i + j) && dp[i + 1][j]) {
                    dp[i][j] = true;
                }
                // 若還沒成功，再嘗試用 s2[j]
                if (!dp[i][j] && j < n && s2.charAt(j) == s3.charAt(i + j) && dp[i][j + 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[0][0];
    }
}