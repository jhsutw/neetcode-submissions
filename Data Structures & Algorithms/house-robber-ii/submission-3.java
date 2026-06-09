// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public int rob(int[] nums) {
        // 特殊情況：只有一間房子
        if (nums.length == 1) return nums[0];

        // House Robber II → 比較不搶第 0 間 vs 不搶最後一間
        return Math.max(
            helper(Arrays.copyOfRange(nums, 1, nums.length)),     // 搶 1 ~ n-1
            helper(Arrays.copyOfRange(nums, 0, nums.length - 1))  // 搶 0 ~ n-2
        );
    }

    private int helper(int[] nums) {
        int rob1 = 0, rob2 = 0;

        // 滾動變數 dp（rob1 是 dp[i-2], rob2 是 dp[i-1]）
        for (int num : nums) {
            int newRob = Math.max(rob1 + num, rob2); // 搶 or 不搶
            rob1 = rob2;
            rob2 = newRob;
        }

        return rob2; // 最後的最大收益
    }
}