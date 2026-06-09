// 1. Backtracking
public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        int ROWS = board.length, COLS = board[0].length;    // 取得行列
        List<String> res = new ArrayList<>();               // 結果列表

        // 對每一個要搜尋的單詞做迴圈
        for (String word : words) {
            boolean found = false;                          
            // 掃描整個棋盤，只要還沒找到，就繼續
            for (int r = 0; r < ROWS && !found; r++) {
                for (int c = 0; c < COLS; c++) {
                    // 只有在首字母吻合的格子才啟動回溯
                    if (board[r][c] != word.charAt(0)) continue;
                    if (backtrack(board, r, c, word, 0)) {
                        res.add(word);                      // 找到就加入結果
                        found = true;                      // 標記已找到，跳出雙層迴圈
                        break;
                    }
                }
            }
        }
        return res;
    }

    private boolean backtrack(char[][] board, int r, int c, String word, int index) {
        // 如果已經匹配完所有字母
        if (index == word.length()) return true;

        // 邊界檢查或字母不符 → 回傳 false
        if (r < 0 || c < 0 || r >= board.length ||
            c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        char temp = board[r][c];         // 暫存當前字母 (等等回溯才可以加回來)
        board[r][c] = '*';               // 標記為已訪問，避免重複走回來

        // 嘗試上下左右四個方向繼續匹配下一個字母
        boolean matched = backtrack(board, r + 1, c, word, index + 1) ||
                          backtrack(board, r - 1, c, word, index + 1) ||
                          backtrack(board, r, c + 1, word, index + 1) ||
                          backtrack(board, r, c - 1, word, index + 1);

        board[r][c] = temp;              // 回溯：還原格子
        return matched;
    }
}

// 以下為執行流程示例：
// char[][] board = {
//   {'o','a','a','n'},
//   {'e','t','a','e'},
//   {'i','h','k','r'},
//   {'i','f','l','v'}
// };
// String[] words = { "oath", "pea", "eat", "rain" };
//
// 1. 搜尋 "oath":
//    └ 在 (0,0) 找到 'o' → backtrack index=0
//       → 向右 (0,1) 匹配 'a' → 向下 (1,1) 匹配 't'
//         → 向下 (2,1) 匹配 'h' → 向右 (2,2) 嘗試 'o' 不符 → 回溯
//         → 向左/上/下 都不符，回溯至 (1,1)
//       → 向右/上/左 不符，回溯至 (0,0)
//       → 向下 (1,0) 匹配 'a' → … 最終能走出 "oath" → 返回 true，加 "oath"
//
// 2. 搜尋 "pea":
//    └ 全盤掃描每個 'p' 都找不到，跳過
//
// 3. 搜尋 "eat":
//    └ 在 (1,1) 找到 't' 但首字母不符
//    └ 在 (1,2) 找到 'a' 也不符
//    └ 在 (1,0) 找到 'e' → backtrack 能依序找到 "eat" → 加 "eat"
//
// 4. 搜尋 "rain":
//    └ 尋不到 'r' 開頭的合法路徑，跳過
//
// 最終 return ["oath", "eat"]