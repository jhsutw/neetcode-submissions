// 1. Recursion
public class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // 從索引 0、目前總和 0 開始嘗試，在每個數字前加 + 或 -
        return backtrack(0, 0, nums, target);
    }

    private int backtrack(int i, int total, int[] nums, int target) {
        // 所有數字都決定 + / - 後，檢查是否達到目標
        if (i == nums.length) {
            return total == target ? 1 : 0;
        }
        // 選擇在 nums[i] 前加 '+' 或 '-'
        return backtrack(i + 1, total + nums[i], nums, target) +
               backtrack(i + 1, total - nums[i], nums, target);
    }
}