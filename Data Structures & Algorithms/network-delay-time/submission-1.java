// 2. Floyd Warshall Algorithm

public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 設定一個極大值，避免之後相加時 overflow（所以用 MAX_VALUE / 2）
        int inf = Integer.MAX_VALUE / 2;

        // 初始化距離矩陣 dist[i][j]：i 到 j 的最短距離
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], inf);  // 預設都是無窮遠
            dist[i][i] = 0;             // 自己到自己距離為 0
        }

        // 讀入邊的資訊（times[i] = [u, v, w]）
        for (int[] time : times) {
            int u = time[0] - 1;  // 注意：這裡 index 是從 0 開始 (所以要 -1)
            int v = time[1] - 1;
            int w = time[2];
            dist[u][v] = w;       // u 到 v 的權重為 w
        }

        // Floyd-Warshall 演算法三層迴圈：
        // 嘗試用中繼點 mid 來更新所有 dist[i][j]
        for (int mid = 0; mid < n; mid++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dist[i][j] = Math.min(dist[i][j],
                                          dist[i][mid] + dist[mid][j]);

        // 找出從 k-1 出發到所有點的最大距離（最慢收到訊號的節點）
        int res = Arrays.stream(dist[k - 1]).max().getAsInt();

        // 如果仍然是 inf，表示有點收不到訊號，回傳 -1
        return res == inf ? -1 : res;
    }
}

// 假設有四個節點 (0, 1, 2, 3)，代表城市或網路節點
// dist[i][j] 表示從節點 i 到節點 j 的目前最短距離

// 初始資料如下：
// 0 → 1 距離為 1
// 1 → 2 距離為 2
// 0 → 3 距離為 10
// 2 → 3 距離為 3

// 初始 dist 矩陣會長這樣：
// [
//   [0, 1, INF, 10],  // from 0
//   [INF, 0, 2, INF], // from 1
//   [INF, INF, 0, 3], // from 2
//   [INF, INF, INF, 0]
// ]

// 當 mid = 1（節點 1）時，我們檢查是否能透過節點 1 更新任兩點間的距離
// 例如：0 → 2 原本是 INF
// 但 0 → 1 是 1，1 → 2 是 2，所以 0 → 2 可以更新為 1 + 2 = 3
// 更新後：dist[0][2] = 3

// 當 mid = 2（節點 2）時，看看是否能透過節點 2 更新其他距離
// 例如：0 → 3 原本是 10
// 現在有 0 → 2 是 3（剛剛更新過），2 → 3 是 3
// 所以：0 → 3 可以變成 3 + 3 = 6，更短！
// 更新後：dist[0][3] = 6

// 執行完所有 mid（中繼點）後，dist 就會記錄所有節點間的最短距離