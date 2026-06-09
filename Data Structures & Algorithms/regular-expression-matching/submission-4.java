// 5. Dynamic Programming (Optimal)
public class Solution {
    public boolean isMatch(String s, String p) {
        // dp[j] 代表「上一排」狀態 dp[i+1][j]：s 從 i+1 開始、p 從 j 開始是否匹配
        // 基底：空字串對空樣式為真 → dp[n] = true
        boolean[] dp = new boolean[p.length() + 1];
        dp[p.length()] = true;

        // 外層：i 從後往前（因為本排 dp[i][*] 依賴上一排 dp[i+1][*]）
        for (int i = s.length(); i >= 0; i--) {
            // dp1 暫存上一排的 dp[i+1][j+1]，供「無 * 的一對一匹配」使用
            boolean dp1 = dp[p.length()];
            // 設定本排的邊界：dp[i][n] = (i == m)
            dp[p.length()] = (i == s.length());

            // 內層：j 從後往前（因為會用到 j+1、j+2）
            for (int j = p.length() - 1; j >= 0; j--) {
                // 當前是否能一對一匹配（不考慮 *）
                boolean match = i < s.length() &&
                                (s.charAt(i) == p.charAt(j) ||
                                 p.charAt(j) == '.');

                boolean res = false; // 將要寫入的本排 dp[i][j]

                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    // 情況一：遇到 X*（preceding element 為 p[j]）
                    // 途徑 A：X* 使用 0 次 → dp[i][j] = dp[i][j+2]
                    // 注意：dp[j+2] 此時仍是本排的值（因 j 由右往左），可直接取用
                    res = dp[j + 2];

                    // 途徑 B：X* 使用 ≥1 次（需 match），吃掉一個 s[i]，p 仍停在 j
                    // → dp[i][j] |= dp[i+1][j]，而 dp[i+1][j] 就是上一排的 dp[j]
                    if (match) {
                        res |= dp[j];
                    }
                } else if (match) {
                    // 情況二：沒有 *，且當前可一對一匹配
                    // → dp[i][j] = dp[i+1][j+1]，上一排的 dp[i+1][j+1] 以 dp1 暫存
                    res = dp1;
                }
                // 若以上皆不成立，res 維持 false（不匹配）

                // 在覆寫 dp[j] 之前，先把「上一排的 dp[i+1][j]」存到 dp1
                // 供下一個迴圈（j-1）作為「上一排的 dp[i+1][(j-1)+1] = dp[i+1][j]」使用
                dp1 = dp[j];

                // 寫回本排結果：dp[i][j]
                dp[j] = res;
            }
        }

        // 最終答案：dp[0] 對應 dp[0][0]（整串 s 與整個 p 是否匹配）
        return dp[0];
    }
}