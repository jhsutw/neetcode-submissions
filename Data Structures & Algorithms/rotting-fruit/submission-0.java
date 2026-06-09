// 1. Breadth First Search
public class Solution {
    public int orangesRotting(int[][] grid) {
        // 使用隊列進行多源 BFS，從所有初始腐爛的橘子同時擴散
        Queue<int[]> q = new ArrayDeque<>();
        int fresh = 0;   // 記錄新鮮橘子的總數
        int time = 0;    // 經過的分鐘數

        // 1. 初始化：遍歷網格，統計新鮮橘子並將所有腐爛橘子入隊
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {            // 找到新鮮橘子
                    fresh++;                     // 新鮮橘子數量 +1
                } else if (grid[r][c] == 2) {     // 找到腐爛橘子
                    q.offer(new int[]{r, c});    // 加入隊列作為 BFS 起點
                }
            }
        }

        // 2. 定義四個方向：右、左、下、上
        int[][] directions = {
            { 0,  1},  // 向右
            { 0, -1},  // 向左
            { 1,  0},  // 向下
            {-1,  0}   // 向上
        };

        // 3. 開始層序遍歷：每一層表示一分鐘的腐爛擴散
        while (fresh > 0 && !q.isEmpty()) {
            int size = q.size();  // 當前層待處理的腐爛橘子數量
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0], c = curr[1];
                // 嘗試將腐爛擴散到四周的新鮮橘子
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    // 邊界檢查 + 確保目標是新鮮橘子
                    if (nr >= 0 && nr < grid.length &&
                        nc >= 0 && nc < grid[0].length &&
                        grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;            // 將該橘子標記為腐爛
                        q.offer(new int[]{nr, nc}); // 把新腐爛橘子加入隊列，供下一分鐘擴散
                        fresh--;                     // 新鮮橘子數量減一
                    }
                }
            }
            time++;  // 處理完一層後，時間（分鐘）累加
        }

        // 4. 如果所有橘子都腐爛了，返回所需時間；否則返回 -1
        return (fresh == 0) ? time : -1;
    }
}

