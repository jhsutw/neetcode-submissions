public class Solution {
    private int ROWS, COLS;
    // 記錄當前 DFS 路徑上哪些格子已經被訪問，避免重複使用同一格
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        ROWS = board.length;
        COLS = board[0].length;
        visited = new boolean[ROWS][COLS];  // 初始化所有格子為未訪問

        // 嘗試以每個格子作為起點
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;  // 只要有一條路徑能找到整個 word 就回傳 true
                }
            }
        }
        return false;  // 所有起點都嘗試過仍失敗，回傳 false
    }

    /**
     * 深度優先搜尋 (DFS)
     * @param board 字母矩陣
     * @param word  要匹配的單詞
     * @param r     當前行索引
     * @param c     當前列索引
     * @param i     已匹配到 word 的第 i 個字符
     * @return      是否能從 (r,c) 繼續匹配到 word[i..end]
     */
    private boolean dfs(char[][] board, String word, int r, int c, int i) {
        // 如果已匹配完整個單詞，返回 true
        if (i == word.length()) {
            return true;
        }
        // 越界檢查、字符是否相符、是否已訪問
        if (r < 0 || c < 0 || r >= ROWS || c >= COLS ||
            board[r][c] != word.charAt(i) || visited[r][c]) {
            return false;
        }

        // 標記當前格子為已訪問
        visited[r][c] = true;
        // 向四個方向繼續匹配下一個字符
        boolean found = dfs(board, word, r + 1, c, i + 1) ||
                        dfs(board, word, r - 1, c, i + 1) ||
                        dfs(board, word, r, c + 1, i + 1) ||
                        dfs(board, word, r, c - 1, i + 1);
        // 回溯：解除標記，讓後續路徑可以再次使用這個格子
        visited[r][c] = false;

        return found;
    }
}

/*
🧪 範例測試：

board = [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
];
word = "SEE"

呼叫 exist(board, "SEE")：

1. 起點 (0,0)='A' ≠ 'S' → fail
   …
2. 起點 (2,0)='A' → fail
3. 起點 (2,1)='D' → fail
4. 起點 (2,2)='E' → match word[0]='S'? fail
5. 起點 (1,0)='S' → match word[0]='S' → visited[1][0]=true
   ▼ i=1, word[1]='E'
   - 下 (2,0)='A'≠'E'
   - 上 (0,0)='A'≠'E'
   - 右 (1,1)='F'≠'E'
   - 左 越界
   → dead end, 回溯 visited[1][0]=false
6. 起點 (1,3)='S' → match, visited[1][3]=true
   ▼ i=1
   - 下 (2,3)='E' match → visited[2][3]=true
     ▼ i=2, word[2]='E'
     - 四方向嘗試會在 (2,2) 或 (1,3) 找到下一個 'E'，最終成功 → return true

exist 回傳：true
*/