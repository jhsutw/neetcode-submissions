public class Solution {
    // 四個方向：上、下、左、右
    private static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};
    private int[][] memo; // memo[r][c]：從 (r,c) 出發的最長遞增路徑長度

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length, n = matrix[0].length;
        memo = new int[m][n];
        int ans = 0;

        // 對每個起點求一次，結果會被 memo 記住，不重複計算
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                ans = Math.max(ans, dfs(matrix, r, c));
            }
        }
        return ans;
    }

    private int dfs(int[][] a, int r, int c) {
        if (memo[r][c] != 0) return memo[r][c]; // 已算過

        int best = 1; // 至少包含自己這一格
        for (int[] d : DIRS) {
            int nr = r + d[0], nc = c + d[1];
            // 只能往更大的相鄰值前進
            if (nr >= 0 && nr < a.length && nc >= 0 && nc < a[0].length && a[nr][nc] > a[r][c]) {
                best = Math.max(best, 1 + dfs(a, nr, nc));
            }
        }
        return memo[r][c] = best;
    }
}