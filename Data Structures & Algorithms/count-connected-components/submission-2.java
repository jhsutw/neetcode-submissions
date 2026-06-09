// 3. Disjoint Set Union (Rank | Size)
// 並查集（Disjoint Set Union）結構，用於追蹤連通分量
class DSU {
    private int[] parent;  // parent[i] = 節點 i 的父節點
    private int[] rank;    // rank[i]   = 以 i 為根的樹的「秩」（近似深度）

    // 建構子：初始化 n 個節點，各自為一個獨立集合
    public DSU(int n) {
        parent = new int[n];
        rank   = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;  // 每個節點起初指向自己
            rank[i]   = 1;  // 初始秩為 1
        }
    }

    // 查找操作：尋找 node 的根節點，並做「路徑壓縮」（halving）
    public int find(int node) {
        int cur = node;
        // 一直追溯到自己的父節點等於自己（一開始預設每個點的parent是自己），即為根（回溯根節點）
        while (cur != parent[cur]) {
            // 路徑壓縮：將 cur 的父節點指向爺爺節點，加速下次查詢
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        return cur;
    }

    // 合併操作：將 u, v 兩個集合合併
    // 如果已在同一集合，返回 false（表示發現環或重複邊）
    public boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if (pu == pv) {
            return false;  // 已經連通，不做合併
        }
        // 按秩合併：將秩較小的根接到秩較大的根下
        if (rank[pv] > rank[pu]) {
            // swap pu, pv，確保 pu 的秩 >= pv 的秩
            int temp = pu;
            pu = pv;
            pv = temp;
        }
        // 將 pv 連到 pu
        parent[pv] = pu;
        // 如果兩棵樹秩相同，pu 的秩 +1；否則保持 pu 本身秩不變
        rank[pu] += rank[pv];
        return true;
    }
}

// 主解法：計算無向圖的連通分量數量
public class Solution {
    public int countComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int res = n;  // 初始時有 n 個獨立分量
        
        // 對每條邊嘗試合併，若成功合併則分量數 -1
        for (int[] edge : edges) {
            if (dsu.union(edge[0], edge[1])) {
                res--;
            }
        }
        
        // 返回最終的連通分量數
        return res;
    }
}

// 假設：n = 5, edges = [[0,1], [2,3]]
// 初始狀態：
// parent = [0, 1, 2, 3, 4]
// rank   = [1, 1, 1, 1, 1]
// res     = 5  // 初始連通分量數

// 處理第一條邊 [0,1]
// find(0) → 0
// find(1) → 1
// union(0,1) 成功：
//   parent[1] = 0
//   rank[0]   = 2
//   res       = 4

// 處理第二條邊 [2,3]
// find(2) → 2
// find(3) → 3
// union(2,3) 成功：
//   parent[3] = 2
//   rank[2]   = 2
//   res       = 3

// 最終回傳：res = 3  
// 代表三個連通分量：{0,1}, {2,3}, {4}