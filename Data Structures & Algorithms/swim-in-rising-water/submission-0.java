// 1. Brute Force
/*
🧠 整體邏輯解釋
這個解法的核心是：
嘗試所有可能的從 (0,0) 到 (n-1,n-1) 的路徑。
每條路徑的代價是：這條路上經過的最大高度（也就是最少水位要升到的高度）
回傳所有合法路徑中需要的最小水位。
*/
public class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visit = new boolean[n][n];

        return dfs(grid, visit, 0, 0, 0); // 從 (0, 0) 出發，起始時間 t = 0
    }

    private int dfs(int[][] grid, boolean[][] visit,
                    int r, int c, int t) {
        int n = grid.length;

        // 超出邊界或已造訪就不繼續
        if (r < 0 || c < 0 || r >= n || c >= n || visit[r][c]) {
            return 1000000; // 一個足夠大的數，代表無效路徑
        }

        // 到達終點，回傳目前經過的最大高度（也就是需要的最小水位）
        if (r == n - 1 && c == n - 1) {
            return Math.max(t, grid[r][c]);
        }

        visit[r][c] = true; // 標記當前點為已訪問
        t = Math.max(t, grid[r][c]); // 更新目前經過路徑的最大高度

        // 往四個方向 DFS，取最小的路徑成本（每條路徑的成本是制高點的高度，找成本最小的路徑）
        int res = Math.min( // Math.min只能放兩個元素所以分兩個比較
            Math.min(dfs(grid, visit, r + 1, c, t),
                     dfs(grid, visit, r - 1, c, t)),
            Math.min(dfs(grid, visit, r, c + 1, t),
                     dfs(grid, visit, r, c - 1, t))
        );

        visit[r][c] = false; // 回溯（解除標記），讓其他路徑可以走這格

        return res; // 回傳此路徑的最小水位需求
    }
}