// 2. Dynamic Programming (Top-Down)
// 用hashMap記錄已經造訪過的日子-行為的利潤
public class Solution {
    // 記憶化表：key 以 "i-buying" 形式標示狀態，value 為該狀態的最佳利潤
    private Map<String, Integer> dp = new HashMap<>();

    public int maxProfit(int[] prices) {
        // 從第 0 天、可買狀態（手上無持股）開始
        return dfs(0, true, prices);
    }

    // dfs(i, buying): 第 i 天在「buying 狀態」下可得到的最大利潤
    // buying = true  表示當天可以買（手上無持股）
    // buying = false 表示當天不能買（手上有持股，只能賣或冷卻）
    private int dfs(int i, boolean buying, int[] prices) {
        // 超過最後一天，無後續利潤
        if (i >= prices.length) {
            return 0;
        }

        // 以 (i, buying) 為 key 做記憶化，避免重複計算
        String key = i + "-" + buying;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        // 選項一：今天不動作（冷卻/跳過），狀態不變，天數 +1
        int cooldown = dfs(i + 1, buying, prices);

        if (buying) {
            // 當天可買：
            // 選項二：今天買入，付出 prices[i]，明天進入「持股」（不可買）狀態
            int buy = dfs(i + 1, false, prices) - prices[i];
            dp.put(key, Math.max(buy, cooldown)); // 取買或不買的較大利潤
        } else {
            // 當天持股（不可買）：
            // 選項二：今天賣出，拿回 prices[i]，且需要冷卻一天 → 跳到 i + 2，恢復可買狀態
            int sell = dfs(i + 2, true, prices) + prices[i];
            dp.put(key, Math.max(sell, cooldown)); // 取賣或不賣的較大利潤
        }

        return dp.get(key);
    }
}
