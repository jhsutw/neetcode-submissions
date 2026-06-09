// 4. Dynamic Programming (Bottom-Up) - I
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i][j] 表示「從索引 i 開始、上一個被選取元素索引為 j-1」時，能取得的最長遞增子序列長度
        // j+1 是為了讓 j = -1 的情況也能存在陣列中（因為陣列不能有負索引）
        int[][] dp = new int[n + 1][n + 1];

        // 從後往前填表（bottom-up）
        for (int i = n - 1; i >= 0; i--) {
            // j 從 i-1 開始到 -1（-1 代表之前沒有選過任何數）
            for (int j = i - 1; j >= -1; j--) {

                // 情況一：不選 nums[i]
                int LIS = dp[i + 1][j + 1];

                // 情況二：選 nums[i]
                // 條件：j == -1（沒有前一個數）或 nums[j] < nums[i]（保持遞增）
                if (j == -1 || nums[j] < nums[i]) {
                    // 選 nums[i] 後，前一個元素索引變成 i，所以去看 dp[i+1][i+1] (後項為了防止j=-1所以+1，但其實是i)
                    LIS = Math.max(LIS, 1 + dp[i + 1][i + 1]);
                }

                // 記錄最佳結果
                dp[i][j + 1] = LIS;
            }
        }

        // 回傳從索引 0 開始、且前面沒有選任何數的情況
        return dp[0][0];
    }
}