// binary search (one way)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int l = 0, r = ROWS * COLS - 1;
        while (l <= r){
            int m = l + (r - l) / 2; // 即使 (top + bot) 的結果不是整數，Java 會自動向下取整（floor）
            // row = m / COLS; col = m % COLS
            int row = m / COLS, col = m % COLS;
            if (target > matrix[row][col]){
                l = m + 1;
            } else if (target < matrix[row][col]){
                r = m - 1;
            } else{
                return true;
            }
        }
        return false;
    }
}
