class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];   // 建立 DP 陣列，dp[i] 表示數字 i 的二進位表示中有幾個 1
        int offset = 1;              // offset 紀錄當前的「最高位元」(2 的次方)

        for (int i = 1; i <= n; i++) {
            if (offset * 2 == i) {   // 當 i 達到下一個 2 的次方 (像 2, 4, 8, 16 ...)
                offset = i;          // 更新 offset 為當前 i
            }
            dp[i] = 1 + dp[i - offset];  
            // i - offset 去掉最高位元，剩下的部份 dp 已經算過
            // 再加上最高位元的 1
        }
        return dp;   // 回傳整個 dp 陣列
    }
}
