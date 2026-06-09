public class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int low = 1;             // 最小可能的數值
        int high = n - 1;        // 最大可能的數值 (nums.length = n+1，所以最大值是 n)

        while (low < high) {
            int mid = low + (high - low) / 2;  // 中間值
            int lessOrEqual = 0;              // 統計 nums 中 <= mid 的數字數量

            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    lessOrEqual++;
                }
            }

            if (lessOrEqual <= mid) {
                // 若小於等於 mid 的數量 <= mid，代表重複數字應該在右半邊 (i.e. input: 12344；若 mid=3，左半邊沒重複的情況<=3的數量不該超過三個)
                low = mid + 1;
            } else {
                // 否則代表重複數字在左半邊（含 mid）
                high = mid;
            }
        }

        return low; // 最終 low == high，即為重複數字
    }
}

