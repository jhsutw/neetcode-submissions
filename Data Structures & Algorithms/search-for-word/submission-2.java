// 1. Backtracking (Hash Set)
public class Solution {
    private int ROWS, COLS;
    // 用來記錄當前 DFS 路徑上的格子，防止重複訪問
    private Set<Pair<Integer, Integer>> path = new HashSet<>();

    public boolean exist(char[][] board, String word) {
        ROWS = board.length;
        COLS = board[0].length;

        // 遍歷每一個起點 (r, c)
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                // 如果從 (r,c) 能找到整個 word，就回傳 true
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        // 所有起點都試過，仍找不到
        return false;
    }

    /**
     * @param board 字母矩陣
     * @param word  待搜尋的單詞
     * @param r     當前行
     * @param c     當前列
     * @param i     已匹配到 word 的第 i 個字元
     * @return      是否能從 (r,c) 位置繼續匹配到 word[i..end]
     */
    private boolean dfs(char[][] board, String word, int r, int c, int i) {
        // 如果已匹配到 word.length()，代表整個單詞都找到了
        if (i == word.length()) {
            return true;
        }
        // 邊界檢查、字元檢查、以及是否已經走過該格
        if (r < 0 || c < 0 || r >= ROWS || c >= COLS ||
            board[r][c] != word.charAt(i) ||
            path.contains(new Pair<>(r, c))) {
            return false;
        }

        // 標記當前格子為「已走過」
        path.add(new Pair<>(r, c));
        // 從當前格子四個方向繼續 DFS
        boolean found = dfs(board, word, r + 1, c, i + 1) ||
                        dfs(board, word, r - 1, c, i + 1) ||
                        dfs(board, word, r, c + 1, i + 1) ||
                        dfs(board, word, r, c - 1, i + 1);
        // 回溯：撤銷「已走過」標記
        path.remove(new Pair<>(r, c));

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
word = "ABCCED"

呼叫 exist(board, "ABCCED")：

1. 起點 (0,0) = 'A' 匹配 word[0]='A'
2. path ⊃ (0,0) → i=1
3. 向下 (1,0)='S'≠'B' → fail
   向上 out of bounds → fail
   向右 (0,1)='B' matches → path⊃(0,1) → i=2
   …
   最終能從 (0,0)->(0,1)->(0,2)->(1,2)->(2,2)->(2,1) 完整匹配 "ABCCED"，返回 true。

exist 最終結果：true
*/
