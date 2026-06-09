// 1. Recursion
public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // 從第 0 或第 1 階開始爬，取兩者的最小花費
        return Math.min(dfs(cost, 0), dfs(cost, 1));
    }

    // 定義一個遞迴函式，用來計算從第 i 階開始爬到頂的最小花費
    private int dfs(int[] cost, int i) {
        // 若 i 超出 cost 陣列長度，表示已經爬到樓梯頂端，無需再付費
        if (i >= cost.length) {
            return 0;
        }

        // 當前這一步的花費加上從下一步或下兩步開始的最小總花費
        return cost[i] + Math.min(dfs(cost, i + 1),
                                  dfs(cost, i + 2));
    }
}