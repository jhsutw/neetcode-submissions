class Solution {
    public int findDuplicate(int[] nums) {
        // 初始化快慢指標
        int slow = 0, fast = 0;

        // 第 1 階段：找出快慢指標相遇的位置（進入 cycle）
        while (true) {
            slow = nums[slow];             // 慢指標走一步
            fast = nums[nums[fast]];       // 快指標走兩步
            if (slow == fast) {
                break;                     // 相遇表示有環
            }
        }

        // 第 2 階段：從頭與相遇點同速出發，找出環的起點
        int slow2 = 0;
        while (true) {
            slow = nums[slow];             // 慢指標繼續前進
            slow2 = nums[slow2];           // 第二個指標從頭走
            if (slow == slow2) {
                return slow;               // 相遇點就是重複的數字
            }
        }
    }
}
