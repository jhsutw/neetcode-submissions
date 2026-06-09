// 7. Dynamic Programming + Binary Search
public class Solution {
    public int lengthOfLIS(int[] nums) {
        // 以「耐心排序（Patience Sorting）」思路求 LIS 長度，時間 O(n log n)
        // dp[k] 代表「長度為 k+1 的遞增子序列」的最小結尾值（tail）
        // 注意：dp 不是實際序列，只是用來維護每個長度對應的最小尾巴

        List<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);  // 先放第一個元素作為長度 1 的尾巴

        int LIS = 1;      // 目前觀察到的最長遞增子序列長度
        for (int i = 1; i < nums.length; i++) {
            // 若當前數比 dp 的最後一個（最長序列的尾巴）還大
            // 代表可以把整個序列延長：直接在尾端加入
            if (dp.get(dp.size() - 1) < nums[i]) {
                dp.add(nums[i]);
                LIS++;          // 最長長度 +1
                continue;
            }

            // 否則，用二分搜尋找到「第一個 >= nums[i]」的位置 idx
            // 將該位置的尾巴改成更小的 nums[i]，使未來更容易延長
            int idx = Collections.binarySearch(dp, nums[i]); // 在 dp 這個 已經排好序的 List 中，用 二分搜尋 找 nums[i] 的位置
            if (idx < 0) idx = -idx - 1;  // Java 的 binarySearch 插入點轉換（在「找不到完全相同元素」時，不會直接回傳插入位置，而是用 負數編碼插入點）
            dp.set(idx, nums[i]);         // 以更小的尾巴取代，維持「最小尾巴」性質
        }

        return LIS; // 等價於回傳 dp.size()；兩者皆為 LIS 長度
    }
}