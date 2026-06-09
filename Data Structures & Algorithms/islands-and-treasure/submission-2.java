/**
 * islandsAndTreasure 方法（多源 BFS 版本）：
 * - grid：二維整數矩陣
 *     - -1 表示障礙（不可通行）
 *     -  0 表示寶藏位置（距離為 0）
 *     - Integer.MAX_VALUE 表示待計算的房間
 * 功能：將每個 Integer.MAX_VALUE 房間的值替換為它到最近寶藏（0）的最短距離
 * 實作：先將所有寶藏位置入隊，然後同時從這些起點做 BFS，逐層擴散並填距離
 */
public class Solution {
    public void islandsAndTreasure(int[][] grid) {
        // 用佇列儲存待處理的格子 (row, col)
        Queue<int[]> q = new LinkedList<>();

        // 取得網格尺寸
        int m = grid.length;
        int n = grid[0].length;

        // 1) 將所有寶藏格 (值為 0) 加入佇列作為 BFS 起點 (從寶藏地往外填INF到保障的距離)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    q.add(new int[] { i, j });
                }
            }
        }
        // 若沒有任何寶藏，就不用處理
        if (q.isEmpty()) {
            return;
        }

        // 定義四個鄰接方向：上、左、下、右
        int[][] dirs = {
            { -1,  0 },  // 向上
            {  0, -1 },  // 向左
            {  1,  0 },  // 向下
            {  0,  1 }   // 向右
        };

        // 2) 多源 BFS：從所有寶藏同時開始，逐層擴散
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int row = node[0];
            int col = node[1];

            // 檢查當前格子的四個方向
            for (int[] dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];

                // 邊界檢查：超出範圍或遇到障礙(-1) 或已被賦值(非 MAX_VALUE) → 跳過
                if (r < 0 || r >= m || c < 0 || c >= n ||
                    grid[r][c] != Integer.MAX_VALUE) {
                    continue;
                }

                // 更新距離：鄰居格距離 = 當前格距離 + 1
                grid[r][c] = grid[row][col] + 1;

                // 將該格加入佇列，繼續往下層擴散
                q.add(new int[] { r, c });
            }
        }
    }
}
/*
| 方法         | 實作概述                                         | 時間複雜度      | 空間複雜度                 | 優點                    | 缺點                         |
| ---------- | -------------------------------------------- | ---------- | --------------------- | --------------------- | -------------------------- |
| 方法一：每格 DFS | 對每個 `INF` 格子啟動遞迴 DFS，搜尋最近的 `0`，並取最小距離        | O((MN)×MN) | O(MN) (遞迴深度+visited)  | 實作最直觀、程式最簡潔           | 極慢（TLE），易爆 recursion stack |
| 方法二：每格 BFS | 對每個 `INF` 格子啟動隊列 BFS，逐層擴散直到遇到 `0`            | O((MN)×MN) | O(MN) (queue+visited) | 保證最短路徑、無遞迴風險          | 同樣太慢、重複大量搜尋                |
| 方法三：多源 BFS | 一開始把所有 `0` 入隊，然後一次 BFS 填寫所有 `INF` 的最短距離，單次遍歷 | O(MN)      | O(MN) (queue)         | 最優時間複雜度；實際面試 & 線上題目常用 | 需先掃描收集所有 `0`，稍微複雜一點        |
*/