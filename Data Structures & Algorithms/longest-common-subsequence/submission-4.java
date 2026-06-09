// 5. Dynamic Programming (Optimal)
// 跟上一個解法一樣，只不過把兩個dp精簡為一個dp，覆蓋的方式取代prep
public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // 讓 text2 成為較短字串，將空間壓到 O(min(m,n))
        if (text1.length() < text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        // dp[j] 代表 dp[i+1][j]（上一列、同欄位）的值
        int[] dp = new int[text2.length() + 1];

        // 由下而上（i: len1-1 -> 0），由右而左（j: len2-1 -> 0）
        for (int i = text1.length() - 1; i >= 0; i--) {
            int prev = 0; // 對應 dp[i+1][j+1]：右下角（對角線）的值
            for (int j = text2.length() - 1; j >= 0; j--) {
                int temp = dp[j]; // 暫存舊的 dp[i+1][j]，等等要給下一步當 prev
                if (text1.charAt(i) == text2.charAt(j)) {
                    // 相等 -> 1 + 右下角（dp[i+1][j+1]）
                    dp[j] = 1 + prev;
                } else {
                    // 不等 -> max(右方 dp[i][j+1], 下方 dp[i+1][j])
                    dp[j] = Math.max(dp[j], dp[j + 1]);
                }
                prev = temp; // 移動對角線：下一個 j-1 的右下角就是現在的 dp[i+1][j]
            }
        }

        // dp[0] = dp[i=0][j=0]
        return dp[0];
    }
}