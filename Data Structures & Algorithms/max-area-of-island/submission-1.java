// 2. Breadth First Search
/**
 * 計算二維整數網格中最大的島嶼面積 (Max Area of Island)。
 * - 島嶼由值為 1 的相鄰格子（上下左右）組成。
 * - 遍歷每個格子，若為 1 則啟動 BFS，計算該島嶼面積並更新最大值。
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
        int maxArea = 0;  // 紀錄目前找到的最大島嶼面積
        
        // 遍歷每個格子
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                // 若遇到陸地 (1)，就執行 BFS 並取最大值
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, bfs(grid, r, c));
                }
            }
        }
        
        return maxArea;
    }
    
    // 從 (r, c) 開始，使用 BFS 計算連通的陸地面積
    private int bfs(int[][] grid, int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        grid[r][c] = 0;                 // 標記為已訪（設為 0，避免重複計算）
        q.add(new int[]{r, c});         // 初始節點入隊
        int area = 1;                   // 初始面積 = 當前格子
        
        // 當隊列不空，持續擴展
        while (!q.isEmpty()) { // 代表q中的node都為陸地"1"，要繼續往四周擴散
            int[] node = q.poll();
            int row = node[0], col = node[1];
            
            // 檢查四個方向
            for (int[] dir : directions) {
                int nr = row + dir[0], nc = col + dir[1];
                // 邊界內且為陸地 (1) 才入隊
                if (nr >= 0 && nr < grid.length &&
                    nc >= 0 && nc < grid[0].length &&
                    grid[nr][nc] == 1) {
                    
                    q.add(new int[]{nr, nc});  // 加入隊列
                    grid[nr][nc] = 0;          // 標記為已訪
                    area++;                    // 累加面積
                }
            }
        }
        
        return area;  // 回傳該島嶼的總面積
    }
}