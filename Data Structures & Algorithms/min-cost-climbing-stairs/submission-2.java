// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        // 建立 dp 陣列，dp[i] 表示走到第 i 階的最小花費
        // 注意：dp 長度要 n + 1，因為我們可以從第 n 階「外面」離開樓梯
        int[] dp = new int[n + 1];

        // 初始狀態：站在地板（第 0 階和第 1 階）不需花費
        dp[0] = 0;
        dp[1] = 0;

        // 從第 2 階開始計算到第 n 階（代表離開樓梯）
        for (int i = 2; i <= n; i++) {
            // 你可以從前一階走上來（花 cost[i - 1]）
            // 或從前兩階跳上來（花 cost[i - 2]）
            // 取兩種方式中的最小花費
            dp[i] = Math.min(dp[i - 1] + cost[i - 1],
                             dp[i - 2] + cost[i - 2]);
        }

        // 回傳走到第 n 階（即樓梯頂端之外）所需的最小花費
        return dp[n];
    }
}