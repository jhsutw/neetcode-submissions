// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        Arrays.sort(coins);
        // dp[i][a]：用 coins[i..n-1] 可以組成金額 a 的方法數
        int[][] dp = new int[n + 1][amount + 1];

        // 基底：金額為 0 時，總有 1 種方式（不選任何硬幣）
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // 由後往前枚舉硬幣索引 i
        for (int i = n - 1; i >= 0; i--) {
            for (int a = 0; a <= amount; a++) {
                // 至少可以「不選這枚硬幣」：轉移到下一種硬幣
                dp[i][a] = dp[i + 1][a];
                // 若金額足夠，再加上「選這枚硬幣」的方式（可重複使用，故仍在 i）
                if (a >= coins[i]) {
                    dp[i][a] += dp[i][a - coins[i]];
                }
            }
        }

        return dp[0][amount];
    }
}