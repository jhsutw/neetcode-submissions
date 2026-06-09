// 2. Dynamic Programming (Top-Down)
public class Solution {
    int[][] memo; // memo[i][j]：從(i,j)走到右下角的路徑數；-1 代表尚未計算

    public int uniquePaths(int m, int n) {
        memo = new int[m][n];
        // 以 -1 作為「未計算」的標記；避免和合法值混淆
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // 從起點 (0,0) 開始
        return dfs(0, 0, m, n); // memo[0][0]會裝所有路徑可能的總和
    }

    // 回傳：從 (i, j) 到 (m-1, n-1) 的唯一路徑數
    public int dfs(int i, int j, int m, int n) {
        // 抵達終點
        if (i == m - 1 && j == n - 1) return 1;
        // 越界：無路徑
        if (i >= m || j >= n) return 0;
        // 記憶化：已算過直接回傳
        if (memo[i][j] != -1) return memo[i][j];

        // 狀態轉移：往右 + 往下
        memo[i][j] = dfs(i, j + 1, m, n)   // 往右
                   + dfs(i + 1, j, m, n); // 往下
        return memo[i][j];
    }
}