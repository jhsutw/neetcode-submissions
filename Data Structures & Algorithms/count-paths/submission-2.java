// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int uniquePaths(int m, int n) {
        // 建立 (m+1) x (n+1) 陣列，多出的一行一列可避免邊界檢查
        int[][] dp = new int[m + 1][n + 1];

        // 終點 (m-1, n-1) 有 1 條路徑（站在終點）
        dp[m - 1][n - 1] = 1;

        // 從右下往左上填表
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // 累加從下方與右方來的路徑數
                dp[i][j] += dp[i + 1][j] + dp[i][j + 1];
            }
        }

        // 回傳從起點 (0, 0) 到終點的路徑數
        return dp[0][0];
    }
}