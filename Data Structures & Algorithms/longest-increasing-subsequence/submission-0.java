// 1. Recursion
public class Solution {
    public int lengthOfLIS(int[] nums) {
        // 從索引 0 開始遞迴，j = -1 代表目前還沒有選任何元素
        return dfs(nums, 0, -1);
    }

    private int dfs(int[] nums, int i, int j) {
        // 終止條件：如果已經走到陣列末端，沒有更多元素可選
        if (i == nums.length) {
            return 0;
        }

        // 選項 1：不包含 nums[i]（直接跳到下一個索引）
        int LIS = dfs(nums, i + 1, j);

        // 選項 2：包含 nums[i]
        // 條件：如果 j == -1（表示還沒選過數字）"或" 當前 nums[i] > 上一個選的數字 nums[j](因為是increasing subsequence)
        if (j == -1 || nums[j] < nums[i]) {
            // 嘗試包含 nums[i]，並遞迴到下一個索引
            // 長度多加 1（因為選了 nums[i]）
            LIS = Math.max(LIS, 1 + dfs(nums, i + 1, i));
        }

        // 回傳在 i 位置能取得的 LIS 最長長度
        return LIS;
    }
}