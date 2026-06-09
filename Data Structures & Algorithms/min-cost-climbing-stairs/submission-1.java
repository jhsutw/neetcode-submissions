// 2. Dynamic Programming (Top-Down)
public class Solution {
    int[] memo; // 用來記錄從每一階開始的最小花費，避免重複計算

    public int minCostClimbingStairs(int[] cost) {
        // 初始化 memo 陣列，大小與 cost 相同
        // 初始值設為 -1，代表尚未計算過
        memo = new int[cost.length];
        Arrays.fill(memo, -1);

        // 從第 0 階或第 1 階開始，取兩者的最小值作為答案
        return Math.min(dfs(cost, 0), dfs(cost, 1));
    }

    // 傳回從第 i 階開始爬到樓梯頂端的最小花費
    private int dfs(int[] cost, int i) {
        // 若 i 超出樓梯範圍，代表已到達頂端，花費為 0
        if (i >= cost.length) {
            return 0;
        }

        // 如果之前已經算過第 i 階的最小花費，直接回傳 (避免重複計算)
        if (memo[i] != -1) {
            return memo[i];
        }

        // 計算從第 i 階開始的最小花費：當前階段的花費 + 往下一或兩階遞迴的最小花費
        memo[i] = cost[i] + Math.min(dfs(cost, i + 1),
                                     dfs(cost, i + 2));

        return memo[i];
    }
}