// 2. Dynamic Programming (Top-Down)
public class Solution {
    private int[][] memo; // memo[i][j] 紀錄 text1[i:] 與 text2[j:] 的 LCS 長度，-1 代表尚未計算

    public int longestCommonSubsequence(String text1, String text2) {
        memo = new int[text1.length()][text2.length()];
        // 以 -1 當作「未計算」標記
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        // 從 (0,0) 開始比較整個字串
        return dfs(text1, text2, 0, 0);
    }

    // 回傳：text1[i:] 與 text2[j:] 的 LCS 長度
    private int dfs(String text1, String text2, int i, int j) {
        // 任一字串走到底，沒有共同子序列
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }
        // 記憶化：已計算過直接回傳
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 當前字元相同 -> 取 1 + 右下角子問題
        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = 1 + dfs(text1, text2, i + 1, j + 1);
        } else {
            // 不同 -> 二選一：略過 text1[i] 或略過 text2[j]，取較大值
            memo[i][j] = Math.max(
                dfs(text1, text2, i + 1, j),
                dfs(text1, text2, i, j + 1)
            );
        }
        return memo[i][j];
    }
}