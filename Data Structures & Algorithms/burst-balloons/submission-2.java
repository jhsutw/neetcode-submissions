// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        
        // 建立一個新陣列，長度比原本多 2
        // 在最左與最右各加上一個值為 1 的「虛擬氣球」
        // 方便處理邊界情況（戳破時一定有左右鄰居）
        int[] newNums = new int[n + 2];
        newNums[0] = newNums[n + 1] = 1; // 邊界氣球值為 1
        for (int i = 0; i < n; i++) {
            newNums[i + 1] = nums[i]; // 將原本的氣球值複製到中間
        }

        // dp[l][r] 表示：戳破區間 [l, r]（僅包含內部氣球）的最大硬幣數
        // 索引 l、r 的範圍為 1..n（因為 0 與 n+1 是邊界氣球，不會被戳）
        int[][] dp = new int[n + 2][n + 2];

        // 區間 DP：由短區間開始計算，逐漸擴展到長區間
        // 這裡 l 從右往左（n 到 1）遞減，確保子問題已經被計算過
        for (int l = n; l >= 1; l--) {
            // r 從 l 往右擴展到 n，代表不同長度的區間
            for (int r = l; r <= n; r++) {
                // 嘗試將區間 [l, r] 中的每個氣球 i
                // 當作「最後一顆被戳破的氣球」
                for (int i = l; i <= r; i++) {
                    // 當 i 是最後一顆被戳破時，l-1 與 r+1 兩顆氣球仍然存在
                    int coins = newNums[l - 1] * newNums[i] * newNums[r + 1];
                    
                    // 加上左區間與右區間的最大硬幣數
                    coins += dp[l][i - 1] + dp[i + 1][r];
                    
                    // 更新 dp[l][r] 為目前找到的最大值
                    dp[l][r] = Math.max(dp[l][r], coins);
                }
            }
        }

        // 最終答案是戳破所有內部氣球（區間 1..n）能獲得的最大硬幣數
        return dp[1][n];
    }
}