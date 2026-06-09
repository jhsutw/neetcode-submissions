// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0; // 邊界：無價格

        // dp1_buy  = dp[i+1][buy]
        // dp1_sell = dp[i+1][sell]
        // dp2_buy  = dp[i+2][buy]（賣出後冷卻用）
        int dp1_buy = 0, dp1_sell = 0;
        int dp2_buy = 0;

        // 從最後一天往前推
        for (int i = n - 1; i >= 0; i--) {
            // 今天 buy 狀態的最佳利潤
            int dp_buy = Math.max(dp1_sell - prices[i],  // 今天買：明天變 sell
                                  dp1_buy);              // 今天不動

            // 今天 sell 狀態的最佳利潤
            int dp_sell = Math.max(dp2_buy + prices[i],  // 今天賣：跳到 i+2 的 buy（冷卻）
                                   dp1_sell);            // 今天不動

            // 滾動窗口往前移一天
            dp2_buy = dp1_buy;    // 原本的 dp[i+1][buy] 變成下一輪的 dp[i+2][buy]
            dp1_buy = dp_buy;     // 更新 dp[i+1][buy] -> 現在變成 dp[i][buy]
            dp1_sell = dp_sell;   // 更新 dp[i+1][sell] -> 現在變成 dp[i][sell]
        }

        // 回傳 dp[0][buy]
        return dp1_buy;
    }
}