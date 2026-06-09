// 1. Depth First Search
/**
 * 計算二維整數網格中最大的島嶼面積。
 * - 島嶼由值為 1 的相鄰格子（上下左右）組成。
 * - 遍歷每個格子，若為 1 則啟動 DFS，統計該島嶼面積並更新最大值。
 * - DFS 中將走過的陸地標記為 0，避免重複計算。
 */
public class Solution {
    // 四個移動方向：下、上、右、左
    private static final int[][] directions = {
        { 1,  0},
        {-1,  0},
        { 0,  1},
        { 0, -1}
    };
    
    public int maxAreaOfIsland(int[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        int maxArea = 0;                  // 當前最大島嶼面積
        
        // 掃描所有格子
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                // 遇到陸地 (1) 就計算該島嶼面積
                if (grid[r][c] == 1) {
                    int area = dfs(grid, r, c);
                    maxArea = Math.max(maxArea, area); // 更新最大值
                }
            }
        }
        
        return maxArea;
    }
    
    // 從 (r, c) 開始，DFS 計算並回傳連通區域的面積
    private int dfs(int[][] grid, int r, int c) {
        // 越界或遇到海水 (0) 就停止，回傳 0，不會再找上下左右是否有陸地
        if (r < 0 || c < 0 || r >= grid.length || 
            c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }
        
        grid[r][c] = 0;                   // 標記為已訪（設為 0，避免重複）
        int area = 1;                     // 當前格子面積 = 1
        // 四個方向繼續累加面積
        for (int[] dir : directions) {
            area += dfs(grid, r + dir[0], c + dir[1]);
        }
        return area;
    }
}

// 以下為執行流程示例：
// 輸入 grid：
// [
//   [0,0,1,0,0,0,0,1,0,0,0,0,0],
//   [0,0,0,0,0,0,0,1,1,1,0,0,0],
//   [0,1,1,0,1,0,0,0,0,0,0,0,0],
//   [0,1,0,0,1,1,0,0,1,0,1,0,0],
//   [0,1,0,0,1,1,0,0,1,1,1,0,0],
//   [0,0,0,0,0,0,0,0,0,0,1,0,0],
//   [0,0,0,0,0,0,0,1,1,1,0,0,0],
//   [0,0,0,0,0,0,0,1,1,0,0,0,0]
// ]
// 1. 首次遇到 (0,2)=1 → DFS 只遍歷該單格，area=1 → maxArea=1。
// 2. 遇到 (0,7)=1 → DFS 遍歷 (0,7),(1,7),(1,8),(1,9) 等，area=6 → maxArea=6。
// 3. 繼續掃描找到更大的島嶼，如在 (2,1) 及後續，最終最大面積 = 6。
// 最終回傳 6。