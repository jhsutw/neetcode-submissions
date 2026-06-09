// 5. Dynamic Programming (Bottom-Up) - II

public class Solution {
    public int lengthOfLIS(int[] nums) {
        // LIS[i] 代表「從索引 i 開始的最長遞增子序列長度」
        int[] LIS = new int[nums.length];
        // 初始化，每個位置至少可以形成長度為 1 的序列（自己本身）
        Arrays.fill(LIS, 1);

        // 從右往左掃描，因為每個位置的結果依賴於後面的元素
        for (int i = nums.length - 1; i >= 0; i--) {
            // 檢查 i 後面的所有元素 j
            for (int j = i + 1; j < nums.length; j++) {
                // 如果 nums[i] < nums[j]，代表可以把 nums[i] 接到 nums[j] 前面形成更長的序列
                if (nums[i] < nums[j]) {
                    // 更新 LIS[i] 為最大值（保留更長的序列長度）
                    LIS[i] = Math.max(LIS[i], 1 + LIS[j]);
                }
            }
        }

        // 回傳整個 LIS 陣列中的最大值，即最長遞增子序列的長度
        return Arrays.stream(LIS).max().getAsInt();
    }
}
