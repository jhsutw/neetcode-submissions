class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 檢查每個row是否無重複值
        for (int row = 0; row < 9; row++){
            // Ｈash Table只有一種元素用HashSet；兩種元素用HashMap
            Set<Character> seen = new HashSet<>();
            for (int i = 0; i < 9; i ++){
                // 重複值要排除'.'，所以要獨立出來寫continue式
                if (board[row][i] == '.') continue;
                if (seen.contains(board[row][i])) return false;
                seen.add(board[row][i]);
            }
        }

        // 檢查每個col是否無重複值
        for (int col = 0; col < 9; col++){
            Set<Character> seen = new HashSet<>();
            for (int i = 0; i < 9; i++){
                if (board[i][col] == '.') continue;
                if (seen.contains(board[i][col])) return false;
                seen.add(board[i][col]);
            }
        }

        // 檢查每個九宮格是否無重複值
        /* 以下用法用於遞迴九個九宮格（左上 → 右上 → 左中 → … → 右下）
        for (int square = 0; square < 9; square++){
            Set<Character> seen = new HashSet<>();
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    int row = (square / 3) * 3 + i;
                    int col = (square % 3) * 3 + j;
        */
        for (int square = 0; square < 9; square++){
            Set<Character> seen = new HashSet<>();
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    int row = (square / 3) * 3 + i;
                    int col = (square % 3) * 3 + j;
                    if (board[row][col] == '.') continue;
                    if (seen.contains(board[row][col])) return false;
                    seen.add(board[row][col]);
                }
            }
        }
        return true;
    }
}
