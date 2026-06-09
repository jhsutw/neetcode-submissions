// 1. Recursion
public class Solution {
    // 主函式：從 0 開始往上爬
    public int climbStairs(int n) {
        return dfs(n, 0);
    }

    // 遞迴函式：目前在第 i 階，要爬到第 n 階的方法總數
    public int dfs(int n, int i) {
        // 如果爬超過了 n 階，無效 → 回傳 0
        if (i > n) return 0;

        // 如果剛好爬到第 n 階，代表這是一個合法路徑 → 回傳 1
        if (i == n) return 1;

        // 狀況還沒結束，繼續遞迴：往下爬 1 或 2 階
        return dfs(n, i + 1) + dfs(n, i + 2);
    }
}