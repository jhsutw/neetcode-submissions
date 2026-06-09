public class Solution {
    // 全局變數：行數、列數
    int ROWS, COLS;
    // DFS 過程中標記能否到達太平洋或大西洋
    boolean pacific, atlantic;
    // 四個方向：下、上、右、左
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // 初始化行列
        ROWS = heights.length;
        COLS = heights[0].length;
        // 結果列表
        List<List<Integer>> res = new ArrayList<>();

        // 對每個格子 (r, c) 啟動 DFS
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                // 每次重置標記
                pacific = false;
                atlantic = false;
                // 從 (r, c) 出發，prevVal 設為極大值，確保首格允許走入
                dfs(heights, r, c, Integer.MAX_VALUE);
                // 如果同時能到兩個海洋，加入結果
                if (pacific && atlantic) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }
        return res;
    }

    /**
     * 深度優先搜尋
     * @param heights 海拔矩陣
     * @param r       當前行
     * @param c       當前列
     * @param prevVal 上一格的高度限制（水只能往低或同高度流）
     */
    private void dfs(int[][] heights, int r, int c, int prevVal) {
        // 走出上邊或左邊界 → 到達太平洋
        if (r < 0 || c < 0) {
            pacific = true;
            return;
        }
        // 走出下邊或右邊界 → 到達大西洋
        if (r >= ROWS || c >= COLS) {
            atlantic = true;
            return;
        }
        // 若當前高度高於上一格，不能「往上流」，停止
        if (heights[r][c] > prevVal) {
            return;
        }

        // 暫存當前高度，並標記已訪（避免無限遞迴）
        int tmp = heights[r][c];
        heights[r][c] = Integer.MAX_VALUE; // 擔心流過了以後又流回來（loop）

        // 對四個方向繼續 DFS
        for (int[] dir : directions) {
            dfs(heights, r + dir[0], c + dir[1], tmp); // (tmp為上一格的高度 = preVal)
            // 若已同時到達兩海，提前結束
            if (pacific && atlantic) {
                break;
            }
        }

        // 回溯：還原高度
        heights[r][c] = tmp;
    }
}