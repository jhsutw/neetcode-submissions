// 4. Dijkstra's Algorithm
/*
✅ 這段程式碼的核心概念：
每個格子視為圖中的一個節點，grid[i][j] 表示進入該格所需的「最低水位」。
你的目標是從 (0, 0) 出發，到 (N-1, N-1) 的一條路徑，讓沿途經過的最大高度最小化。

*/
public class Solution {
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        boolean[][] visit = new boolean[N][N];

        // 優先佇列（小頂堆），根據當前最大水位排序
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0])
        );

        int[][] directions = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };

        // 起點加入 minHeap，格式為 {目前路徑上的最大水位, row, col} 
        minHeap.offer(new int[]{grid[0][0], 0, 0});
        visit[0][0] = true;

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll(); // 代表每次走的路徑為最大水位小者
            int t = curr[0]; // 當前到這格為止，經過的最大水位
            int r = curr[1], c = curr[2];

            // 抵達終點，回傳所需水位
            if (r == N - 1 && c == N - 1) {
                return t;
            }

            // 向四個方向擴展
            for (int[] dir : directions) {
                int neiR = r + dir[0], neiC = c + dir[1];
                // 邊界檢查 + 沒有走過
                if (neiR >= 0 && neiC >= 0 && neiR < N &&
                    neiC < N && !visit[neiR][neiC]) {
                    
                    visit[neiR][neiC] = true;

                    // 將鄰居加入 heap，更新路徑上的最大高度
                    minHeap.offer(new int[]{
                        Math.max(t, grid[neiR][neiC]), // 取node跟neinode最大水位大者
                        neiR, neiC
                    });
                }
            }
        }

        // 理論上不會走到這裡
        return N * N;
    }
}