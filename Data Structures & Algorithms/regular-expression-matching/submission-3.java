// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public boolean isMatch(String s, String p) {
        // 定義：dp[j] 代表「上一輪 i+1 的狀態」中的 dp[i+1][j]
        // 初始化基底：空字串對空樣式為 true → dp[p.length()] = true
        boolean[] dp = new boolean[p.length() + 1];
        dp[p.length()] = true;

        // i 從後往前掃，代表在計算「dp[i][*]」
        for (int i = s.length(); i >= 0; i--) {
            // nextDp[j] 代表「本輪 i 的狀態」中的 dp[i][j]
            boolean[] nextDp = new boolean[p.length() + 1];

            // 邊界：dp[i][n] = (i == m)
            // 當 pattern 用完（j==n），只有 s 也同時用完（i==m）才為 true
            nextDp[p.length()] = (i == s.length());

            // j 從後往前掃，因為轉移會用到 j+1 或 j+2
            for (int j = p.length() - 1; j >= 0; j--) {
                // 是否能「一對一」匹配（不含 * 的情況）
                boolean match = i < s.length() &&
                                (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

                // 若下一個字元是 '*'，表示 p[j] 這個 preceding element 可重複 0+ 次
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    // 途徑 A：0 次使用，跳過「X*」→ dp[i][j] = dp[i][j+2]
                    nextDp[j] = nextDp[j + 2];

                    // 途徑 B：多次使用（需 match 為真）
                    // 吃掉 s 的一個字元（i -> i+1），pattern 停在 j（X* 可繼續吃）
                    // 此時 dp[i][j] |= dp[i+1][j]；而 dp[i+1][j] 就是上一輪的 dp[j]
                    if (match) {
                        nextDp[j] |= dp[j];
                    }
                } else if (match) {
                    // 沒有 '*' 可用 → 一對一往後比對
                    // dp[i][j] = dp[i+1][j+1]；上一輪的 dp[j+1] 就是 dp[i+1][j+1]
                    nextDp[j] = dp[j + 1];
                }
                // 否則保持預設 false
            }

            // 進入下一輪：把本輪計算好的 dp[i][*] 當作「下一輪的上一行」dp[i+1][*]
            dp = nextDp;
        }

        // 問題所求：dp[0][0]，在 1D 寫法中對應最後一輪的 dp[0]
        return dp[0];
    }
}