class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 創建三個list，紀錄rows、cols、squares是否有重複值
        int [] rows = new int[9];
        int [] cols = new int[9];
        int [] squares = new int[9];

        // 遍歷每格檢查是否有重複值
        for (int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                if (board[r][c] == '.') continue;

                // 將 '1' ~ '9' 轉成 0~8 的索引值，方便對應到第幾位元
                // board[r][c]為char非int，所以在做加減要用''
                // '1' 的 ASCII 值是 49
                // i.e. '5' - '1' 相當於 index（53 - 49）= 4
                int val = board[r][c] - '1';

                // rows[r]會是一個二進位數 i.e. 010000000
                // rows[r]若含有某數字，該數字的位置為1
                // 若(rows[r] & (1 << val)) > 0，代表該數字已經出現過 > 重複值
                if ((rows[r] & (1 << val)) > 0 ||
                    (cols[c] & (1 << val)) > 0 ||
                    // squares[(r / 3) * 3 + (c / 3)]可以把九宮格位置用0~8編碼
                    (squares[(r / 3) * 3 + (c / 3)] & (1 << val)) > 0){
                        return false;
                    }
                
                // 把現在所在的格子的rows、cols、squares用val標註該格的數字（val）
                rows[r] |= (1 << val);
                cols[c] |= (1 << val);
                squares[(r / 3) * 3 + (c / 3)] |= (1 << val);
            }
        }
        return true;
    }
}
