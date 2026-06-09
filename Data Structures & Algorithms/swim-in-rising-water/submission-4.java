// 5. Kruskal's Algorithm
// 並查集（Disjoint Set Union）資料結構，用於維護哪些格子是連通的
class DSU {
    private int[] Parent; // 每個節點的父節點（代表元）
    private int[] Size;   // 每個集合的大小，用於 union 時優化

    public DSU(int n) {
        Parent = new int[n + 1];
        Size = new int[n + 1];
        for (int i = 0; i <= n; i++) Parent[i] = i; // 初始化，每個節點都是自己的代表元
        Arrays.fill(Size, 1); // 每個集合大小一開始都是 1
    }

    // 查找某個節點的代表元，並進行路徑壓縮
    public int find(int node) {
        if (Parent[node] != node)
            Parent[node] = find(Parent[node]);
        return Parent[node];
    }

    // 合併兩個集合，若本來就在同一集合回傳 false，否則合併並回傳 true
    public boolean union(int u, int v) {
        int pu = find(u), pv = find(v);
        if (pu == pv) return false; // 已經連通
        // 小的集合合併到大的集合上（Union by Size）
        if (Size[pu] < Size[pv]) {
            int temp = pu;
            pu = pv;
            pv = temp;
        }
        Size[pu] += Size[pv]; // 更新集合大小
        Parent[pv] = pu;      // pv 合併進 pu
        return true;
    }

    // 檢查兩個節點是否在同一集合（即是否連通）
    public boolean connected(int u, int v) {
        return find(u) == find(v);
    }
}

public class Solution {
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        DSU dsu = new DSU(N * N); // 建立並查集，將每個格子視為節點

        // 將所有格子記錄為 [高度, row, col]，稍後按高度排序模擬水位上升
        List<int[]> positions = new ArrayList<>();
        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++)
                positions.add(new int[]{grid[r][c], r, c});

        // 按照高度升序排序，模擬水位由低到高上升
        positions.sort(Comparator.comparingInt(a -> a[0]));

        // 定義上下左右四個方向
        int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        // 開始灌水模擬：每次將當前格子設為可通行，並嘗試與鄰居格子 union
        for (int[] pos : positions) {
            int t = pos[0]; // 當前時間 / 水位（等於這個格子的高度）
            int r = pos[1], c = pos[2]; // 當前格子的位置

            // 檢查四個方向的鄰居是否已經在水下（可通行），若是就 union
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N &&
                    grid[nr][nc] <= t) {
                    dsu.union(r * N + c, nr * N + nc);
                }
            }

            // 每次 union 完檢查是否起點(0)與終點(N*N)連通，若是回傳目前時間 t
            if (dsu.connected(0, N * N - 1)) return t;
        }

        // 理論上永遠不會到這裡，回傳最大值保險
        return N * N;
    }
}