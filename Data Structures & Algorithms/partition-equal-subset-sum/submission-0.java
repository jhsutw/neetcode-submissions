// 1. Recursion
public class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // 總和為奇數，無法分成兩個和相等的子集 (除以2有小數點)
        if (sum % 2 != 0) {
            return false;
        }
        // 目標是找是否存在子集，其和為 sum/2
        return dfs(nums, 0, sum / 2);
    }

    // 回溯：在索引 i 之後，是否能湊到 target
    public boolean dfs(int[] nums, int i, int target) {
        // 全部考慮完，檢查是否剛好湊到 0
        if (i == nums.length) {
            return target == 0;
        }
        // 提前剪枝：超過目標就不必再試
        if (target < 0) {
            return false;
        }
        // 選或不選 nums[i]
        return dfs(nums, i + 1, target) ||
               dfs(nums, i + 1, target - nums[i]);
    }
}

