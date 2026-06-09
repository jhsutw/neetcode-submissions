// 4. Dynamic Programming (Space Optimized)
// 每次計算每格通過的路徑數，透過更新row（下面的行）、newRow（row的上面一行）
public class Solution {
    public int uniquePaths(int m, int n) {
        // row[j] 表示當前行 j 列的路徑數
        // 初始化為 1，因為最後一行往右走只有一條路
        int[] row = new int[n];
        Arrays.fill(row, 1);

        // 從倒數第二行開始往上計算
        for (int i = 0; i < m - 1; i++) {
            int[] newRow = new int[n];
            Arrays.fill(newRow, 1); // 最右邊一列的路徑數固定為 1

            // 從右往左更新，避免覆蓋尚未使用到的值
            for (int j = n - 2; j >= 0; j--) {
                // 往右走：newRow[j+1]
                // 往下走：row[j]
                newRow[j] = newRow[j + 1] + row[j];
            }

            // 滾動更新到上一行
            row = newRow;
        }
        return row[0]; // 回傳起點位置的路徑數
    }
}