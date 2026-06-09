// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        // 1D 滾動陣列：
        // dp[j]    表示「上一列」（對應 s 的下一個索引 i+1）時，s[i+1..] 能匹配 t[j..] 的子序列個數
        // nextDp[j]表示「本列」（對應目前的 i）時，s[i..]   能匹配 t[j..] 的子序列個數
        int[] dp = new int[n + 1];
        int[] nextDp = new int[n + 1];

        // 基底：t 的空字串（j==n）在任何 s[i..] 中的匹配數都是 1（選空子序列）
        dp[n] = nextDp[n] = 1;

        // 從 s 的尾端往前填（i = m-1 .. 0）
        for (int i = m - 1; i >= 0; i--) {
            // 對每個 j = n-1 .. 0 計算本列 nextDp[j]
            for (int j = n - 1; j >= 0; j--) {
                // 不使用 s[i] 的情況：承接「上一列」的 dp[j]
                nextDp[j] = dp[j];
                // 若 s[i] 可匹配 t[j]，再加上「使用 s[i]」的情況：dp[j+1]
                if (s.charAt(i) == t.charAt(j)) {
                    nextDp[j] += dp[j + 1];
                }
            }
            // 本列計算完成，成為下一輪的「上一列」
            dp = nextDp.clone(); //（保留原寫法；亦可用陣列交換＋重設 nextDp[n] 來省配置）
        }

        // s[0..] 對上 t[0..] 的總匹配數
        return dp[0];
    }
}