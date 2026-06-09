// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int climbStairs(int n) {
        // 基本情況：前兩階直接回傳答案
        if (n <= 2) {
            return n; // n=1 時只有1種走法；n=2 時有2種（1+1, 2）
        }

        // dp[i] 表示走到第 i 階的方法數
        int[] dp = new int[n + 1];

        // 初始化前兩階
        dp[1] = 1; // 第1階只有1種方法
        dp[2] = 2; // 第2階有2種方法（1+1 或 直接2）

        // 遞推計算後面的階數
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            // 到第 i 階的方法 = 從 i-1 踏一步 or 從 i-2 踏兩步
        }

        return dp[n]; // 回傳走到第 n 階的方法總數
    }
}