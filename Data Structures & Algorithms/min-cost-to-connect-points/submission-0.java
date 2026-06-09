// 1. Kruskal's Algorithm
/*
✅ 程式碼功能總覽：
1. DSU（Disjoint Set Union） 用於檢查兩個點是否已經連通，避免產生環。
2. Kruskal's algorithm：將所有邊按距離排序，從小到大依序加入，若形成環就跳過。
*/
// 幫助判斷兩點是否已在同一集合的資料結構（並查集）
class DSU {
    int[] Parent, Size;

    public DSU(int n) {
        Parent = new int[n + 1];  // 每個點的父節點初始化為自己
        Size = new int[n + 1];    // 每個集合的大小
        for (int i = 0; i <= n; i++) Parent[i] = i;
        Arrays.fill(Size, 1);
    }

    // 查找某節點的代表（含路徑壓縮）
    public int find(int node) {
        if (Parent[node] != node) {
            Parent[node] = find(Parent[node]); // 路徑壓縮
        }
        return Parent[node];
    }

    // 將兩個集合合併，若已經在同一集合則返回 false
    public boolean union(int u, int v) {
        int pu = find(u), pv = find(v);
        if (pu == pv) return false;  // 已連通
        if (Size[pu] < Size[pv]) {   // 合併時小集合掛在大集合下
            int temp = pu;
            pu = pv;
            pv = temp;
        }
        Size[pu] += Size[pv];
        Parent[pv] = pu;
        return true;
    }
}

public class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        DSU dsu = new DSU(n);
        List<int[]> edges = new ArrayList<>();

        // 建立所有點之間的邊與距離（曼哈頓距離）
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) +
                           Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[] {dist, i, j}); // {距離, 點i, 點j}
            }
        }

        // 按照距離由小到大排序 (距離短的先加入)
        edges.sort((a, b) -> Integer.compare(a[0], b[0]));

        int res = 0;

        // 套用 Kruskal 演算法挑邊
        for (int[] edge : edges) {
            if (dsu.union(edge[1], edge[2])) {
                res += edge[0]; // 若成功連接（沒有形成環），加上這條邊的距離
            }
        }

        return res; // 最小總成本
    }
}