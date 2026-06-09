public class Solution {
    // 定義四個方向：下、上、右、左
    private int[][] directions = {
        { 1,  0},
        {-1,  0},
        { 0,  1},
        { 0, -1}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int ROWS = heights.length;          // 總行數
        int COLS = heights[0].length;       // 總列數
        // pac[r][c] = true 表示從 (r,c) 能流向太平洋
        boolean[][] pac = new boolean[ROWS][COLS];
        // atl[r][c] = true 表示從 (r,c) 能流向大西洋
        boolean[][] atl = new boolean[ROWS][COLS];

        // 分 pac 跟 alt 兩片海域來做，最後再取交集
        // 1. 從上邊界和下邊界觸發 DFS
        for (int c = 0; c < COLS; c++) {
            dfs(0, c, pac, heights);            // 上邊所有點能到太平洋
            dfs(ROWS - 1, c, atl, heights);     // 下邊所有點能到大西洋
        }
        // 2. 從左邊界和右邊界觸發 DFS
        for (int r = 0; r < ROWS; r++) {
            dfs(r, 0, pac, heights);            // 左邊所有點能到太平洋
            dfs(r, COLS - 1, atl, heights);     // 右邊所有點能到大西洋
        }

        // 3. 收集同時能到達兩個海的格子
        List<List<Integer>> res = new ArrayList<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                // 只有兩個陣列都為 true，才能同時流向兩海
                if (pac[r][c] && atl[r][c]) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }
        return res;
    }

    /**
     * 深度優先搜尋，從 (r,c) 向內部擴散，
     * 標記所有能由 (r,c) 流出的格子到達當前「ocean」所代表的海邊界。
     *
     * @param r       當前行
     * @param c       當前列
     * @param ocean   目標海洋的標記矩陣 (pac 或 atl)
     * @param heights 高度矩陣
     */
    private void dfs(int r, int c, boolean[][] ocean, int[][] heights) {
        // 標記 (r,c) 已能流向該海
        ocean[r][c] = true;

        // 嘗試四個方向擴散
        for (int[] d : directions) {
            int nr = r + d[0], nc = c + d[1];
            // 邊界檢查，且該鄰居尚未標記過，
            // 且高度 >= 當前格高度（水只能從高往低或持平流動） 
            if (nr >= 0 && nr < heights.length &&
                nc >= 0 && nc < heights[0].length &&
                !ocean[nr][nc] &&
                heights[nr][nc] >= heights[r][c]) {
                dfs(nr, nc, ocean, heights);
            }
        }
    }
}

/*
| 特性        | 方法一：每格启动 DFS                       | 方法二：边界多源 DFS                               |
| --------- | ---------------------------------- | ------------------------------------------ |
| **核心思路**  | 对每个 `(r,c)` 单独做一次 DFS，判断能否同时到达双海   | 从所有太平洋边界点同时做 DFS，标记能流向太平洋的区域；再从大西洋边界同理，取交集 |
| **时间复杂度** | 最坏 O((MN)×(MN))，每个格子都要走一次完整地图      | O(MN)，每个格子仅被访问最多两次（一次太平洋，一次大西洋）            |
| **空间复杂度** | O(MN) 递归栈 + 无额外矩阵                  | O(MN) 递归栈 + O(MN) 标记矩阵                     |
| **访问方式**  | 自顶向下：从一个格子出发向四周扩散                  | 自底向内：从海岸向岛内扩散                              |
| **标记逻辑**  | 用 `Integer.MAX_VALUE` 暂时标记已访，回溯时恢复 | 用两个布尔矩阵 `pac[][]` / `atl[][]` 直接标记，不需恢复    |
| **实现复杂度** | 代码简单直观，但重复逻辑多                      | 代码稍复杂（要两次 DFS），但逻辑高效且去重                    |
| **优点**    | 易理解、一开始就能写出原型                      | 性能佳，适合大规模输入                                |
| **缺点**    | 极慢（大矩阵时会超时或栈溢出）                    | 需要维护两个状态矩阵，初始化边界时要注意四条边                    |
*/