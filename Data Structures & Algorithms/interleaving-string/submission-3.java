// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;

        // 讓 s1 較短，空間壓到 O(max(m,n))
        if (n < m) {
            String temp = s1; s1 = s2; s2 = temp;
            int t = m; m = n; n = t;
        }

        // dp[j] 對應 2D DP 的 dp[i+1][j]
        boolean[] dp = new boolean[n + 1];

        // 先初始化「底列」：i = m 時，dp[m][j]
        // dp[m][n] = true
        dp[n] = true;
        // 對 j 從 n-1..0：只剩 s2 去匹配 s3[m + j..]
        for (int j = n - 1; j >= 0; j--) {
            dp[j] = (s2.charAt(j) == s3.charAt(m + j)) && dp[j + 1];
        }

        // 往上填每一列 i = m-1..0
        for (int i = m - 1; i >= 0; i--) {
            boolean[] nextDp = new boolean[n + 1];

            // 先處理 j = n：只剩 s1 去匹配 s3[i + n..]
            nextDp[n] = (s1.charAt(i) == s3.charAt(i + n)) && dp[n];

            // 內層 j = n-1..0：兩種選擇
            for (int j = n - 1; j >= 0; j--) {
                boolean takeS1 = (s1.charAt(i) == s3.charAt(i + j)) && dp[j];        // 用 s1[i]，看下面那列
                boolean takeS2 = (s2.charAt(j) == s3.charAt(i + j)) && nextDp[j + 1]; // 用 s2[j]，看本列右邊
                nextDp[j] = takeS1 || takeS2;
            }
            dp = nextDp;
        }
        return dp[0];
    }
}