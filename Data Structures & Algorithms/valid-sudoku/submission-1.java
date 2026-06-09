class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 建立cols、rows、squares 三個 HashMap 以檢查重複值
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<String, Set<Character>> squares = new HashMap<>();

        // 遞迴每一個格子
        for (int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++){
                if (board[r][c] == '.') continue;
                // 定義每一個格子所屬的九宮格
                // Java中(1)''內放char、(2)""內放str
                String squareKey = (r / 3) + "," + (c / 3);

                // 檢查每個row、col、square中否有重複值
                /* rows.computeIfAbsent(r, k -> new HashSet<>())
                   (1) 若rows中有r這個key，直接用.contains(board[r][c])查找對應的HashSet是否有重複值
                   (2) 若rows中沒有r這個key，創建該key並創建其對應的HashSet
                */
                if (rows.computeIfAbsent(r, k -> new HashSet<>()).contains(board[r][c]) ||
                    cols.computeIfAbsent(c, k -> new HashSet<>()).contains(board[r][c]) ||
                    squares.computeIfAbsent(squareKey, k -> new HashSet<>()).contains(board[r][c])){
                        return false;
                    }

                // 將格子中的值加入對應的rows、cols、squares（key）中，作為其value
                rows.get(r).add(board[r][c]);
                cols.get(c).add(board[r][c]);
                squares.get(squareKey).add(board[r][c]);
            }
        }
        return true;
    }
}
