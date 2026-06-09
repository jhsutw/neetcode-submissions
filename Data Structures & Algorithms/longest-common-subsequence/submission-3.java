// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // 讓 text2 變成較短的那條，降低空間（dp 長度 = |text2| + 1）
        if (text1.length() < text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        // prev = dp[i+1][*]（上一列），curr = dp[i][*]（本列）
        int[] prev = new int[text2.length() + 1];
        int[] curr = new int[text2.length() + 1];

        // 從尾端往前填（對應 bottom-up：i = |t1|-1..0, j = |t2|-1..0）
        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    // 字元相同：1 + 右下角（prev[j+1] 對應 dp[i+1][j+1]）
                    curr[j] = 1 + prev[j + 1];
                } else {
                    // 不同：取右方（curr[j+1] = dp[i][j+1]）與下方（prev[j] = dp[i+1][j]）較大者
                    /*
                    curr[j + 1] → dp[i][j+1]
                    在同一列（同樣的 i），向右一格。
                    因為我們內層迴圈 j 是由右往左跑，所以 curr[j+1] 已經被更新過，可以直接用。

                    prev[j] → dp[i+1][j]
                    在下一列（i+1），同一個 j。
                    因為外層迴圈 i 是由下往上跑，所以 prev 存的就是上一輪的結果（下一列的值）
                    */
                    curr[j] = Math.max(curr[j + 1], prev[j]);
                }
            }
            // 滾動：本列成為下一輪的「上一列」，curr 重用為空殼
            int[] temp = prev;
            prev = curr;
            curr = temp;
            // （可選）Arrays.fill(curr, 0); 不必填零，因為每輪都覆寫到 j=0..|text2|-1
        }

        // dp[0][0]
        return prev[0];
    }
}