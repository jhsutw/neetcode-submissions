// 2. Dynamic Programming (Top-Down)
public class Solution {
    // 四個方向（上、下、左、右）的位移，用來走鄰接格
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 記憶化陣列：dp[r][c] 紀錄「從 (r,c) 出發」的最長遞增路徑長度；-1 代表尚未計算
    int[][] dp;

    // 以 (r, c) 為當前位置、prevVal 為前一格的值，回傳從 (r,c) 出發的最長遞增路徑長度
    private int dfs(int[][] matrix, int r, int c, int prevVal) {
        int ROWS = matrix.length, COLS = matrix[0].length;

        // 邊界與單調性檢查：
        // 超出邊界或當前值 <= 前一格的值（不嚴格遞增）就停止，回傳 0（代表不能前進）
        if (r < 0 || r >= ROWS || c < 0 ||
            c >= COLS || matrix[r][c] <= prevVal) {
            return 0;
        }

        // 若此格已經計算過最長路徑，直接回傳快取結果
        if (dp[r][c] != -1) return dp[r][c];

        // 至少包含自己這一格，因此起始長度為 1
        int res = 1;

        // 嘗試往四個方向延伸，僅當鄰格值大於當前值才會在下一輪通過檢查繼續遞迴
        for (int[] d : directions) {
            res = Math.max(res, 1 + dfs(matrix, r + d[0],
                                    c + d[1], matrix[r][c]));
        }

        // 記錄並回傳從 (r,c) 出發的最長遞增路徑
        return dp[r][c] = res;
    }

    // 主函式：計算整個矩陣的最長遞增路徑
    public int longestIncreasingPath(int[][] matrix) {
        int ROWS = matrix.length, COLS = matrix[0].length;
        int LIP = 0; // 最終答案（Longest Increasing Path）

        // 初始化記憶化陣列為 -1（未計算）
        dp = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                dp[i][j] = -1;
            }
        }

        // 以每一格作為起點，嘗試走最長遞增路徑
        // 初始 prevVal 給最小整數，確保第一步一定能走
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                LIP = Math.max(LIP, dfs(matrix, r, c, Integer.MIN_VALUE));
            }
        }

        // 回傳最長遞增路徑長度
        return LIP;
    }
}