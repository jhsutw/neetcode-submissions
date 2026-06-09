/**
 * 使用並查集 (Disjoint Set Union) 計算二維網格中最大島嶼面積 (Max Area of Island)。
 * 每個陸地格 (值為 1) 對應一個 DSU 節點，編號 = r * COLS + c。
 * 將相鄰的陸地格合併到同一集合，最後遍歷所有格子，取其所在集合的最大大小。
 */
class DSU {
    private int[] Parent;  // Parent[i]：節點 i 的父節點
    private int[] Size;    // Size[i]：以 i 為根的集合大小

    /**
     * 初始化 DSU，節點編號從 0 到 n。（n = ROWS*COLS）
     * 每個節點起初自成一個集合，集合大小為 1。
     */
    public DSU(int n) {
        Parent = new int[n + 1];
        Size   = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            Parent[i] = i;
            Size[i]   = 1;
        }
    }

    /**
     * 查找節點 x 的根節點，並執行路徑壓縮 (Path Compression)。
     */
    public int find(int x) {
        if (Parent[x] != x) { // x的parent不是自己
            Parent[x] = find(Parent[x]);  // 遞迴壓縮路徑
        }
        return Parent[x];
    }

    /**
     * 將節點 x 和 y 所在集合合併，
     * 採用「按集合大小」(Union by Size) 策略：小集合掛到大集合根下。
     * 若原本就在同一集合，回傳 false；否則合併後回傳 true。
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return false;  // 已經在同一集合
        }
        // 集合大小較大的作為合併後的根
        if (Size[rootX] >= Size[rootY]) {
            Parent[rootY] = rootX;
            Size[rootX] += Size[rootY];
        } else {
            Parent[rootX] = rootY;
            Size[rootY] += Size[rootX];
        }
        return true;
    }

    /**
     * 回傳節點 x 所在集合的大小 (即該島嶼面積)。
     */
    public int getSize(int x) {
        return Size[find(x)];
    }
}

public class Solution {
    /**
     * 主函式：遍歷所有格子，若為陸地 (1)，嘗試與上下左右相鄰陸地合併，
     * 並即時計算所在集合大小以更新最大島嶼面積。
     */
    public int maxAreaOfIsland(int[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;
        DSU dsu = new DSU(ROWS * COLS);  // 建立 DSU 節點數 = ROWS*COLS

        // 四個移動方向：下、上、右、左
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int maxArea = 0;                // 追蹤最大島嶼面積

        // 遍歷所有格子
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == 1) {   // 遇到陸地
                    // 嘗試與相鄰方向的陸地進行 union
                    for (int[] d : directions) {
                        int nr = r + d[0], nc = c + d[1];
                        if (nr >= 0 && nr < ROWS &&
                            nc >= 0 && nc < COLS &&
                            grid[nr][nc] == 1) {
                            dsu.union(r * COLS + c, nr * COLS + nc); // 合併當前以及鄰近陸地
                        }
                    }
                    // 即時計算並更新最大集合大小
                    int curArea = dsu.getSize(r * COLS + c);
                    if (curArea > maxArea) {
                        maxArea = curArea;
                    }
                }
            }
        }

        return maxArea;
    }
}