// 2. Dynamic Programming (Top-Down)
public class Solution {
    private int[][] dp;     // dp[i][total + totalSum]：在索引 i、目前和為 total 時的方案數（-INF 代表未計算）
    private int totalSum;   // nums 所有元素總和，用來位移 total 使其成為非負索引

    public int findTargetSumWays(int[] nums, int target) {
        totalSum = 0;
        for (int num : nums) totalSum += num;

        // 建立記憶化表：
        // total 的可能範圍為 [-totalSum, totalSum]，位移 +totalSum 後變成 [0 .. 2*totalSum]
        dp = new int[nums.length][2 * totalSum + 1];

        // 以 Integer.MIN_VALUE 當作「尚未計算」的標記值
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 2 * totalSum + 1; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        // 從索引 0、目前總和 0 開始嘗試
        return backtrack(0, 0, nums, target);
    }

    private int backtrack(int i, int total, int[] nums, int target) {
        // 若所有數字都已決定 + / -，檢查是否達到目標
        if (i == nums.length) {
            return total == target ? 1 : 0;
        }

        // 取出對應的記憶化索引（把可能為負的 total 位移為非負）
        int idx = total + totalSum; // totalSum是所有num相加，必定大於total

        // 若已經計算過，直接回傳
        if (dp[i][idx] != Integer.MIN_VALUE) {
            return dp[i][idx];
        }

        // 選擇在 nums[i] 前加 '+' 或 '-'
        int ways = backtrack(i + 1, total + nums[i], nums, target)
                 + backtrack(i + 1, total - nums[i], nums, target);

        // 記錄結果
        dp[i][idx] = ways;
        return ways;
    }
}