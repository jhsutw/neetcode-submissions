public class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int res = 0;

        // 檢查每個 bit 位元（int 有 32 位元）
        for (int b = 0; b < 32; b++) {
            int x = 0, y = 0;
            int mask = 1 << b; // 建立第 b 個位元的遮罩

            // 計算 nums 陣列中有哪些數字的第 b 位是 1 (包含重複值，為nums有的值)
            for (int num : nums) {
                if ((num & mask) != 0) {
                    x++;
                }
            }

            // 計算從 1 到 n-1 中有哪些數字的第 b 位是 1 (不含重複值，為所有可能值)
            for (int num = 1; num < n; num++) {
                if ((num & mask) != 0) {
                    y++;
                }
            }

            // 如果 x > y，代表重複的那個數的這一位是 1
            if (x > y) {
                res |= mask; // 把該位元設為 1
            }
        }

        return res;
    }
}
