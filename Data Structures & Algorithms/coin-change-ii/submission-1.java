// 2. Dynamic Programming (Top-Down)
public class Solution {
    public int change(int amount, int[] coins) {
        // 將硬幣排序（非必要，但有時可幫助一致性或剪枝）
        Arrays.sort(coins);

        // 建立記憶化表：memo[i][a] 表示從索引 i 的硬幣開始 (0 ~ coins.length)，組成金額 a 的方法數 (0 ~ amount)
        int[][] memo = new int[coins.length + 1][amount + 1];
        for (int[] row : memo) Arrays.fill(row, -1);

        return dfs(0, amount, coins, memo);
    }

    /**
     * @param i     目前考慮的硬幣索引
     * @param a     剩餘要組成的金額
     * @param coins 硬幣面額陣列
     * @param memo  記憶化表
     * @return      從 coins[i...] 組成金額 a 的方法數
     */
    private int dfs(int i, int a, int[] coins, int[][] memo) {
        // 金額剛好組成，算一種方式
        if (a == 0) return 1;
        // 沒硬幣可用且金額尚未組成，無解
        if (i >= coins.length) return 0;

        if (memo[i][a] != -1) return memo[i][a];

        // 1) 一定可行的選擇：跳過當前硬幣（改用後面的硬幣）
        int res = dfs(i + 1, a, coins, memo);

        // 2) 若金額足夠，也可以選擇使用當前硬幣（可重複使用，故索引不變）
        if (a >= coins[i]) {
            res += dfs(i, a - coins[i], coins, memo);
        }

        memo[i][a] = res;
        return res;
    }
}