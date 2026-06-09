// 5. Dynamic Programming (Hash Set)
public class Solution {
    public boolean canPartition(int[] nums) {
        // 若所有元素總和為奇數，無法分成兩個和相等的子集
        if (Arrays.stream(nums).sum() % 2 != 0) {
            return false;
        }

        // dp: 目前「可達成的子集合和」集合（使用 HashSet 避免重複）
        Set<Integer> dp = new HashSet<>();
        dp.add(0); // 和為 0（空集合）一定可達

        int target = Arrays.stream(nums).sum() / 2; // 目標和：總和的一半

        // 逐一處理每個數字（由後往前處理等價於由前往後；此寫法不影響正確性）
        for (int i = nums.length - 1; i >= 0; i--) {
            // nextDP: 本輪加入 nums[i] 後的新「可達成和」集合
            // 使用新集合以避免「同一輪」重複使用同一元素（0/1 背包特性）
            Set<Integer> nextDP = new HashSet<>();

            // 枚舉上一輪所有可達成的和 t，嘗試「不選 / 選 nums[i]」
            for (int t : dp) {
                // 只要有任一組合正好湊到 target，立即回傳 true（提早結束）
                if (t + nums[i] == target) {
                    return true;
                }
                // 選 nums[i]：可達成 t + nums[i]
                nextDP.add(t + nums[i]);
                // 不選 nums[i]：維持 t
                nextDP.add(t);
            }

            // 滾動到下一輪
            dp = nextDP;
        }
        // 全部元素用過仍沒湊到 target
        return false;
    }
}