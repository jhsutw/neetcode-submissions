// 3. Dynamic Programming (Top-Down) - II
// 用array紀錄每個i為起點的LIS數量，避免重複計算！
public class Solution {
    private int[] memo; // memo[i] 表示「以 i 為起點的 LIS 最長長度」

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        memo = new int[n];
        Arrays.fill(memo, -1); // -1 代表尚未計算

        int maxLIS = 1; // 全域答案（最少也有 1，單一元素）
        for (int i = 0; i < n; i++) {
            // 以每個位置 i 當起點，取能得到的最長遞增序列
            maxLIS = Math.max(maxLIS, dfs(nums, i));
        }
        return maxLIS;
    }

    private int dfs(int[] nums, int i) {
        // 若已計算過以 i 為起點的 LIS，直接回傳
        if (memo[i] != -1) {
            return memo[i];
        }

        int LIS = 1; // 至少包含 nums[i] 自己，所以起始為 1
        // 嘗試接在 nums[i] 後面的元素（必須比 nums[i] 大）
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] < nums[j]) {
                // 取「接上 j 之後的 LIS」的最大值
                LIS = Math.max(LIS, 1 + dfs(nums, j));
            }
        }

        memo[i] = LIS; // 記憶化：存下以 i 為起點的結果
        return LIS;
    }
}