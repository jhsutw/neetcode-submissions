// 3. Sorting
public class Solution {
    public int singleNumber(int[] nums) {
        // 先排序，使成對出現的數字相鄰
        Arrays.sort(nums);

        int i = 0;
        // 逐對檢查：每次比較相鄰兩個（i, i+1）
        while (i < nums.length - 1) {
            if (nums[i] == nums[i + 1]) {
                // 成對 → 跳過這一對，i 前進 2 格
                i += 2;
            } else {
                // 不成對 → 當前 nums[i] 就是那個只出現一次的數
                return nums[i];
            }
        }

        // 走到這裡代表前面都是成對的情況，
        // 單獨的數在最後一個位置（或只有一個元素的情況）
        return nums[i];
    }
}