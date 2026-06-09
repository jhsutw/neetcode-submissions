// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j] 代表：text1 的子字串 text1[i:] 與 text2 的子字串 text2[j:] 的 LCS 長度
        // 多開一列一行做邊界（i==len 或 j==len 時 LCS=0）
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        // 自底向上：從字串尾端往前填
        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    // 當前字元相同：取 1 + 右下角子問題
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    // 不同：略過 text1[i] 或略過 text2[j]，取較大者
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        // 起點 (0,0) 即為整體 LCS 長度
        return dp[0][0];
    }
}
