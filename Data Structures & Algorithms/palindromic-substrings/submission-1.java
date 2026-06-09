// 2. Dynamic Programming
public class Solution {
    public int countSubstrings(String s) {
        int res = 0, n = s.length();  // res 用來統計回文子字串總數，n 是字串長度
        boolean[][] dp = new boolean[n][n];  // dp[i][j] 表示子字串 s[i...j] 是否為回文

        // 從後往前掃描起點 i（因為我們要先知道 dp[i+1][j-1] 的值）
        for (int i = n - 1; i >= 0; i--) {
            // 結束點 j 從 i 向右延伸
            for (int j = i; j < n; j++) {
                // 如果 s[i] == s[j] 且：
                // 1. 子字串長度 <= 2（即 j - i <= 2），例如 "a", "aa", "aba"
                // 2. 或者內層子字串 s[i+1...j-1] 本身就是回文（dp[i+1][j-1] 為 true）
                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || dp[i + 1][j - 1])) {

                    dp[i][j] = true;  // 標記 s[i...j] 是回文
                    res++;  // 回文數量加一
                }
            }
        }

        return res;  // 回傳所有回文子字串的個數
    }
}