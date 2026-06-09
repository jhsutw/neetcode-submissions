// 2. Dynamic Programming (Top-Down)
public class Solution {
    private Boolean[][] dp; // dp[i][j]：從 s1[i..] 與 s2[j..] 能否交錯出 s3[i+j..]

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false; // 長度不合直接否
        dp = new Boolean[m + 1][n + 1];
        return dfs(0, 0, s1, s2, s3);
    }

    private boolean dfs(int i, int j, String s1, String s2, String s3) {
        int k = i + j; // s3 的對應索引
        if (k == s3.length()) return i == s1.length() && j == s2.length();
        if (dp[i][j] != null) return dp[i][j];

        boolean ok = false;
        // 嘗試使用 s1[i]
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            ok = dfs(i + 1, j, s1, s2, s3);
        }
        // 若尚未成功，嘗試使用 s2[j]
        if (!ok && j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            ok = dfs(i, j + 1, s1, s2, s3);
        }
        return dp[i][j] = ok;
    }
}