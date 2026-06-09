// 3. Disjoint Set Union
/**
 * 使用並查集 (Union-Find) 計算二維網格中的島嶼數量。
 * - 每個陸地格 ('1') 視為一個節點，索引由 r*COLS + c 計算。
 * - 初始時每塊陸地獨立成為一個島嶼，遍歷時若與鄰近陸地合併成功，島嶼總數減一。
 */
public class Solution {
    public int numIslands(char[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;
        DSU dsu = new DSU(ROWS * COLS);    // 建立 DSU 結構，節點數為 ROWS*COLS

        // 四個移動方向：下、上、右、左
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        int islands = 0;                   // 島嶌計數初始化

        // 遍歷整個網格
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == '1') {   // 發現未訪問的陸地
                    islands++;             // 暫視為一座新島嶼
                    // 嘗試與上下左右四個方向的陸地合併
                    for (int[] d : directions) {
                        int nr = r + d[0], nc = c + d[1];
                        if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS
                            && grid[nr][nc] == '1') {
                            // 若合併成功，代表原本屬於不同島嶼 → 減少島嶼計數
                            if (dsu.union(r * COLS + c, nr * COLS + nc)) {
                                islands--;
                            }
                        }
                    }
                }
            }
        }
        return islands;                   // 回傳最終島嶼數量
    }
}

class DSU {
    private int[] parent;  // parent[i]：節點 i 的父節點
    private int[] size;    // size[i]：以 i 為根節點的集合大小

    /**
     * 初始化並查集，節點編號從 0 到 n。
     * 每個節點起初都是自己的父節點，集合大小為 1。
     */
    public DSU(int n) {
        parent = new int[n + 1];
        size   = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i]   = 1;
        }
    }

    /**
     * 查找節點 x 的根節點，同時進行路徑壓縮，
     * 使樹高度降低，加速後續查找。
     */
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // 路徑壓縮
        }
        return parent[x];
    }

    /**
     * 將 x 和 y 兩個節點所在的集合合併，
     * 採用「按集合大小」合併方式：小集合掛到大集合根下。
     * 回傳是否成功合併（原本不在同一集合則回傳 true）。
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return false;  // 已屬於同一集合，不需合併
        }
        // 小集合併入大集合
        if (size[rootX] >= size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        } else {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }
        return true;
    }
}

// 以下為執行流程示例：
// grid = [
//   ['1','1','0','0','0'],
//   ['1','1','0','0','0'],
//   ['0','0','1','0','0'],
//   ['0','0','0','1','1']
// ]
//
// 1. 初始化 DSU，共 16 個節點，各自獨立，islands=0。
// 2. 掃描 (0,0)='1' → islands=1；與 (1,0) 合併成功 → islands=0；與 (0,1) 合併成功 → islands=-1。
//    （-1 只是中間計數，最終結果仍正確）
// 3. 掃描 (0,1),(1,0),(1,1) 都是 '1'，各自 +1，並嘗試合併已在同集合者皆失敗 → islands 回補到 2。
// 4. 掃描 (2,2)='1' → islands=3；無鄰近陸地可合併。
// 5. 掃描 (3,3)='1' → islands=4；與 (3,4) 合併成功 → islands=3。
//  最終回傳 3。```