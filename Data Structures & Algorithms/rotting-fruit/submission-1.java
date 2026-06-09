// 2. Breadth First Search (No Queue)
public class Solution {
    public int orangesRotting(int[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        int fresh = 0, time = 0;

        // 1. 統計初始新鮮橘子數量
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == 1) fresh++;
            }
        }

        // 四個方向：右、左、下、上
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // 2. 當還有新鮮橘子，重複每分鐘的腐爛擴散
        while (fresh > 0) {
            boolean flag = false;  // 本輪是否有新橘子被腐爛

            // 2.1 掃描整個網格，對每個腐爛橘子嘗試感染鄰近新鮮橘子
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (grid[r][c] == 2) {  // 如果當前是腐爛橘子
                        for (int[] d : directions) {
                            int row = r + d[0], col = c + d[1];
                            // 邊界檢查，並且鄰格必須是新鮮橘子
                            if (row >= 0 && col >= 0 && 
                                row < ROWS && col < COLS && 
                                grid[row][col] == 1) {
                                grid[row][col] = 3;  // 暫標記為「即將腐爛」
                                fresh--;            // 新鮮橘子數量減一
                                flag = true;        // 標記本輪有感染發生
                            }
                        }
                    }
                }
            }

            // 2.2 若本輪沒有任何感染，代表剩餘新鮮橘子無法被腐爛 → 失敗 (提早結束)
            if (!flag) return -1;

            // 2.3 將所有標記為 3（即將腐爛）的橘子，正式標記為腐爛 (2)
            // Why 分兩次 1 -> 3 -> 2? 如果你在遍歷時一遇到新鮮橘子就立刻把它標記成 2（腐爛），那麼它會在同一次「分鐘」迴圈中又去感染它周圍的鄰居，導致一次迴圈走完就能多步傳播，這就不符合「每分鐘只能擴散一層」的邏輯了。
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (grid[r][c] == 3) grid[r][c] = 2;
                }
            }

            // 2.4 一輪腐爛擴散完成，時間增 1 分鐘
            time++;
        }

        // 3. 所有新鮮橘子均已腐爛，回傳所需分鐘數
        return time;
    }
}