// 2. Dynamic Programming (Top-Down)
public class Solution {
    private int[] memo; // 用來記錄從每個位置開始搶的最大金額

    public int rob(int[] nums) {
        // 初始化 memo 陣列，預設為 -1 表示尚未計算
        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        // 從第 0 間房子開始遞迴搶劫
        return dfs(nums, 0);
    }

    // 回傳從第 i 間房子開始能搶到的最大金額
    private int dfs(int[] nums, int i) {
        // 若超過最後一間，則回傳 0（無房可搶）
        if (i >= nums.length) {
            return 0;
        }

        // 若已經算過，就直接回傳記錄的結果
        if (memo[i] != -1) {
            return memo[i];
        }

        // 計算當前這間房子的最大搶劫收益：
        // 1. 不搶這間 -> 往下一間（i+1）
        // 2. 搶這間 -> 加上 nums[i]，往下兩間（i+2）
        memo[i] = Math.max(
            dfs(nums, i + 1),
            nums[i] + dfs(nums, i + 2)
        );

        return memo[i]; // 回傳結果
    }
}