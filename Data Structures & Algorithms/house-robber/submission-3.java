// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public int rob(int[] nums) {
        // rob1：代表 dp[i - 2]，搶到前兩間房的最大金額
        // rob2：代表 dp[i - 1]，搶到前一間房的最大金額
        int rob1 = 0, rob2 = 0;

        // 遍歷每一間房子
        for (int num : nums) {
            // temp 是目前這一間房子搶或不搶的最大金額
            // 選擇：
            // 1. 搶這間 -> num + rob1（跳過前一間）
            // 2. 不搶這間 -> rob2（維持前一間的最大金額）
            // 把rob1更新成rob2；rob2更新成下一個搶劫點
            int temp = Math.max(num + rob1, rob2);

            // 更新 rob1 和 rob2，往後滑動
            rob1 = rob2;
            rob2 = temp;
        }

        // rob2 儲存的是最後一間房子的最大搶劫金額
        return rob2;
    }
}