public class Solution {
    // col[c] 表示第 c 列是否已有皇后
    boolean[] col;
    // posDiag[r + c] 表示 "/" 方向的正斜線是否已有皇后
    boolean[] posDiag;
    // negDiag[r - c + n] 表示 "\" 方向的負斜線是否已有皇后，加上 n 確保索引非負
    boolean[] negDiag;
    // 儲存所有合法的棋盤方案
    List<List<String>> res;
    // 當前棋盤狀態，用 '.' 表示空格，'Q' 表示皇后
    char[][] board;

    public List<List<String>> solveNQueens(int n) {
        // 初始化標記陣列
        col = new boolean[n];
        posDiag = new boolean[2 * n];       // r+c 最大到 2n-2
        negDiag = new boolean[2 * n];       // r-c+n 範圍 [0, 2n-2]
        res = new ArrayList<>();
        // 建立 n×n 棋盤，填入 '.'
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        // 從第 0 行開始回溯
        backtrack(0, n);
        return res;
    }

    /**
     * 回溯函式：嘗試在第 r 行放置皇后
     * @param r 當前行索引
     * @param n 棋盤大小
     */
    private void backtrack(int r, int n) {
        // 如果已放置到第 n 行，代表放置完畢，將當前 board 轉為 List<String> 加入結果
        if (r == n) {
            List<String> copy = new ArrayList<>();
            for (char[] row : board) {
                copy.add(new String(row)); // 要複製row
            }
            res.add(copy);
            return;
        }
        // 在第 r 行的每一列嘗試放置皇后
        for (int c = 0; c < n; c++) {
            // 計算兩條對角線的索引
            int pd = r + c;       // 正斜線索引
            int nd = r - c + n;   // 負斜線索引，偏移 n 保持非負
            // 如果該列或對角線已有皇后，則不能放（其中一個方向有被標記為true）
            if (col[c] || posDiag[pd] || negDiag[nd]) {
                continue;
            }
            // 標記放置皇后
            col[c] = true;
            posDiag[pd] = true;
            negDiag[nd] = true;
            board[r][c] = 'Q';

            // 遞迴處理下一行
            backtrack(r + 1, n);

            // 回溯：撤銷標記與皇后
            col[c] = false;
            posDiag[pd] = false;
            negDiag[nd] = false;
            board[r][c] = '.';
        }
    }
}