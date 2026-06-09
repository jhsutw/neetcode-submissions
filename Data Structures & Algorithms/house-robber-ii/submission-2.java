// 2. Dynamic Programming (Top-Down)
public class Solution {
    private int[][] memo; // 記憶化表：memo[i][flag] 表示在第 i 間房子、flag 狀態下的最大收益

    public int rob(int[] nums) {
        // 特例：只有一間房子，直接搶
        if (nums.length == 1) return nums[0];

        // 初始化記憶化陣列，大小為 [nums.length][2]
        memo = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            memo[i][0] = -1; // flag == 0：表示還沒搶過第 0 間
            memo[i][1] = -1; // flag == 1：表示搶過第 0 間
        }

        // 嘗試兩種起始情況，取最大值：
        // 1. 從第 0 間開始搶（flag = 1，代表已搶過第 0 間）
        // 2. 從第 1 間開始搶（flag = 0，代表沒搶過第 0 間）
        return Math.max(dfs(0, 1, nums), dfs(1, 0, nums));
    }

    // i: 當前考慮的房子編號
    // flag: 是否搶過第 0 間（0 = 沒搶過, 1 = 有搶過）
    private int dfs(int i, int flag, int[] nums) {
        // 若超過範圍，或 flag=1 且當前是最後一間房子，就不能搶，回傳 0
        if (i >= nums.length || (flag == 1 && i == nums.length - 1))
            return 0;

        // 若已經計算過這個狀態，直接回傳
        if (memo[i][flag] != -1)
            return memo[i][flag];

        // 選擇：
        // 1. 不搶當前房子 → 往下一間 dfs(i+1)
        // 2. 搶當前房子 → nums[i] + dfs(i+2)，並更新 flag（若搶的是第 0 間，flag 要設為 1）
        // flag | i == 0 ? 1 : 0 (1) 若flag本來為1，不管i為多少交集都會是1；若flag本來為0，i若為0交集就會是1
        memo[i][flag] = Math.max(
            dfs(i + 1, flag, nums),
            nums[i] + dfs(i + 2, flag | (i == 0 ? 1 : 0), nums)
        );

        return memo[i][flag];
    }
}