// 5. Dynamic Programming (Optimal)
public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // 空間壓縮到 O(min(m,n))：若 word2 較長，交換兩字串，確保 n 是較短者
        if (m < n) {
            String temp = word1; word1 = word2; word2 = temp;
            m = word1.length(); n = word2.length();
        }

        // dp[j] 對應 2D DP 的 dp[i+1][j]（上一列）
        // 初始列（i = m）：把空字串轉成 word2[j..] 需要插入 (n - j) 次
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) dp[i] = n - i;

        // 由底向上處理 i = m-1..0
        for (int i = m - 1; i >= 0; i--) {
            // nextDp 暫存「對角線」的上一列值：一開始是 dp[n] == 0（即 dp[i+1][n]）
            int nextDp = dp[n];
            // 邊界：把 word1[i..] 轉成空字串 word2[n..] 需刪除 (m - i) 次
            dp[n] = m - i;

            // j 從右往左，確保 dp[j+1] 已更新為本列 dp[i][j+1]
            for (int j = n - 1; j >= 0; j--) {
                int temp = dp[j]; // 暫存上一列的 dp[i+1][j]，等會兒要給下一輪的 nextDp 用

                if (word1.charAt(i) == word2.charAt(j)) {
                    // 字元相同：不需操作 → 本列 dp[i][j] = 上一列對角 dp[i+1][j+1]
                    dp[j] = nextDp;
                } else {
                    // 字元不同：1 + min(刪除, 插入, 替換)
                    // 刪除：上一列的 dp[i+1][j]      -> dp[j]（尚未覆寫前）
                    // 插入：本列已算好的 dp[i][j+1] -> dp[j+1]
                    // 替換：上一列對角 dp[i+1][j+1] -> nextDp
                    dp[j] = 1 + Math.min(dp[j], Math.min(dp[j + 1], nextDp));
                }

                // 右移一格：下一輪的「對角」就是目前保存的上一列 dp[i+1][j]
                nextDp = temp;
            }
        }
        // 最終答案：將 word1[0..] 轉成 word2[0..] 的最小編輯距離
        return dp[0];
    }
}