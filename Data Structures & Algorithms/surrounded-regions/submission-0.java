// 1. Depth First Search
public class Solution {
    private int ROWS, COLS;  // 紀錄棋盤行數與列數

    /**
     * 主方法：將所有被 'X' 包圍的 'O' 反轉為 'X'
     * 步驟：
     * 1. 從邊界出發，把能連通到邊界的 'O' 標記為暫存符號 'T'（因為所有能通向邊界的 'O' 都已標記為 'T'，故剩下未標記的 'O' 必然無法聯通邊界、被 'X' 完全包圍。）
     * 2. 全盤掃描，未被標記的 'O' 即為被包圍 → 反轉為 'X'
     * 3. 將所有暫存符號 'T' 還原回 'O'
     */
    public void solve(char[][] board) {
        ROWS = board.length;
        COLS = board[0].length;

        // 1. 處理左右邊界
        for (int r = 0; r < ROWS; r++) {
            if (board[r][0] == 'O') {
                capture(board, r, 0);           // 左邊界 'O' 開始 DFS 標記
            }
            if (board[r][COLS - 1] == 'O') {
                capture(board, r, COLS - 1);    // 右邊界 'O' 開始 DFS 標記
            }
        }

        // 2. 處理上下邊界
        for (int c = 0; c < COLS; c++) {
            if (board[0][c] == 'O') {
                capture(board, 0, c);           // 上邊界 'O' 開始 DFS 標記
            }
            if (board[ROWS - 1][c] == 'O') {
                capture(board, ROWS - 1, c);    // 下邊界 'O' 開始 DFS 標記
            }
        }

        // 3. 最終遍歷整個棋盤，翻轉與還原
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 'O') {
                    // 未被標記的 'O' → 完全被 'X' 包圍，翻轉為 'X'
                    board[r][c] = 'X';
                } else if (board[r][c] == 'T') {
                    // 暫存符號 'T' → 邊界可達，還原回 'O'
                    board[r][c] = 'O';
                }
            }
        }
    }

    /**
     * DFS 標記函式：將與 (r,c) 連通的所有 'O' 標記為 'T'
     * 只遍歷高度與形狀為連通分量的 'O'
     */
    private void capture(char[][] board, int r, int c) {
        // 越界或當前格子非 'O' 則停止
        if (r < 0 || c < 0 || r >= ROWS || 
            c >= COLS || board[r][c] != 'O') {
            return;
        }
        // 標記為 'T'，表示這個 'O' 不應該被翻轉
        board[r][c] = 'T';
        // 四個方向遞迴擴散
        capture(board, r + 1, c);
        capture(board, r - 1, c);
        capture(board, r, c + 1);
        capture(board, r, c - 1);
    }
}