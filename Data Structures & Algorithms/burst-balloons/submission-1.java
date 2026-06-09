// 2. Dynamic Programming (Top-Down)
public class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 建立新陣列，頭尾各加一個值為 1 的氣球，方便處理邊界
        int[] newNums = new int[n + 2];
        newNums[0] = newNums[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            newNums[i + 1] = nums[i];
        }

        // dp[l][r] 表示戳破區間 [l, r] 之間所有氣球可獲得的最大硬幣數
        int[][] dp = new int[n + 2][n + 2];
        // 初始化 dp 陣列為 -1，表示尚未計算
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        // 從區間 [1, n] 開始計算（忽略邊界的 1）
        return dfs(newNums, 1, newNums.length - 2, dp);
    }

    // 與其思考「下一顆要戳哪顆」，不如思考「某段區間最後戳哪顆」
    public int dfs(int[] nums, int l, int r, int[][] dp) {
        // 若區間無效，代表沒有氣球可戳
        if (l > r) {
            return 0;
        }
        // 如果已經計算過，直接回傳結果（記憶化）
        if (dp[l][r] != -1) {
            return dp[l][r];
        }

        dp[l][r] = 0; // 預設為 0
        // 嘗試在區間 [l, r] 中選擇最後一顆戳破的氣球 i
        for (int i = l; i <= r; i++) {
            // 計算戳破 i 這顆氣球時，左右氣球仍存在的硬幣數
            int coins = nums[l - 1] * nums[i] * nums[r + 1]; // 選 i 當最後戳破的氣球，拿到當下 nums[l-1] * nums[i] * nums[r+1] 的硬幣數（因為左右邊界 l-1 和 r+1 還存在）
            // 加上左右兩邊區間的最佳解（i作為切分點，找下一個最後一顆戳破的氣球，整體順序反過來）
            coins += dfs(nums, l, i - 1, dp) + dfs(nums, i + 1, r, dp);
            // 更新最大值
            dp[l][r] = Math.max(dp[l][r], coins);
        }
        return dp[l][r];
    }
}