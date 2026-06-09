// 4. Dynamic Programming (Space Optimized)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // 為了把空間壓到 O(min(m, n))，確保 n 是較短的字串長度
        // 若 word2 比 word1 長，就交換兩者與其長度
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
            String t = word1;
            word1 = word2;
            word2 = t;
        }

        // 1D 滾動陣列：
        // dp[j]    對應「下一列」（i+1）的 dp[i+1][j]
        // nextDp[j]對應「當前列」（i）的   dp[i][j]
        int[] dp = new int[n + 1];
        int[] nextDp = new int[n + 1];

        // 底列（i = m）：把空字串轉成 word2[j..] 只能「插入」剩餘字元
        // 因此 dp[j] = n - j
        for (int j = 0; j <= n; j++) {
            dp[j] = n - j;
        }

        // 自底向上填表：i 從 m-1 .. 0
        for (int i = m - 1; i >= 0; i--) {
            // 右邊界（j = n）：把 word1[i..] 轉成空字串只能「刪除」剩餘字元
            // 因此 nextDp[n] = m - i
            nextDp[n] = m - i;

            // 內圈 j 從 n-1 .. 0
            for (int j = n - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    // 字元相同：不需操作，等同於 dp[i+1][j+1]
                    nextDp[j] = dp[j + 1];
                } else {
                    // 三種操作取最小 + 1：
                    // 刪除（dp[i+1][j]）      -> dp[j]
                    // 插入（dp[i][j+1]）       -> nextDp[j+1] nextDP等同於i+1
                    // 替換（dp[i+1][j+1]）    -> dp[j+1]
                    nextDp[j] = 1 + Math.min(
                        dp[j],
                        Math.min(nextDp[j + 1], dp[j + 1])
                    );
                }
            }
            // 將當前列拷貝成下一輪的「下一列」
            System.arraycopy(nextDp, 0, dp, 0, n + 1);
        }

        // 最終答案：dp[0] = 將 word1[0..] 轉成 word2[0..] 的最小編輯距離
        return dp[0];
    }
}