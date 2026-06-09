/** 2. Breadth First Search
 * islandsAndTreasure 方法（BFS 版本）：
 * - grid：二維整數矩陣
 *     - -1 表示障礙（無法通行）
 *     -  0 表示寶藏位置（目標點）
 *     - INF (2147483647) 表示待計算的空房間
 * 功能：將每個 INF 房間的值替換為它到最近寶藏（值為 0）的最短距離
 * 實作：對每個 INF 單元呼叫 bfs，層序遍歷逐步擴散，遇到 0 即返回當前步數
 */
public class Solution {
    // 四個移動方向：下、上、右、左
    private int[][] directions = {
        { 1,  0},  // 向下
        {-1,  0},  // 向上
        { 0,  1},  // 向右
        { 0, -1}   // 向左
    };
    private int INF = 2147483647;  // 定義 INF 常數
    private int ROWS, COLS;        // grid 的行數與列數

    /**
     * 從 (r,c) 開始執行 BFS，返回到最近寶藏（0）的最短步數
     * @param grid 二維整數矩陣
     * @param r 起點行索引
     * @param c 起點列索引
     * @return 最短距離；若無法到達任何 0，返回 INF
     */
    private int bfs(int[][] grid, int r, int c) {
        // 1. 初始化隊列與訪問矩陣
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});               // 將起點放入隊列
        boolean[][] visit = new boolean[ROWS][COLS];
        visit[r][c] = true;                    // 標記起點為已訪

        int steps = 0;                         // BFS 層數（距離）

        // 2. 層序遍歷
        while (!q.isEmpty()) {
            int size = q.size();              // 當前層節點數
            // 處理當前層所有節點
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();        // 取出一個節點
                int row = curr[0], col = curr[1];
                // 若當前位置為寶藏，直接返回步數
                if (grid[row][col] == 0) {
                    return steps;
                }
                // 否則，向四個方向擴散
                for (int[] dir : directions) {
                    int nr = row + dir[0], nc = col + dir[1];
                    // 邊界檢查 + 未訪且不是障礙
                    if (nr >= 0 && nr < ROWS &&
                        nc >= 0 && nc < COLS &&
                        !visit[nr][nc] &&
                        grid[nr][nc] != -1) {
                        visit[nr][nc] = true;            // 標記訪問
                        q.add(new int[]{nr, nc});        // 入隊
                    }
                }
            }
            steps++;   // 完成一層後，距離 +1
        }
        // 隊列清空仍未找到寶藏 → 無法到達，回傳 INF
        return INF;
    }

    /**
     * 主方法：遍歷每個 INF 單元，調用 bfs 填入到最近寶藏的最短距離
     * @param grid 二維整數矩陣，調用後被原地修改
     */
    public void islandsAndTreasure(int[][] grid) {
        ROWS = grid.length;       // 記錄行數
        COLS = grid[0].length;    // 記錄列數

        // 遍歷所有格子
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                // 只處理值為 INF 的房間
                if (grid[r][c] == INF) {
                    // 調用 bfs 並將結果寫回該格
                    grid[r][c] = bfs(grid, r, c);
                }
            }
        }
    }
}