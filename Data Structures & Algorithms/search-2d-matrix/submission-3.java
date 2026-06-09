// binary search
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        // 先檢查哪一個ROW包含target值
        int top = 0, bot = ROWS - 1;
        while (top <= bot){
            int row = (top + bot) / 2;
            if (target > matrix[row][COLS - 1]){
                top = row + 1;
            } else if (target < matrix[row][0]){
                bot = row - 1;
            } else{
                break;
            }
        }

        if (!(top <= bot)){ // 如果 top > bot，代表整個矩陣都沒一列符合
            return false;
        }

        // 再檢查哪一個該ROW的哪個COL包含target值
        int row = (top + bot) / 2;
        int l = 0, r = COLS - 1;
        while (l <= r){
            int m = (l + r) / 2;
            if (target > matrix[row][m]){
                l = m + 1;
            } else if (target < matrix[row][m]){
                r = m - 1;
            } else{
                return true;
            }
        }
        return false;
    }
}
