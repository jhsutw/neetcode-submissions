class Solution {
    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            Set<Character> existed = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') continue;
                if (existed.contains(board[i][j])) {
                    return false;
                } else {
                    existed.add(board[i][j]);
                }
            }
        }

        for (int j = 0; j < n; j++) {
            Set<Character> existed = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (board[i][j] == '.') continue;
                if (existed.contains(board[i][j])) {
                    return false;
                } else {
                    existed.add(board[i][j]);
                }
            }
        }

        for (int k = 0; k < n; k++) {
            int row = k / 3;
            int col = k % 3;
            Set<Character> existed = new HashSet<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[row * 3 + i][col * 3 + j] == '.') continue;
                    if (existed.contains(board[row * 3 + i][col * 3 + j])) {
                        return false;
                    } else {
                        existed.add(board[row * 3 + i][col * 3 + j]);
                    }
                }
            }
        }
        return true;
    }
}
