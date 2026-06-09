// 2. Breadth First Search
public class Solution {
    // 四個移動方向：下、上、右、左
    private static final int[][] directions = {
        { 1,  0},
        {-1,  0},
        { 0,  1},
        { 0, -1}
    };
    
    public int numIslands(char[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        int islands = 0;  // 記錄發現的島嶼數
        
        // 掃描每一格
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                // 遇到陸地 '1' 就啟動 BFS 把整座島淹成 '0'
                if (grid[r][c] == '1') {
                    bfs(grid, r, c);
                    islands++;  // 完成一次完整淹島流程，島嶼計數 +1
                }
            }
        }
        
        return islands;
    }
    
    private void bfs(char[][] grid, int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        grid[r][c] = '0';                 // 標記為已訪（淹成海水）
        q.add(new int[]{r, c});           // 起點入隊
        
        // 只要隊列不空，就繼續擴散
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int row = node[0], col = node[1];
            
            // 四個方向檢查相鄰格
            for (int[] dir : directions) {
                int nr = row + dir[0], nc = col + dir[1];
                // 確保在界內且為陸地，才入隊並標記
                if (nr >= 0 && nc >= 0 && nr < grid.length &&
                    nc < grid[0].length && grid[nr][nc] == '1') {
                    q.add(new int[]{nr, nc});
                    grid[nr][nc] = '0';  // 標記為已訪
                }
            }
        }
    }
}

// 以下為執行流程示例：
// 輸入 grid：
// [
//   ['1','1','0','0','0'],
//   ['1','1','0','0','0'],
//   ['0','0','1','0','0'],
//   ['0','0','0','1','1']
// ]
//
// 1. 掃描到 (0,0) = '1' → islands=1 → 呼叫 bfs:
//    - 將 (0,0) 標為 '0'，入隊 [(0,0)]。
//    - 出隊 (0,0)，檢查四方向：將 (1,0)、(0,1) 入隊並標為 '0'。
//    - 出隊 (1,0)，檢查：將 (1,1) 入隊並標 '0'。
//    - 出隊 (0,1)，檢查：無新格可入隊。
//    - 出隊 (1,1)，檢查：無新格可入隊。
//    BFS 結束，第一座島嶼全部變 '0'。
//
// 2. 繼續掃描，到 (2,2) = '1' → islands=2 → bfs 同理淹沒 (2,2)。
//
// 3. 再掃描，到 (3,3) = '1' → islands=3 → bfs 淹沒 (3,3),(3,4)。
//
// 最終回傳 3（共三座島嶼）。