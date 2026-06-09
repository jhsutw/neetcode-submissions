// 並查集 (Disjoint Set Union) 實作
class DSU {
    int[] Parent, Size;  // Parent[i]：節點 i 的父節點；Size[i]：以 i 為根的集合大小

    // 初始化並查集，節點編號 0…n（在board中的格數編號 0 ~ ROW*COL-1格 + Boundary虛擬節點）
    public DSU(int n) {
        Parent = new int[n + 1];
        Size   = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            Parent[i] = i;   // 每個節點自成一集合
            Size[i]   = 1;   // 初始大小為 1
        }
    }

    // 查找根節點、並進行路徑壓縮
    public int find(int node) {
        if (Parent[node] != node) {
            Parent[node] = find(Parent[node]);
        }
        return Parent[node];
    }

    // 合併 u, v 所在集合，按集合大小掛載；若已同根則返回 false
    public boolean union(int u, int v) {
        int pu = find(u), pv = find(v);
        if (pu == pv) return false;
        if (Size[pu] >= Size[pv]) {
            Size[pu] += Size[pv];
            Parent[pv] = pu;
        } else {
            Size[pv] += Size[pu];
            Parent[pu] = pv;
        }
        return true;
    }

    // 判斷 u, v 是否在同一集合
    public boolean connected(int u, int v) {
        return find(u) == find(v);
    }
}

public class Solution {
    /**
     * 使用 DSU 解 Surrounded Regions：
     * - 將所有邊界上的 'O' 與一個虛擬節點（ROWS*COLS）合併，
     *   代表它們能夠「連通邊界」不應被翻轉。
     * - 對每個內部 'O'，若與相鄰的 'O' 同為 O，則合併到同一集合。
     * - 最後遍歷所有格子，若與虛擬節點不連通 → 被完全包圍 → 翻成 'X'。
     */
    public void solve(char[][] board) {
        int ROWS = board.length, COLS = board[0].length;
        // DSU 節點數為 ROWS*COLS 個格子 + 1 個虛擬邊界節點
        DSU dsu = new DSU(ROWS * COLS + 1);
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int BOUNDARY = ROWS * COLS;  // 虛擬邊界節點編號

        // 1) 遍歷所有格子，對 'O' 進行合併操作
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] != 'O') continue; // 若 board[r][c] == 'X' 跳過
                int idx = r * COLS + c; // 第 r row 的 第 c col
                // 邊界 'O' 直接與虛擬節點 union
                if (r == 0 || c == 0 || r == ROWS - 1 || c == COLS - 1) {
                    dsu.union(BOUNDARY, idx);
                } else {
                    // 內部 'O' 則與四周相鄰的 'O' 合併
                    for (int[] d : directions) {
                        int nr = r + d[0], nc = c + d[1];
                        if (board[nr][nc] == 'O') {
                            dsu.union(idx, nr * COLS + nc);
                        }
                    }
                }
            }
        }

        // 2) 第二次遍歷：所有不與邊界節點連通的 'O' → 翻成 'X'
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (!dsu.connected(BOUNDARY, r * COLS + c)) {
                    board[r][c] = 'X';
                }
            }
        }
    }
}