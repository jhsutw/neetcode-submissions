// 2. Dynamic Programming (Top-Down)
public class Solution {
    // 記憶化表：dp[i][j] 表示將 word1[i..] 轉成 word2[j..] 的最小編輯距離
    // 取值為 -1 代表尚未計算
    private int[][] dp;

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        dp = new int[m][n];
        // 初始化為 -1（未計算）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        // 從 (i=0, j=0) 開始遞迴
        return dfs(0, 0, word1, word2, m, n);
    }

    // dfs(i, j)：把 word1 的子字串 word1[i..] 轉成 word2 的子字串 word2[j..] 所需的最少操作數
    private int dfs(int i, int j, String word1, String word2, int m, int n) {
        // 若 word1 已用完，只能「插入」剩下的 word2 字元
        if (i == m) return n - j;
        // 若 word2 已用完，只能「刪除」剩下的 word1 字元
        if (j == n) return m - i;
        // 有計算過就直接回傳
        if (dp[i][j] != -1) return dp[i][j];

        // 當前字元相同：不需任何操作，往下一對字元
        if (word1.charAt(i) == word2.charAt(j)) {
            dp[i][j] = dfs(i + 1, j + 1, word1, word2, m, n);
        } else {
            // 三種操作取其最小：
            // 1) 刪除 word1[i]    -> dfs(i+1, j)
            // 2) 插入 word2[j]    -> dfs(i, j+1)
            // 3) 替換 word1[i]    -> dfs(i+1, j+1)
            int res = Math.min(dfs(i + 1, j, word1, word2, m, n),
                               dfs(i, j + 1, word1, word2, m, n));
            res = Math.min(res, dfs(i + 1, j + 1, word1, word2, m, n));
            dp[i][j] = res + 1; // 本步執行 1 次操作
        }
        return dp[i][j];
    }
}