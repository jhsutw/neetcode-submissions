// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j]：將 word1 的子字串 word1[i..] 轉成 word2 的子字串 word2[j..] 的最小編輯距離
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // 邊界 1：當 i 到達 word1 尾端時（i == word1.length()）
        // 只能「插入」剩下的 word2 字元，因此代價 = word2.length() - j
        for (int j = 0; j <= word2.length(); j++) {
            dp[word1.length()][j] = word2.length() - j;
        }
        // 邊界 2：當 j 到達 word2 尾端時（j == word2.length()）
        // 只能「刪除」剩下的 word1 字元，因此代價 = word1.length() - i
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][word2.length()] = word1.length() - i;
        }

        // 自底向上填表：從字尾往字首掃描
        for (int i = word1.length() - 1; i >= 0; i--) {
            for (int j = word2.length() - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    // 當前字元相同：不需操作，等同於看下一對字元
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    // 三種操作取其最小 + 1 次操作：
                    // 刪除（dp[i + 1][j]）、插入（dp[i][j + 1]）、替換（dp[i + 1][j + 1]）
                    dp[i][j] = 1 + Math.min(dp[i + 1][j],
                                   Math.min(dp[i][j + 1], dp[i + 1][j + 1]));
                }
            }
        }
        // 最終答案：把 word1[0..] 轉成 word2[0..] 的最小編輯距離
        return dp[0][0];
    }
}