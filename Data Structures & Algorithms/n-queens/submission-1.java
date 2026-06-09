public class Solution {
    // 已放置皇后的列索引集合
    Set<Integer> col = new HashSet<>();
    // 已放置皇后的「正斜線」集合 (r + c 相同代表同一條 / 斜線)
    Set<Integer> posDiag = new HashSet<>();
    // 已放置皇后的「負斜線」集合 (r - c 相同代表同一條 \ 斜線)
    Set<Integer> negDiag = new HashSet<>();
    // 儲存所有合法方案
    List<List<String>> res = new ArrayList<>();
    
    public List<List<String>> solveNQueens(int n) {
        // 初始化 n×n 棋盤，'.' 代表空位
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.'); // 把所有row中的值都填上'.'
        }
        // 從第 0 行開始回溯
        backtrack(0, n, board);
        return res;
    }

    /**
     * 回溯函式：嘗試在第 r 行放一個皇后
     * @param r     當前行索引
     * @param n     棋盤大小
     * @param board 當前棋盤狀態
     */
    private void backtrack(int r, int n, char[][] board) {
        // 如果已成功放置完第 n-1 行，r==n 代表找到一組解
        if (r == n) {
            // 將 board 轉為 List<String> 並加入結果
            List<String> copy = new ArrayList<>();
            for (char[] row : board) {
                copy.add(new String(row));
            }
            res.add(copy);
            return;
        }

        // 嘗試在第 r 行的每一列 c 放置皇后
        for (int c = 0; c < n; c++) {
            // 如果該列或對應斜線已被佔用，就跳過
            if (col.contains(c) // 同個col有重複值
                || posDiag.contains(r + c) // 正對角線有重複值
                || negDiag.contains(r - c)) { // 負對角線有重複值
                continue; // 跳過
            }

            // 標記第 r 行 c 列放置皇后
            col.add(c);
            posDiag.add(r + c);
            negDiag.add(r - c);
            board[r][c] = 'Q';

            // 繼續處理下一行
            backtrack(r + 1, n, board);

            // 回溯：撤銷標記，恢復空位（當這條路走不下去換別條時）
            col.remove(c);
            posDiag.remove(r + c);
            negDiag.remove(r - c);
            board[r][c] = '.';
        }
    }
}

/*
🧪 範例演示 (n = 4)：

初始：col={}, posDiag={}, negDiag={}, board 全為 '.'

r = 0：
  c = 0 → 沒有衝突 → 放 Q(0,0)，col={0}, posDiag={0+0=0}, negDiag={0-0=0}
    → 進入 r=1
      r = 1：
        c = 0 → col.contains(0) 跳過
        c = 1 → posDiag.contains(1+1=2)?否; negDiag.contains(1-1=0)?是 → 跳過
        c = 2 → 無衝突 → 放 Q(1,2)，col={0,2}, posDiag={0,3}, negDiag={0,-1}
          → 進入 r=2
            r = 2：
              c = 0 → col.contains(0)跳
              c = 1 → posDiag.contains(2+1=3)?是跳
              c = 2 → col.contains(2)跳
              c = 3 → negDiag.contains(2-3=-1)?是跳
            → 沒地方放，回溯到 r=1，撤銷 Q(1,2)
      回溯到 r=0，撤銷 Q(0,0)

  c = 1 → 無衝突 → 放 Q(0,1)，col={1}, posDiag={1}, negDiag={-1}
    → r=1…
    最終會找到兩種合法配置：
      [".Q..","...Q","Q...","..Q."]
      ["..Q.","Q...","...Q",".Q.."]
*/