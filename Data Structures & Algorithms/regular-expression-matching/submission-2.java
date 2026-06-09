// 3. Dynamic Programming (Bottom-Up)
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j] 表示：s[i..] 是否能與 p[j..] 完全匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 當兩邊都走到字尾（空字串對空樣式）時為真
        dp[m][n] = true;

        // 從尾端往前填表：
        // i 代表 s 的位置（可取到 m，表示空字串）
        for (int i = m; i >= 0; i--) {
            // j 從 n-1 開始（因為 j==n 的那一列只在 dp[m][n] 為 true，其他預設 false）
            for (int j = n - 1; j >= 0; j--) {
                // 當前是否可「一對一」匹配（不考慮 '*'）
                // 條件：i 未越界，且 p[j] 與 s[i] 相同或 p[j]=='.'
                boolean match = i < m && (s.charAt(i) == p.charAt(j) ||
                                          p.charAt(j) == '.');

                // 若 p 的下一個字元是 '*'，表示「p[j] 這個 preceding element 可重複 0+ 次」
                if ((j + 1) < n && p.charAt(j + 1) == '*') {
                    // 兩種情況擇一為真即可：
                    // 1) 當作 0 次使用 -> 跳過「X*」整段：看 dp[i][j+2]
                    dp[i][j] = dp[i][j + 2];

                    // 2) 若當前能匹配（match），則吃掉一個 s 的字元：i+1
                    //    但樣式仍停在 j（因為 X* 仍可繼續吃更多字元）
                    if (match) {
                        dp[i][j] = dp[i][j] || dp[i + 1][j]; // 不可以寫 dp[i][j] = dp[i + 1][j]; 因為假如 dp[i][j] = dp[i][j + 2];為true，dp[i + 1][j]為false就會把dp[i][j]更新為false QQ）
                    }
                } else if (match) {
                    // 沒有 '*' 可用時，必須「一對一」往後比對
                    dp[i][j] = dp[i + 1][j + 1];
                }
                // 否則維持預設 false
            }
        }

        // 問題原始要求：整個 s 與整個 p 是否匹配
        return dp[0][0];
    }
}