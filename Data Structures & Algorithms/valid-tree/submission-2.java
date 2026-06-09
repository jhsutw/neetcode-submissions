// 3. Disjoint Set Union
// 並查集（Disjoint Set Union）結構，用於追蹤連通分量並檢測環路
class DSU {
    private int[] parent;  // parent[i] = 節點 i 的父節點
    private int[] size;    // size[i]   = 以 i 為根的樹的節點數
    private int comps;     // 當前連通分量數（根節點數量）

    // 建構子：初始化 n 個節點，各自為一個連通分量
    public DSU(int n) {
        this.comps = n;
        this.parent = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;   // 一開始每個節點都是自己的父節點
            size[i] = 1;     // 每棵樹初始只有 1 個節點
        }
    }

    // 查找操作：尋找 node 的根節點，並進行「路徑壓縮」
    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);  // 遞迴壓平樹的高度
        }
        return parent[node];
    }

    // 合併操作：將 u, v 所屬集合合併，若已在同一集合，返回 false（表示檢測到環）
    public boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if (pu == pv) {
            return false;  // u 和 v 已連通，新增此邊會形成環
        }
        // 「按大小」合併：小樹接到大樹下（若pu的size比pv小，就把pu跟pv交換 >> 維持pu比pv大）
        if (size[pu] < size[pv]) {
            int tmp = pu;
            pu = pv;
            pv = tmp;
        }
        // 處理完後pu的size比pv大，pu成為pv的parent
        parent[pv] = pu;       // 將 pv 的根接到 pu
        size[pu] += size[pv];  // 更新新根的樹大小
        comps--;               // 連通分量減 1
        return true;
    }

    // 回傳當前的連通分量數
    public int components() {
        return comps;
    }
}

// 主解法：判斷 n 個節點和 edges 定義的無向圖是否構成一棵樹
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // 一棵樹在有 n 個節點時，邊數必須 ≤ n-1；若邊數 > n-1，必有環
        if (edges.length > n - 1) {
            return false;
        }

        // 使用 DSU 進行連通性與環路檢測
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            // 若 union 返回 false，表示該邊連接了已連通的節點 → 有環
            if (!dsu.union(edge[0], edge[1])) {
                return false;
            }
        }
        // 檢查是否只有 1 個連通分量，若是，則該圖連通且無環，即為樹
        return dsu.components() == 1;
    }
}