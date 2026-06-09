public class Solution {
    /**
     * 主函式：返回 N 皇后問題的所有擺放方案
     * @param n 棋盤大小 (n × n)
     * @return  每種方案對應的字串列表表示
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();       // 結果集
        // 初始化空棋盤，用 '.' 表示空位
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        // 從第 0 行開始回溯擺放皇后
        backtrack(0, board, res);
        return res;
    }

    /**
     * 回溯函式：試圖在第 r 行放一個皇后
     * @param r     當前行索引
     * @param board 棋盤狀態
     * @param res   儲存所有合法方案
     */
    private void backtrack(int r, char[][] board, List<List<String>> res) {
        int n = board.length;
        // Base case：已經成功在每一行放置皇后（如果中間有任何case不成立，就不會擺到最後一row）
        if (r == n) {
            // 把 char[][] 轉成 List<String> 後加入結果
            List<String> copy = new ArrayList<>();
            for (char[] row : board) {
                copy.add(new String(row));
            }
            res.add(copy);
            return;
        }
        // 在當前行的每一列嘗試放置皇后
        for (int c = 0; c < n; c++) {
            // 如果 (r,c) 位置安全 (不會被其他皇后攻擊)
            if (isSafe(r, c, board)) {
                board[r][c] = 'Q';           // 放置皇后
                backtrack(r + 1, board, res); // 遞迴處理下一行
                board[r][c] = '.';           // 回溯：撤銷放置
            }
        }
    }

    /**
     * 檢查 (r,c) 放置皇后是否安全
     * 只需向上檢查同一列、左下斜、左上斜 (因為擺Q時是col by col擺，不會有同col出現兩個Q的情況，並且右邊col的後擺，所以不用管右上、右下)
     */
    private boolean isSafe(int r, int c, char[][] board) {
        int n = board.length;
        // 檢查同一列上方是否有皇后
        for (int i = 0; i < r; i++) {
            if (board[i][c] == 'Q') return false;
        }
        // 檢查左下斜對角線
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        // 檢查左上斜對角線
        for (int i = r - 1, j = c + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // 三條路徑都沒有皇后，可以安全放置
        return true;
    }
}

/*
🧪 範例演示 (n = 4)：

初始空棋盤：
. . . .
. . . .
. . . .
. . . .

1) r=0，嘗試 c=0..3
   放 Q 在 (0,1)，繼續 r=1

2) r=1，嘗試 c=0：被 (0,1) 斜線攻擊 → skip
         c=1：同列被攻擊 → skip
         c=2：安全，放 Q，r=2

3) r=2，嘗試 c=0：安全，放 Q，r=3
   r=3，... 最終發現不通 → 回溯

   繼續 r=2 試 c=1..3，找到另一合法擺放 → 收集一套完整方案

4) r=0 試 c=2..3，重複上述回溯流程 → 收集第二套、第三套 ...

最終兩種合法方案 (4×4)：
[ ".Q..",  // 方案1
  "...Q",
  "Q...",
  "..Q." ]

[ "..Q.",  // 方案2
  "Q...",
  "...Q",
  ".Q.." ]
*/