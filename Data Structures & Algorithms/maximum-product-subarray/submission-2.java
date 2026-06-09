// 3. Kadane's Algorithm
public class Solution {
    public int maxProduct(int[] nums) {
        int res = nums[0];     // 目前為止的最大乘積答案

        int curMin = 1,        // 以目前位置為結尾的「最小乘積」（可能因為負數而變成最大）(賭負最大的數*負數會不會變最大)
            curMax = 1;        // 以目前位置為結尾的「最大乘積」

        for (int num : nums) {
            int tmp = curMax * num; 
            // 先把「舊的」curMax*num 存起來，因為等會要用來計算新的 curMin
            // （同一輪內 curMax/curMin 都要用到「上一輪的值」去乘 num）

            // 新的 curMax：從三者取最大
            // 1) num*curMax（把目前數字接在原本最大乘積後面）
            // 2) num*curMin（如 num < 0，最小乘積 * 負數 可能變最大）
            // 3) num 本身（重新開一段子陣列）
            curMax = Math.max(Math.max(num * curMax, num * curMin), num);

            // 新的 curMin：從三者取最小
            // 1) tmp（也就是「舊的」curMax*num）
            // 2) num*curMin（把目前數字接在原本最小乘積後面）
            // 3) num 本身（重新開一段子陣列）
            curMin = Math.min(Math.min(tmp, num * curMin), num);

            // 用目前更新後的 curMax 嘗試刷新全域答案
            res = Math.max(res, curMax);
        }
        return res;
    }
}