// 1. Brute Force (Recursion)
public class Solution {
    public int maxCoins(int[] nums) {
        // 建立一個新陣列，長度比原本多 2，在頭尾補上 1（方便處理邊界）
        int[] newNums = new int[nums.length + 2];
        newNums[0] = newNums[nums.length + 1] = 1; // 超越邊界視為*1，所以要頭尾設成邊界
        // 將原本的氣球數字放到新陣列中
        for (int i = 0; i < nums.length; i++) {
            newNums[i + 1] = nums[i]; // newNums從1複製到nums.length+1
        }

        // 呼叫遞迴函式計算最大硬幣數
        return dfs(newNums);
    }

    public int dfs(int[] nums) { // 這邊指的nums都是newNums
        // 當只剩下兩顆邊界氣球時（中間沒氣球），無法再戳，回傳 0
        if (nums.length == 2) {
            return 0;
        }

        int maxCoins = 0; // 記錄最大硬幣數
        // 嘗試戳每一顆非邊界的氣球
        for (int i = 1; i < nums.length - 1; i++) {
            // 戳破第 i 顆氣球獲得的硬幣數 = 左鄰 * 自己 * 右鄰
            int coins = nums[i - 1] * nums[i] * nums[i + 1];
            // 建立一個新的陣列，移除被戳破的氣球 (這邊指的nums都是newNums，所以實際長度是原本的nums.length+1)
            int[] newNums = new int[nums.length - 1];
            for (int j = 0, k = 0; j < nums.length; j++) {
                if (j != i) {
                    newNums[k++] = nums[j]; // 這邊k會從1開始複製到尾邊界前一個值，因為要預留0, newNums.length為邊界位置
                }
            }
            // 加上移除該氣球後，剩下的最大硬幣數（遞迴計算）
            coins += dfs(newNums);
            // 更新最大硬幣數
            maxCoins = Math.max(maxCoins, coins);
        }
        return maxCoins;
    }
}