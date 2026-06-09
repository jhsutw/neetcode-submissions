// 2. Breadth First Search
public class Solution {
    private int ROWS, COLS;
    // 四個方向：下、上、右、左
    private int[][] directions = {
        { 1,  0},
        {-1,  0},
        { 0,  1},
        { 0, -1}
    };

    public void solve(char[][] board) {
        ROWS = board.length;
        COLS = board[0].length;

        // 1) 標記所有可連通到邊界的 'O'
        capture(board);

        // 2) 全盤掃描：未被標記的 'O' → 翻轉為 'X'；標記 'T' → 還原回 'O'
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == 'T') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    /**
     * 將所有能從邊界觸及的 'O' 標記為 'T'
     * 使用多源 BFS：先將所有邊界上的 'O' 入隊，再往內擴散
     */
    private void capture(char[][] board) {
        Queue<int[]> q = new LinkedList<>();
        // 1. 收集邊界上的 'O'
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                // 注意括號：先判斷是否在邊界，再檢查是否為 'O'
                if ((r == 0 || r == ROWS - 1 || c == 0 || c == COLS - 1)
                    && board[r][c] == 'O') {
                    q.offer(new int[]{r, c});
                }
            }
        }
        // 2. BFS 標記所有相連的 'O'
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];
            // 如果目前仍為 'O'，則標記為 'T'
            if (board[r][c] == 'O') {
                board[r][c] = 'T';
                // 向四個方向拓展
                for (int[] d : directions) {
                    int nr = r + d[0], nc = c + d[1];
                    // 邊界檢查，且鄰居必須是 'O' 才能入隊
                    if (nr >= 0 && nr < ROWS
                        && nc >= 0 && nc < COLS
                        && board[nr][nc] == 'O') {
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }
}

/*
// 初始棋盤：
// X X X X
// X O O X
// X O O X
// X X X O

// 1) 收集邊界上的所有 'O'：
//    - 遍歷四條邊，只有 (3,3) 是邊界上的 'O'
//    → 隊列初始為 [(3,3)]

// 2) 開始多源 BFS 標記：
//    - 從隊列取出 (3,3)，發現 board[3][3]=='O'
//      → 標記為 'T'
//    - 嘗試向四個方向擴散，但 (4,3)、(2,3)、(3,4)、(3,2) 都不是 'O'
//      → 無新節點入隊
//    - 隊列為空，BFS 結束

// 標記後中間狀態：
// X X X X
// X O O X
// X O O X
// X X X T

// 3) 全盤掃描翻轉與還原：
//    - 所有仍為 'O' 的格子（1,1),(1,2),(2,1),(2,2) → 完全被 'X' 包圍
//      → 轉為 'X'
//    - 任何 'T' → 還原為 'O'

// 最終棋盤結果：
// X X X X
// X X X X
// X X X X
// X X X O
*/