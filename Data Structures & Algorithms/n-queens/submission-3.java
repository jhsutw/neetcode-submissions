public class Solution {
    int col = 0, posDiag = 0, negDiag = 0;  
    // col     用二進位的第 c 位表示第 c 欄是否已有皇后
    // posDiag 用二進位的第 (r+c) 位表示「\」方向的斜線是否被佔用
    // negDiag 用二進位的第 (r-c+n) 位表示「/」方向的斜線是否被佔用
    List<List<String>> res;  
    char[][] board;  

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();               // 儲存所有合法棋盤配置
        board = new char[n][n];                
        for (int i = 0; i < n; i++) {         
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';            // 用 '.' 表示空格
            }
        }
        backtrack(0, n);                      // 從第 0 列開始遞迴
        return res;
    }

    private void backtrack(int r, int n) {
        if (r == n) {                         // 已經成功放置完 n 列
            List<String> copy = new ArrayList<>();
            for (char[] row : board) {
                copy.add(new String(row));   // 將 char[] 轉成 String
            }
            res.add(copy);                    // 收集一個解
            return;
        }
        for (int c = 0; c < n; c++) {         // 嘗試在第 r 列的第 c 欄放皇后
            // 檢查同欄、同\斜線、同/斜線是否已有皇后
            if ((col     & (1 << c))         > 0  
             || (posDiag & (1 << (r + c)))  > 0
             || (negDiag & (1 << (r - c + n))) > 0) {
                continue;                    // 有衝突就跳過
            }
            // 標記放置皇后後的狀態
            // ^ 是 bitwise XOR（異或）：相同位元結果為 0，不同位元結果為 1
            // | 是 bitwise OR（或）：任一位為 1 結果就為 1，否則為 0
            col     ^= (1 << c); // a ^= b 等同於 a = a ^ b 對應位相同則結果為 0，不同則為 1。        
            posDiag ^= (1 << (r + c));        
            negDiag ^= (1 << (r - c + n));    
            board[r][c] = 'Q';                // 實際放上皇后

            backtrack(r + 1, n);              // 處理下一列

            // 回溯：還原成放置前的狀態
            col     ^= (1 << c);
            posDiag ^= (1 << (r + c));
            negDiag ^= (1 << (r - c + n));
            board[r][c] = '.';                // 清空皇后
        }
    }
}

/*
這裡之所以用 ^=（XOR 並賦值）而不是 |=，關鍵在於「放皇后」和「回溯時移除皇后」這兩個步驟都可以用同一條語句來完成──因為 XOR 是「切換」位元的操作：

放皇后：col ^= (1 << c)
把第 c 欄的那一位從 0 → 1。

回溯移除皇后：同樣再執行一次 col ^= (1 << c)
把那一位從 1 → 0。

如此一來，只要在進遞迴前後各呼叫一次 ^=，就完成「標記」和「還原」兩個動作，程式碼更簡潔。

如果改用 |=（OR 並賦值）：
col |= (1 << c);   // 放皇后：把那一位設為 1
// ……回溯時要移除皇后，就得寫：
col &= ~(1 << c);  // 先把掩碼取反，再 AND，才能把那一位清回 0
你會看到「移除」必須額外用 &= ~mask，語句不對稱；而 ^= 則一次搞定切換和還原，剛好很適合 backtracking 的流程。
*/

// 以下為 n = 4 的執行流程示例：
// 初始狀態:
//   col     = 0b0000, posDiag = 0b00000, negDiag = 0b00000
//   board = {
//     "....",
//     "....",
//     "....",
//     "...."
//   }
//
// 第 0 列放在 (0,1):
//   更新 col     = 0b0010   (1<<1)
//         posDiag = 0b00010  (1<<(0+1))
//         negDiag = 0b01000  (1<<(0-1+4)=1<<3)
//   board:
//     ".Q..",
//     "....",
//     "....",
//     "...."
//
// 第 1 列放在 (1,3):
//   更新 col     = 0b1010   (0b0010 | 0b1000)
//         posDiag = 0b10010  (0b00010 | 0b10000)
//         negDiag = 0b01100  (0b01000 | 0b00100)
//   board:
//     ".Q..",
//     "...Q",
//     "....",
//     "...."
//
// 第 2 列放在 (2,0):
//   更新 col     = 0b1011   (0b1010 | 0b0001)
//         posDiag = 0b10110  (0b10010 | 0b00100)
//         negDiag = 0b10100  (0b01100 | 0b10000)  // (2-0+4)=6 對應第 6 位
//   board:
//     ".Q..",
//     "...Q",
//     "Q...",
//     "...."
//
// 第 3 列放在 (3,2):
//   無衝突，最終 board:
//     ".Q..",
//     "...Q",
//     "Q...",
//     "..Q."
//   到達 r == 4，收集解 [" .Q..", "...Q", "Q...", "..Q." ]
