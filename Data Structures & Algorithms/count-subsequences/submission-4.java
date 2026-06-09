// 5. Dynamic Programming (Optimal)
public class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        // dp[j] 代表：在處理到 s 的第 i 個字元時（由外層迴圈控制），
        // s[i..] 能匹配 t[j..] 的子序列個數（此陣列會就地滾動更新）
        int[] dp = new int[n + 1];

        // 基底：t 走到底就算是一種方法成立
        dp[n] = 1;

        // 從 s 的尾端往前處理（對應 2D DP 的由下往上）
        for (int i = m - 1; i >= 0; i--) {
            int prev = 1; // 對應「上一列」的 dp[j+1]（因為 dp[n] = 1）
            for (int j = n - 1; j >= 0; j--) {
                int res = dp[j]; // 不使用 s[i] 的情況：承接上一列的 dp[j]
                if (s.charAt(i) == t.charAt(j)) {
                    // 使用 s[i] 的情況：加上上一列的 dp[j+1]，此值由 prev 暫存
                    res += prev;
                }

                // 迴圈右移前，更新 prev 為「上一列的 dp[j]」（尚未覆寫前的 dp[j]）
                prev = dp[j];
                // 寫回本列結果
                dp[j] = res;
            }
        }

        // s[0..] 對上 t[0..] 的總匹配數
        return dp[0];
    }
}