// 4. Dynamic Programming (Space Optimized)

public class Solution {
    public boolean canPartition(int[] nums) {
        // 若總和為奇數，無法拆成兩個和相等的子集
        if (sum(nums) % 2 != 0) {
            return false;
        }

        int target = sum(nums) / 2; // 目標和為總和的一半
        boolean[] dp = new boolean[target + 1];     // 當前輪的狀態
        boolean[] nextDp = new boolean[target + 1]; // 下一輪的狀態

        dp[0] = true; // 和為 0 一定可達成（選空集合）

        // 外層迴圈：逐一考慮 nums[i] 是否選取
        for (int i = 0; i < nums.length; i++) {
            // 內層迴圈：枚舉所有目標和 j
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) {
                    // 狀態轉移：
                    // 不選 nums[i] -> dp[j]
                    // 選 nums[i]   -> dp[j - nums[i]]
                    nextDp[j] = dp[j] || dp[j - nums[i]];
                } else {
                    // 放不下 nums[i]，只能承接上一輪結果
                    nextDp[j] = dp[j];
                }
            }
            // 將下一輪的結果變成當前輪，並重置 nextDp 給下一輪用
            boolean[] temp = dp;
            dp = nextDp;
            nextDp = temp;
        }

        // 回傳是否能達成 target
        return dp[target];
    }

    // 計算陣列總和
    private int sum(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        return total;
    }
}