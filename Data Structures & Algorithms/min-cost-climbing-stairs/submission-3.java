// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // 從倒數第 3 個位置開始往前處理（因為最後兩階不需要再修改）
        for (int i = cost.length - 3; i >= 0; i--) {
            // cost[i] 會累加上從 i 走到頂端的最小花費 (把離開i點的cost轉換成從i點到終點的cost)
            // 你可以從 cost[i] 走到 cost[i+1] 或 cost[i+2]，選擇較便宜的那條路徑
            cost[i] += Math.min(cost[i + 1], cost[i + 2]);
        }

        // 最後可以從第 0 階或第 1 階開始，選擇花費較小的那個作為起點
        return Math.min(cost[0], cost[1]);
    }
}