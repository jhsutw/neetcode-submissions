// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int rob(int[] nums) {
        // 若沒有房子，搶不到錢
        if (nums.length == 0) return 0;

        // 若只有一間房子，只能搶那間
        if (nums.length == 1) return nums[0];

        // 建立 dp 陣列，dp[i] 表示搶到第 i 間房子的最大金額
        int[] dp = new int[nums.length];

        // 初始化第一間房子的搶劫金額
        dp[0] = nums[0];

        // 第二間房子只能選擇第一間或第二間中較大的那一間搶
        dp[1] = Math.max(nums[0], nums[1]);

        // 從第三間房子開始做狀態轉移
        for (int i = 2; i < nums.length; i++) {
            // dp[i - 1]：不搶第 i 間，維持前一間的最大金額
            // nums[i] + dp[i - 2]：搶第 i 間，加上 i-2 間的最大金額
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        // 回傳最後一間房子的最大搶劫金額
        return dp[nums.length - 1];
    }
}