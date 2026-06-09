// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        // dp[i]：使用前 i 個數字（nums[0..i-1]）時，
        //        「某個總和 -> 能達成該總和的方式數」的映射
        Map<Integer, Integer>[] dp = new HashMap[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = new HashMap<>();
        }

        // 初始狀態：尚未選任何數字時，只能組成總和 0，方式數為 1（空集合）
        dp[0].put(0, 1);

        // 逐步把第 i 個數字（nums[i]）加入考量
        for (int i = 0; i < n; i++) {
            for (Map.Entry<Integer, Integer> entry : dp[i].entrySet()) {
                int total = entry.getKey();    // 目前的總和
                int count = entry.getValue();  // 達成該總和的方式數

                // 在 nums[i] 前加上 '+'：總和變為 total + nums[i]
                dp[i + 1].put(
                    total + nums[i],
                    dp[i + 1].getOrDefault(total + nums[i], 0) + count
                );

                // 在 nums[i] 前加上 '-'：總和變為 total - nums[i]
                dp[i + 1].put(
                    total - nums[i],
                    dp[i + 1].getOrDefault(total - nums[i], 0) + count
                );
            }
        }

        // 回傳使用所有 n 個數字後，能達成 target 的方式數（若無則為 0）
        return dp[n].getOrDefault(target, 0);
    }
}