// 1. Recursion
public class Solution {
    // memo[i][j]：從 s1 的 i、s2 的 j（因此 s3 的 k = i + j）出發，是否能交錯出 s3[k..]
    // 使用 Boolean（null 代表未計算）避免額外的標記值
    private Boolean[][] memo;

    public boolean isInterleave(String s1, String s2, String s3) {
        // 長度不相等直接不可能
        if (s1.length() + s2.length() != s3.length()) return false;

        memo = new Boolean[s1.length() + 1][s2.length() + 1];
        return dfs(0, 0, s1, s2, s3);
    }

    private boolean dfs(int i, int j, String s1, String s2, String s3) {
        int k = i + j; // s3 的位置可由 i、j 推出
        if (k == s3.length()) {
            // 當 s3 用完時，必須同時用完 s1 與 s2 才算成功
            return i == s1.length() && j == s2.length();
        }

        if (memo[i][j] != null) return memo[i][j];

        boolean ok = false;

        // 嘗試使用 s1[i] 與 s3[k] 配對，配對成功 s1[i]往後推進成 s1[i + 1]
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            ok = dfs(i + 1, j, s1, s2, s3);
        }
        // 若還沒成功，再嘗試使用 s2[j] 與 s3[k] 配對，配對成功 s2[i]往後推進成 s2[i + 1]
        if (!ok && j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            ok = dfs(i, j + 1, s1, s2, s3);
        }

        memo[i][j] = ok;
        return ok;
    }
}