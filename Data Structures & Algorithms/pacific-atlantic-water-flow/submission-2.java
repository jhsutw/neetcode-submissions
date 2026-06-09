// 3. Breadth First Search
public class Solution {
    // 四個移動方向：下、上、右、左
    private int[][] directions = {
        { 1,  0},
        {-1,  0},
        { 0,  1},
        { 0, -1}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // 取得行數與列數
        int ROWS = heights.length;
        int COLS = heights[0].length;
        // pac[r][c] = true 表示 (r,c) 能流向太平洋
        boolean[][] pac = new boolean[ROWS][COLS];
        // atl[r][c] = true 表示 (r,c) 能流向大西洋
        boolean[][] atl = new boolean[ROWS][COLS];

        // 建立太平洋與大西洋的多源 BFS 隊列
        Queue<int[]> pacQueue = new LinkedList<>();
        Queue<int[]> atlQueue = new LinkedList<>();

        // 將上邊界 (第一行) 加入太平洋隊列，下邊界 (最後一行) 加入大西洋隊列
        for (int c = 0; c < COLS; c++) {
            pacQueue.add(new int[]{0, c});            // 太平洋邊界：第一行
            atlQueue.add(new int[]{ROWS - 1, c});     // 大西洋邊界：最後一行
        }
        // 將左邊界 (第一列) 加入太平洋隊列，右邊界 (最後一列) 加入大西洋隊列
        for (int r = 0; r < ROWS; r++) {
            pacQueue.add(new int[]{r, 0});            // 太平洋邊界：第一列
            atlQueue.add(new int[]{r, COLS - 1});     // 大西洋邊界：最後一列
        }

        // 分別從太平洋邊界與大西洋邊界執行 BFS，標記可達區域
        bfs(pacQueue, pac, heights);
        bfs(atlQueue, atl, heights);

        // 收集同時能流向兩海的座標
        List<List<Integer>> res = new ArrayList<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (pac[r][c] && atl[r][c]) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }
        return res;
    }

    /**
     * 多源 BFS
     * @param q       起點座標隊列（所有邊界格子）
     * @param ocean   標記矩陣，記錄哪些格子已可流向該海洋
     * @param heights 高度矩陣
     */
    private void bfs(Queue<int[]> q, boolean[][] ocean, int[][] heights) {
        // 當隊列不空，持續擴散
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            // 標記當前格子可流向對應海洋
            ocean[r][c] = true;

            // 嘗試向四個方向擴散
            for (int[] d : directions) {
                int nr = r + d[0], nc = c + d[1];
                // 邊界檢查、尚未標記，且高度條件滿足（可從高往低或等高流）
                if (nr >= 0 && nr < heights.length &&
                    nc >= 0 && nc < heights[0].length &&
                    !ocean[nr][nc] &&
                    heights[nr][nc] >= heights[r][c]) {
                    // 將符合條件的鄰居加入隊列
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
}

/*
| 特性        | 邊界 DFS 方法                                   | 邊界 BFS 方法                             |
| --------- | ------------------------------------------- | ------------------------------------- |
| **核心思路**  | 從太平洋/大西洋邊界各自啟動遞迴 DFS，深度探索能流向海洋的路徑           | 從太平洋/大西洋邊界各自啟動多源 BFS，層級（廣度）探索能流向海洋的路徑 |
| **資料結構**  | 兩個 boolean\[]\[] 標記矩陣 + 系統呼叫堆疊              | 兩個 boolean\[]\[] 標記矩陣 + 兩個佇列 (Queue)  |
| **訪問順序**  | 深度優先：每走一步就往下一層遞迴                            | 廣度優先：先將當前層所有節點處理完再進入下一層               |
| **標記方式**  | 在進入 DFS 時立即標記 ocean\[r]\[c] = true，無需額外回溯還原 | 在出隊時標記 ocean\[r]\[c] = true，避免重複入隊    |
| **時間複雜度** | O(M×N)，每個格子最多被標記一次                          | O(M×N)，每個格子最多被入隊出隊一次                  |
| **空間複雜度** | O(M×N) 遞迴深度（最壞情況）                           | O(M×N) 佇列大小（最壞情況）                     |
| **程式複雜度** | 程式行數較少，但要注意遞迴邊界與呼叫堆疊限制                      | 初始化佇列步驟較多，但邏輯直觀、不易爆堆疊                 |
| **適用場景**  | 小至中等尺寸的矩陣，或語言對遞迴優化較好時                       | 中大型矩陣或需嚴格控制記憶體使用、避免遞迴深度過深時            |

*/