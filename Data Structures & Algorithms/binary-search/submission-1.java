class Solution {
    public int search(int[] nums, int target) {
        // 設定左界、右界
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            // 相較於 (l + r) / 2更能避免溢位
            int m = l + ((r - l) / 2);
            if (nums[m] > target){
                r = m - 1;
            } else if (nums[m] < target){
                l = m + 1;
            } else{
                return m;
            }
        }
        return -1;
    }
}
