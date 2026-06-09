// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // dp: 目前可達「總和」 -> 對應的「組法數」
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1); // 尚未使用任何數字時，只能組成總和 0，且方法數為 1（空集合）

        for (int num : nums) {
            // nextDp: 引入當前 num 後的新一層狀態
            Map<Integer, Integer> nextDp = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                int total = entry.getKey();     // 既有可達的總和
                int count = entry.getValue();   // 達成該總和的方法數

                // 在 num 前加 '+'
                nextDp.put(total + num, nextDp.getOrDefault(total + num, 0) + count);
                // 在 num 前加 '-'
                nextDp.put(total - num, nextDp.getOrDefault(total - num, 0) + count);
            }
            // 滾動到下一層
            dp = nextDp;
        }

        // 回傳最後可達 target 的方法數，若沒有則為 0
        return dp.getOrDefault(target, 0);
    }
}