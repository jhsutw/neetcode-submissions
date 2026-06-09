// 2. Depth First Search（Linear Search + DFS）
public class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visit = new boolean[n][n];

        // 找出地圖中最低和最高的高度
        int minH = grid[0][0], maxH = grid[0][0];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                maxH = Math.max(maxH, grid[row][col]);
                minH = Math.min(minH, grid[row][col]);
            }
        }

        // 從最小高度開始，逐步嘗試每個時間 t，直到能成功從 (0,0) 到 (n-1,n-1)
        for (int t = minH; t < maxH; t++) { // 降雨水位高度從minH開始++ (遍歷每種水位高的能走的路徑)
            if (dfs(grid, visit, 0, 0, t)) {
                return t; // 找到第一個可行時間 t 就回傳
            }
            // 重置訪問紀錄（每次走完一種路徑就清除走訪記錄，走下一個路徑）
            for (int r = 0; r < n; r++) {
                Arrays.fill(visit[r], false);
            }
        }
        return maxH; // 如果沒在 loop 裡 return，就代表只有最大高度可行
    }

    // DFS 搜尋是否能在時間 t（即水位 t）情況下從 (0,0) 達到 (n-1,n-1)
    private boolean dfs(int[][] grid, boolean[][] visit, int r, int c, int t) {
        if (r < 0 || c < 0 || r >= grid.length ||
            c >= grid.length || visit[r][c] || grid[r][c] > t) {
            return false;
        }
        if (r == grid.length - 1 && c == grid.length - 1) {
            return true;
        }
        visit[r][c] = true;
        return dfs(grid, visit, r + 1, c, t) ||
               dfs(grid, visit, r - 1, c, t) ||
               dfs(grid, visit, r, c + 1, t) ||
               dfs(grid, visit, r, c - 1, t);
    }
}