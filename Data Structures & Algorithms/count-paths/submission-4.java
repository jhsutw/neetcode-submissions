// 5. Dynamic Programming (Optimal)
public class Solution {
    public int uniquePaths(int m, int n) {
        // dp[j] 表示「當前行第 j 列」到終點的路徑數
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 最底行每個位置只能往右走，因此路徑數都是 1

        // 從倒數第二行開始往上計算
        for (int i = m - 2; i >= 0; i--) {
            // 從倒數第二列開始往左更新
            for (int j = n - 2; j >= 0; j--) {
                // 往右走 dp[j+1] + 往下走 dp[j] (因為每一輪是複製下面一個row值再更新dp[j]所以不用再加一次)
                dp[j] += dp[j + 1];
            }
        }

        // 起點 (0,0) 的路徑數
        return dp[0];
    }
}