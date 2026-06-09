public class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;  // 累加總和
        }
        // 總和為奇數時，不可能切成兩半
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;                 // 目標：是否能湊出 sum/2
        boolean[][] dp = new boolean[n + 1][target + 1];
        // dp[i][j]：只用前 i 個數字，是否能湊出和 j

        // base case：當target為0代表可以湊出該數
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // 轉移：每個數字只能用一次（0/1 背包）
        for (int i = 1; i <= n; i++) { // nums 的 index
            for (int j = 1; j <= target; j++) { // target 值
                if (nums[i - 1] <= j) {
                    // 不選 nums[i-1] 或 選 nums[i-1]（把「選」與「不選」兩種情況都考慮到）
                    dp[i][j] = dp[i - 1][j] || // 不選
                               dp[i - 1][j - nums[i - 1]]; // 選（target會慢慢被扣掉）
                } else {
                    // 放不下就只能不選
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][target]; // 是否能用所有元素湊到 target，若target為0則回傳true
    }
}