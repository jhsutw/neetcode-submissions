// 3. Prim's Algorithm (Optimal)
// 不需建圖、不需優先佇列，直接用 dist[] 陣列保存與當前生成樹的最短距離。

public class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length, node = 0;

        int[] dist = new int[n];         // dist[i] 表示節點 i 到目前已連接集合的最小距離
        boolean[] visit = new boolean[n]; // 紀錄節點是否已納入 MST

        Arrays.fill(dist, 100000000);    // 初始設成一個很大的值，代表還沒更新
        int edges = 0, res = 0;          // edges 紀錄已連接幾條邊，res 為總成本

        while (edges < n - 1) {          // 生成 n-1 條邊即完成 MST
            visit[node] = true;          // 將目前節點納入 MST

            int nextNode = -1;

            // 更新還沒加入 MST 的所有點的距離
            for (int i = 0; i < n; i++) {
                if (visit[i]) continue;  // 已加入的不更新

                // 計算曼哈頓距離（計算當前node到其他node的距離，但不算visited的）
                int curDist = Math.abs(points[i][0] - points[node][0]) +
                              Math.abs(points[i][1] - points[node][1]);

                // 若新的距離更小就更新 dist[i]
                dist[i] = Math.min(dist[i], curDist);

                // 找出距離最小的下一個節點
                /*
                1. nextNode == -1：代表目前還沒有找到任何候選節點（第一次進入這段時一定成立）
                2. dist[i] < dist[nextNode]：比較目前第 i 個點與當前候選節點（nextNode）的最小距離
                → 如果第 i 個點距離更小，說明它更適合成為下一個節點
                如果上面兩個條件其中一個成立，就把 i 設為新的 nextNode 候選
                */
                if (nextNode == -1 || dist[i] < dist[nextNode]) {
                    nextNode = i;
                }
            }

            res += dist[nextNode]; // 將這條最小邊加入總成本
            node = nextNode;       // 將下一個節點設為當前節點，進行下一輪
            edges++;               // 成功連接一條邊
        }

        return res; // 回傳總最小成本
    }
}