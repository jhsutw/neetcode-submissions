class Solution {
    public int binary_search(int l, int r, int[] nums, int target) {
        // 若左值大於右值return -1
        if (l > r) return -1;
        // mid值為 l + (r - l) / 2
        int m  = l + (r - l) / 2;

        // 若mid值為target值則reutrn m
        // 若mid值小於target值，用 (m+1, r)再做BS
        // 若mid值大於target值，用 (l, m-1)再做BS
        if (nums[m] == target) return m;
        return (nums[m] < target) ?
            binary_search(m + 1, r, nums, target) :
            binary_search(l, m - 1, nums, target);
    }

    public int search(int[] nums, int target) {
        return binary_search(0, nums.length - 1, nums, target);
    }
}
