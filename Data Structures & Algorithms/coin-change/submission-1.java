// 2. Dynamic Programming (Top-Down)
public class Solution {
    HashMap<Integer, Integer> memo = new HashMap<>(); // 建立一個記憶表，用來存已計算過的 amount 對應的最少硬幣數

    // 遞迴函式：回傳湊出 amount 所需的最少硬幣數量
    public int dfs(int amount, int[] coins) {
        if (amount == 0) return 0; // 基底情況：金額為 0，表示不需要硬幣

        if (memo.containsKey(amount)) // 如果這個 amount 已經算過，直接回傳記憶表中的結果
            return memo.get(amount);

        int res = Integer.MAX_VALUE; // 初始化結果為無限大（代表目前還找不到解）

        for (int coin : coins) { // 遍歷每一種硬幣
            if (amount - coin >= 0) { // 確保使用該硬幣後金額不為負
                int result = dfs(amount - coin, coins); // 遞迴計算湊出剩餘金額所需的最少硬幣
                if (result != Integer.MAX_VALUE) {
                    res = Math.min(res, 1 + result); // 若有解，取最小的硬幣數量
                }
            }
        }

        memo.put(amount, res); // 將當前 amount 的結果存入記憶表 (之後再遇到一樣的amount就直接帶入值不用重算)
        return res; // 回傳最少的硬幣數量（若仍為無限大，表示無法湊出）
    }

    // 主函式
    public int coinChange(int[] coins, int amount) {
        int minCoins = dfs(amount, coins); // 使用 DFS + 記憶化搜尋解答
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins; // 若無解，回傳 -1；否則回傳最少硬幣數
    }
}