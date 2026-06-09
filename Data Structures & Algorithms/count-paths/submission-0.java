// 1. Recursion
public class Solution {
    public int uniquePaths(int m, int n) {
        // 從起點 (0, 0) 開始遞迴搜尋
        return dfs(0, 0, m, n);
    }

    // i, j 表示當前位置
    // m 代表網格的行數（row count）; n 代表網格的列數（column count）
    public int dfs(int i, int j, int m, int n) {
        // 抵達右下角終點時，找到一條路徑
        if (i == (m - 1) && j == (n - 1)) {
            return 1;
        }
        // 越界時，無效路徑
        if (i >= m || j >= n) return 0;

        // 向右走一格 + 向下走一格
        return dfs(i, j + 1, m, n) + // 往右
               dfs(i + 1, j, m, n);  // 往下
    }
}
