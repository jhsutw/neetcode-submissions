// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; // dp[i] 代表湊出金額 i 所需的最少硬幣數
        Arrays.fill(dp, amount + 1);    // 初始所有值設為一個不可能達成的最大值（如 amount + 1）
        dp[0] = 0;                      // base case：金額為 0 時，需要 0 枚硬幣

        // 從 1 到 amount 逐步計算最少硬幣數
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                // 如果硬幣面額小於等於當前金額 i，表示可以使用這個硬幣
                if (coins[j] <= i) {
                    // 狀態轉移方程：取 min(dp[i], dp[i - coins[j]] + 1)
                    // 表示「用一枚 coins[j]，加上湊出 i - coins[j] 所需的最少硬幣」
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        // 如果最終還是無法湊出金額，回傳 -1；否則回傳最少硬幣數
        return dp[amount] > amount ? -1 : dp[amount];
    }
}