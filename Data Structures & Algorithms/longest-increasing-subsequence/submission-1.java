// 2. Dynamic Programming (Top-Down) - I
// 把重複子問題結果缓存起來，避免重算。
public class Solution {
    private int[][] memo; // memo[i][j+1] 用來記錄當前 i、j 狀態下的 LIS 結果

    private int dfs(int i, int j, int[] nums) {
        // 終止條件：走到陣列尾端
        if (i == nums.length) {
            return 0;
        }

        // 如果已經計算過，直接回傳記錄的結果
        // 注意這裡 j+1 是為了處理 j = -1 的情況（避免負索引）
        if (memo[i][j + 1] != -1) {
            return memo[i][j + 1];
        }

        // 選項 1：不選 nums[i]，直接看下一個位置的結果
        int LIS = dfs(i + 1, j, nums);

        // 選項 2：選 nums[i]
        // 條件：j = -1 代表還沒選過數字，或者 nums[i] > nums[j]
        if (j == -1 || nums[j] < nums[i]) {
            LIS = Math.max(LIS, 1 + dfs(i + 1, i, nums));
        }

        // 記錄當前狀態的結果
        memo[i][j + 1] = LIS;

        return LIS;
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // 建立 n x (n+1) 的記憶化陣列
        // 因為 j 有可能是 -1，所以要多開一欄給 j = -1 使用
        memo = new int[n][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // 初始化為 -1，代表還沒計算過
        }
        // 從索引 0 開始，j = -1 代表沒有選過任何元素
        return dfs(0, -1, nums);
    }
}