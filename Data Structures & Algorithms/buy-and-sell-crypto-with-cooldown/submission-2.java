// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i][1]：第 i 天「可買」（手上無持股）時，往後的最大利潤
        // dp[i][0]：第 i 天「持股」（不可買）時，往後的最大利潤
        int[][] dp = new int[n + 1][2]; // 預設 0 做邊界（超出最後一天利潤為 0）

        // 從最後一天往前推
        for (int i = n - 1; i >= 0; i--) {
            for (int buying = 1; buying >= 0; buying--) {
                if (buying == 1) {
                    // 可買：二選一 -> 今天買 / 今天不動
                    int buy = dp[i + 1][0] - prices[i]; // 今天買，明天變持股
                    int cooldown = dp[i + 1][1];        // 今天不動，狀態不變到明天
                    dp[i][1] = Math.max(buy, cooldown);
                } else {
                    // 持股：二選一 -> 今天賣 / 今天不動
                    // 賣出後需冷卻一天：跳到 i+2 並恢復可買
                    int sell = (i + 2 < n) ? dp[i + 2][1] + prices[i] : prices[i];
                    int cooldown = dp[i + 1][0]; // 今天不動，仍持股到明天
                    dp[i][0] = Math.max(sell, cooldown);
                }
            }
        }
        return dp[0][1]; // 第 0 天、可買狀態的最大利潤
    }
}