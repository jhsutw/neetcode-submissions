public class Solution {
    // static 表示這個欄位屬於類別（class）本身，而不是某個物件（instance）。
    // final 修飾引用型別的欄位時，代表「這個引用一旦被初始化，就不能再指向別的陣列」。

    private static final int[][] directions = {  // 四個方向：下、上、右、左
        { 1,  0}, 
        {-1,  0}, 
        { 0,  1}, 
        { 0, -1}
    };
    
    public int numIslands(char[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        int islands = 0;                     // 計數島嶼數量
        
        // 掃描每個格子
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == '1') {     // 發現一塊尚未標記的陸地
                    dfs(grid, r, c);         // 將整座島嶼「淹沒」為水
                    islands++;               // 計數 +1
                }
            }
        }
        
        return islands;                     // 回傳總島嶼數
    }
    
    private void dfs(char[][] grid, int r, int c) {
        // 邊界檢查或遇到海水就停止
        if (r < 0 || c < 0 || r >= grid.length ||
            c >= grid[0].length || grid[r][c] == '0') {
            return;
        }
        
        grid[r][c] = '0';                   // 標記此格為「已訪」，以免重複計數
                                            // 不用回溯，因為四個方向都算是同一塊地（要回溯的case是某節點的另一個方向可以有不同的組合，如文字尋找）
        
        // 向四個方向遞迴
        for (int[] dir : directions) {
            dfs(grid, r + dir[0], c + dir[1]);
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
// 1. 掃描到 (0,0) = '1' → islands=1 → dfs「淹沒」(0,0),(0,1),(1,0),(1,1) 全部變 '0'
//    grid 變為：
//    [
//      ['0','0','0','0','0'],
//      ['0','0','0','0','0'],
//      ['0','0','1','0','0'],
//      ['0','0','0','1','1']
//    ]
//
// 2. 繼續掃描，直到遇到 (2,2) = '1' → islands=2 → dfs 將 (2,2) 變 '0'
//
// 3. 再掃描，遇到 (3,3) = '1' → islands=3 → dfs 淹沒 (3,3),(3,4)
//
// 最終回傳 3（共三座島嶼）。