// 3. Backtracking (Optimal)
public class Solution {
    private int ROWS, COLS;

    /**
     * 主函式：檢查 board 中是否存在連續相鄰 (上下左右) 拼出 word 的路徑
     */
    public boolean exist(char[][] board, String word) {
        ROWS = board.length;
        COLS = board[0].length;

        // 以每個格子作為起點，嘗試 DFS 配對整個 word
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 深度優先搜尋 (DFS)
     * @param board 字母矩陣，走過的格子會暫時標為 '#' 防止重訪
     * @param word  要匹配的單詞
     * @param r     當前行索引
     * @param c     當前列索引
     * @param i     已匹配到 word 的第 i 個字符
     * @return      是否能從 (r,c) 繼續匹配到 word[i..end]
     */
    private boolean dfs(char[][] board, String word, int r, int c, int i) {
        // 如果已成功匹配所有字符
        if (i == word.length()) {
            return true;
        }
        // 越界檢查、字符是否吻合，或該格已被標記為 '#'（表示已走過）
        if (r < 0 || c < 0 || r >= ROWS || c >= COLS ||
            board[r][c] != word.charAt(i) || board[r][c] == '#') {
            return false;
        }

        // 暫時將當前格子標記為 '#'，避免後續路徑重訪（用標記原的board的方式，不用再創建一個boolean）
        board[r][c] = '#';

        // 向四個方向 (下、上、右、左) 繼續匹配下一個字符
        boolean found = dfs(board, word, r + 1, c, i + 1) ||
                        dfs(board, word, r - 1, c, i + 1) ||
                        dfs(board, word, r, c + 1, i + 1) ||
                        dfs(board, word, r, c - 1, i + 1);

        // 回溯：將 '#' 恢復為原字符
        board[r][c] = word.charAt(i);
        return found;
    }
}

/*
🧪 範例演示 (board, word = "SEE")：

board = [ 
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
];

exist(...) 走訪起點 (1,3) = 'S'：
  i=0 match 'S' → 標記為 '#'
    嘗試下 (2,3)='E' match i=1 → 標記
      嘗試下 (3,3) 越界 → 回傳 false
      嘗試上 (1,3)='#' → false
      嘗試右 越界 → false
      嘗試左 (2,2)='E' match i=2 → 標記
        i=3 == word.length → return true
      恢復 (2,2)、(2,3)、(1,3)
  整體回傳 true
*/