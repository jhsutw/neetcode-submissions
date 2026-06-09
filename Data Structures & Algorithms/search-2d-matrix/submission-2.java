// Staircase Search 
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = n - 1;

        /* 每次比較 matrix[r][c] 和 target：
        1. 若大於 target → 向左移動（c--）
        2. 若小於 target → 向下移動（r++）
        3. 若相等 → 回傳 true
        */
        while (r < m && c >= 0) {
            if (matrix[r][c] > target){
                c--;
            } else if (matrix[r][c] < target){
                r++;
            } else {
                return true;
            }
        }
        return false;
    }
}
