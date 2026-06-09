// 1. Recursion
public class Solution {
    public int maxProfit(int[] prices) {
        // 從第 0 天開始，狀態為「可買」（手上沒持股）
        return dfs(0, true, prices);
    }

    // dfs(i, buying): 第 i 天、是否處於可買狀態（buying=true 表示手上沒持股）
    private int dfs(int i, boolean buying, int[] prices) {
        if (i >= prices.length) {
            return 0; // 超出最後一天，沒有收益
        }

        // 冷卻/跳過：今天不操作，往下一天
        int cooldown = dfs(i + 1, buying, prices);

        if (buying) {
            // 選擇買：今天買入，花掉 prices[i]，明天狀態變「不可買」（手上有持股）
            int buy = dfs(i + 1, false, prices) - prices[i];
            return Math.max(buy, cooldown); // 就是在 「買」 與 「今天不動」 之間，選取未來最終利潤更高的那個動作
        } else {
            // 選擇賣：今天賣出，賺到 prices[i]，且明天進入冷卻 → i+2 且狀態變「可買」
            int sell = dfs(i + 2, true, prices) + prices[i];
            return Math.max(sell, cooldown);
        }
    }
}