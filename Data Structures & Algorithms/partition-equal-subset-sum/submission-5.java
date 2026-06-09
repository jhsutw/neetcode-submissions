// 6. Dynamic Programming (Optimal)
public class Solution {
    public boolean canPartition(int[] nums) {
        // 若總和為奇數，無法切成兩個和相等的子集
        if (sum(nums) % 2 != 0) {
            return false;
        }

        int target = sum(nums) / 2;     // 目標：是否能湊出總和的一半
        boolean[] dp = new boolean[target + 1];
        // dp[j]：是否能用「目前處理到的元素」湊出和 j

        dp[0] = true;                   // 基底：和為 0 一定可行（選空集合）

        // 逐一考慮每個元素（0/1 背包：每個元素最多用一次）
        for (int i = 0; i < nums.length; i++) {
            // 倒序遍歷避免同一元素被重複使用
            for (int j = target; j >= nums[i]; j--) {
                // 轉移：不選 nums[i]（dp[j]）或選 nums[i]（dp[j - nums[i]]）
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        // 是否能剛好湊到 target
        return dp[target];
    }

    // 輔助函式：計算陣列總和
    private int sum(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        return total;
    }
}