// 2. Dynamic Programming (Top-Down)
public class Solution {
    // dp[i][j] 紀錄子問題 dfs(i, j) 的結果，以避免重複計算
    // 含義：s[i..] 是否能與 p[j..] 完全匹配
    private Boolean[][] dp;

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        dp = new Boolean[m + 1][n + 1]; // 狀態空間：i ∈ [0..m], j ∈ [0..n]
        return dfs(0, 0, s, p, m, n);
    }

    // dfs(i, j): 判斷 s[i..] 是否能與 p[j..] 匹配
    private boolean dfs(int i, int j, String s, String p, int m, int n) {
        // 若樣式 p 已用完（j 到末尾），則只有當 s 也同時用完（i == m）才算整體匹配
        if (j == n) {
            return i == m;
        }

        // 記憶化：若已計算過此狀態則直接回傳
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        // 判斷當前字元是否可「一對一」匹配（不包含 * 的情況）
        // 條件：i 未越界，且 (當前字元相同 或 p[j] == '.')
        boolean match = i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // 如果下一個樣式字元是 '*'，代表 p[j] 這個 preceding element 可重複 0 次或多次
        if (j + 1 < n && p.charAt(j + 1) == '*') {
            // 兩種選擇只要其一成立即可：
            // 1) 略過此「X*」整段（當作出現 0 次）：j 跳兩格到 j+2
            // 2) 若當前可匹配（match == true），消耗一個 s 的字元（i -> i+1），p 留在 j（因 X* 還能繼續匹配更多）
            dp[i][j] = dfs(i, j + 2, s, p, m, n) || (match && dfs(i + 1, j, s, p, m, n));
        } else {
            // 沒有 '*' 可用時，必須「一對一」往下匹配
            dp[i][j] = match && dfs(i + 1, j + 1, s, p, m, n);
        }

        return dp[i][j];
    }
}