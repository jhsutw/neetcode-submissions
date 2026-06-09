//  3. Binary Search + DFS
/*
這個版本比你前一版更高效，原因是：
✅ 用 Binary Search 減少了時間範圍查找次數（由 $O(n^2)$ 減少為 $O(\log n^2)$）
✅ 避免不必要的線性遞增嘗試
✅ 每次檢查水位 t = mid 時，只進行一次 DFS，並在 visit[][] 中控制訪問狀態
*/
public class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visit = new boolean[n][n];

        // 找出整張圖中的最大、最小高度
        int minH = grid[0][0], maxH = grid[0][0];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                maxH = Math.max(maxH, grid[row][col]);
                minH = Math.min(minH, grid[row][col]);
            }
        }

        // Binary Search：找出最小的可行水位 t，使得能從 (0,0) 游到 (n-1,n-1)
        int l = minH, r = maxH;
        while (l < r) {
            int m = (l + r) >> 1; // m = (l + r) / 2
            if (dfs(grid, visit, 0, 0, m)) {
                r = m; // 如果可行，繼續找更小的 t (下調右界)
            } else {
                l = m + 1; // 否則往右邊找（上調左界）
            }

            // 重置 visit 陣列
            for (int row = 0; row < n; row++) {
                Arrays.fill(visit[row], false);
            }
        }

        return r; // 此時 l == r，即最小可行時間
    }

    // 在水位 t 時，從 (0,0) 能否游到 (n-1,n-1)
    private boolean dfs(int[][] grid, boolean[][] visit, int r, int c, int t) {
        if (r < 0 || c < 0 || r >= grid.length ||
            c >= grid.length || visit[r][c] || grid[r][c] > t) {
            return false;
        }

        if (r == grid.length - 1 && c == grid.length - 1) {
            return true; // 成功抵達終點
        }

        visit[r][c] = true;

        return dfs(grid, visit, r + 1, c, t) ||
               dfs(grid, visit, r - 1, c, t) ||
               dfs(grid, visit, r, c + 1, t) ||
               dfs(grid, visit, r, c - 1, t);
    }
}
