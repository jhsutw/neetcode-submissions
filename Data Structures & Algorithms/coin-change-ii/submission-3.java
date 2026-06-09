// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public int change(int amount, int[] coins) {
        // dp[a] 代表「只用處理過的硬幣」能組成金額 a 的方法數
        int[] dp = new int[amount + 1];
        dp[0] = 1; // 組成 0 元只有 1 種方式：不選任何硬幣

        // 逐一加入每一種硬幣（此處從後往前也可以，因為我們使用 nextDP 分層）
        for (int i = coins.length - 1; i >= 0; i--) {
            int[] nextDP = new int[amount + 1];
            nextDP[0] = 1; // 只要允許「不選任何硬幣」，組成 0 元永遠有 1 種方式

            for (int a = 1; a <= amount; a++) {
                // 不使用當前硬幣：方式數承接上一層（尚未引入當前硬幣）的 dp[a]
                nextDP[a] = dp[a];

                // 使用當前硬幣：由於可重複使用，仍在 nextDP 這一層累積
                if (a - coins[i] >= 0) {
                    nextDP[a] += nextDP[a - coins[i]];
                }
            }
            // 將本輪（已引入當前硬幣）的結果，作為下一輪的「上一層」
            dp = nextDP;
        }
        return dp[amount];
    }
}