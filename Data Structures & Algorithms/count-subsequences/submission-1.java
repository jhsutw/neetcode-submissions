// 2. Dynamic Programming (Top-Down)
public class Solution {
    // 記憶化表：dp[i][j] 表示 s 的子字串 s[i..] 能形成 t 的子字串 t[j..] 的「不同子序列個數」
    // 以 -1 表示尚未計算（避免重複遞迴）
    private int[][] dp;

    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        // 若 t 比 s 長，必不可能
        if (n > m) return 0;

        dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], -1);
        }
        // 從 (i=0, j=0) 開始：同時從 s、t 的起點出發
        return dfs(s, t, 0, 0);
    }

    // 回傳：s[i..] 中有多少種子序列可以等於 t[j..]
    private int dfs(String s, String t, int i, int j) {
        // t 已完全匹配完：找到一種有效子序列
        if (j == t.length()) return 1;
        // s 用盡但 t 尚未匹配完：無法形成
        if (i == s.length()) return 0;
        // 查記憶化
        if (dp[i][j] != -1) return dp[i][j];

        // 情況 1：跳過 s[i] 不用，看看後續還能匹配出多少
        int res = dfs(s, t, i + 1, j);

        // 情況 2：若 s[i] == t[j]，可選擇使用 s[i] 來匹配 t[j]
        if (s.charAt(i) == t.charAt(j)) {
            res += dfs(s, t, i + 1, j + 1);
        }

        dp[i][j] = res; // 記錄結果
        return res;
    }
}