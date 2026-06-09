// 2. Dynamic Programming (Top-Down)
public class Solution {
    // memo[i][t] 紀錄：從索引 i 開始，是否能湊出目標和 t
    // null 表示尚未計算；true/false 為計算結果
    Boolean[][] memo;

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // 若總和為奇數，無法分成兩個和相等的子集
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;           // 目標：找是否存在子集和為 sum/2
        memo = new Boolean[n][target + 1]; // 之所以要 +1，是因為 target 是「最大可能的子集和」，而陣列索引是從 0 開始計算，所以必須多開一格才能存到 target 這個值本身

        return dfs(nums, 0, target);
    }

    // 回溯＋記憶化：從索引 i 開始，能否湊到 target
    public boolean dfs(int[] nums, int i, int target) {
        // 全部元素都考慮完，只有 target == 0 才代表剛好湊到
        if (i == nums.length) {
            return target == 0;
        }
        // 剪枝：目標已經小於 0，不可能成功
        if (target < 0) {
            return false;
        }
        // 記憶化查詢：若已有結果，直接回傳以避免重複計算
        if (memo[i][target] != null) {
            return memo[i][target];
        }

        // 選或不選 nums[i] 兩條路，只要有一條可行即為 true
        boolean res = dfs(nums, i + 1, target) ||
                      dfs(nums, i + 1, target - nums[i]);

        memo[i][target] = res;
        return res;
    }
}